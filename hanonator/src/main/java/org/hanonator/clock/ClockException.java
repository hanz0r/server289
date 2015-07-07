package org.hanonator.clock;

public class ClockException extends Exception {

	private static final long serialVersionUID = -8925957584947521952L;

	public ClockException() {
		super();
	}

	public ClockException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClockException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClockException(String message) {
		super(message);
	}

	public ClockException(Throwable cause) {
		super(cause);
	}

	

}