package jdbc.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jdbc.excecao.ConexaoBancoDadosMysql;
import jdbc.excecao.ConexaoBancoDadosPostgres;
import jdbc.excecao.ExcecaoJDBC;

public class Programa {

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		System.out.print("Entre com codigo do Banco de Dados: [1] = MYSQL ; [2] = POSTGRES ");
		int codigo = scan.nextInt();
		
		switch (codigo) {
		case 1: {
			Connection conexao = null;
			PreparedStatement ps = null;
			try {
				conexao = ConexaoBancoDadosMysql.FazendoConexaoBancoDados();
				ps = conexao.prepareStatement(
						"CREATE DATABASE atividade ");
		
				int linha = ps.executeUpdate();
				System.out.println("Linhas alteradas: " + linha);
			}
			catch(SQLException e) {
				throw new ExcecaoJDBC(e.getMessage());
			}
			finally {
				ConexaoBancoDadosMysql.fecharStatement(ps);
				ConexaoBancoDadosMysql.fecharConexao();
			}
			
			break;
		}
		
		case 2: {
			Connection conexao = null;
			PreparedStatement ps = null;
			try {
				conexao = ConexaoBancoDadosPostgres.FazendoConexaoBancoDados();
				ps = conexao.prepareStatement(
						"CREATE DATABASE atividade ");
		
				int linha = ps.executeUpdate();
				System.out.println("Linhas alteradas: " + linha);
			}
			catch(SQLException e) {
				throw new ExcecaoJDBC(e.getMessage());
			}
			finally {
				ConexaoBancoDadosMysql.fecharStatement(ps);
				ConexaoBancoDadosMysql.fecharConexao();
			}
			
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + codigo);
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	

	}
}
