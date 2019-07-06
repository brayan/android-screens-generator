package com.brayanbedritchuk.tcc.exceptions;

public class ConfiguracaoException extends Exception {

	private static final long serialVersionUID = -3302884686711530258L;

	public ConfiguracaoException() {
		super();
	}

	public ConfiguracaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConfiguracaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfiguracaoException(String message) {
		super(message);
	}

	public ConfiguracaoException(Throwable cause) {
		super(cause);
	}

}
