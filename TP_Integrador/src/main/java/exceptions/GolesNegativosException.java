package exceptions;

public class GolesNegativosException extends Exception{

	private static final long serialVersionUID = 1L;

	public GolesNegativosException(String msgException) {
		super(msgException);
	}
}
