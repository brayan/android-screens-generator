package com.brayanbedritchuk.tcc.exceptions;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = -2061261965701688694L;

	public ValidacaoException() {
		super();
	}

	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidacaoException(String message) {
		super(message);
	}

	public ValidacaoException(Throwable cause) {
		super(cause);
	}

}
