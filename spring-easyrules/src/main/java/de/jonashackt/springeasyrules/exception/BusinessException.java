package de.jonashackt.springeasyrules.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(String message) {
		super(message);
	}

}
