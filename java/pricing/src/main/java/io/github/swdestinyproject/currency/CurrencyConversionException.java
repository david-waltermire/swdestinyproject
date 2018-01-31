package io.github.swdestinyproject.currency;

public class CurrencyConversionException extends Exception {

	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public CurrencyConversionException() {
	}

	public CurrencyConversionException(String message) {
		super(message);
	}

	public CurrencyConversionException(Throwable cause) {
		super(cause);
	}

	public CurrencyConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrencyConversionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
