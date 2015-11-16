package com.iitdev.exception;

public class IitdevRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495914701854217249L;

	public IitdevRuntimeException() {
		super();
	}

	public IitdevRuntimeException(String message) {
		super(message);
	}

	public IitdevRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IitdevRuntimeException(Throwable cause) {
		super(cause);
	}
}
