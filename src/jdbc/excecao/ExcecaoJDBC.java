package jdbc.excecao;

public class ExcecaoJDBC extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExcecaoJDBC(String msg) {
		super(msg);
	}
}
