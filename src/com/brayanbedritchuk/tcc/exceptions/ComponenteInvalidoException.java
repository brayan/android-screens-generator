package com.brayanbedritchuk.tcc.exceptions;

public class ComponenteInvalidoException extends Exception {

	private static final long serialVersionUID = -8799670715341702515L;

	public ComponenteInvalidoException() {
		super();
	}

	public ComponenteInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ComponenteInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComponenteInvalidoException(String message) {
		super(message);
	}

	public ComponenteInvalidoException(Throwable cause) {
		super(cause);
	}

}
