package exceptions;

public class CantidadDatosException extends Exception{

	private static final long serialVersionUID = 1L;

	public CantidadDatosException(String msgException) {
		super(msgException);
	}

}
