package destiny.chancecube_import.currency;

public class UnsupportedSymbolException extends CurrencyConversionException {

	/**
	 * the serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedSymbolException() {
	}

	public UnsupportedSymbolException(String message) {
		super(message);
	}

	public UnsupportedSymbolException(Throwable cause) {
		super(cause);
	}

	public UnsupportedSymbolException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedSymbolException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
