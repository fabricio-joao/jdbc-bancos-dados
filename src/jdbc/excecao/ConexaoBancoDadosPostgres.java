package jdbc.excecao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class ConexaoBancoDadosPostgres {
	
   private static Connection conexao = null;
	
	public static Connection FazendoConexaoBancoDados() {
		
		if(conexao == null) {
			try {
				Properties props = carregarDadosConexao();
				String url = props.getProperty("bd2url");
				conexao = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new ExcecaoJDBC(e.getMessage());
			}
		}
		
		return conexao;
	}
	
	public static void fecharConexao() {
		if(conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new ExcecaoJDBC(e.getMessage());
			}
		}
	}
	
	public static void fecharStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new ExcecaoJDBC(e.getMessage());
			}
		}
	}
	private static Properties carregarDadosConexao() {
		try (FileInputStream fs = new FileInputStream("bd2.propriedadesconexao")) {

			Properties props = new Properties();
			props.load(fs);
			return props;
	    
		} catch (IOException e) {
			throw new ExcecaoJDBC(e.getMessage());
		}
	}

}
