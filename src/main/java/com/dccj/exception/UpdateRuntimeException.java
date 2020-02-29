package com.dccj.exception;

public class UpdateRuntimeException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 18794756827824687L;

	public UpdateRuntimeException() {
		super();
	}

	public UpdateRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateRuntimeException(String message) {
		super(message);
	}

	public UpdateRuntimeException(Throwable cause) {
		super(cause);
	}
	
	

}
