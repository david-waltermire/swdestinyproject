package destiny.chancecube_import.currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import destiny.chancecube_import.currency.source.FixerQuoteSource;

public class CurrencyConverter {

	private CurrencyUnit defaultCurrencyUnit = CurrencyUnit.USD;
	private final QuoteSource quoteSource;
	private Map<CurrencyUnit, Quote> baseToQuoteCache = new HashMap<>();

	public CurrencyConverter() {
		this(new FixerQuoteSource());
	}

	public CurrencyConverter(QuoteSource quoteSource) {
		this.quoteSource = quoteSource;
	}

	public BigDecimal convertCurrency(BigDecimal value, CurrencyUnit convertTo) throws CurrencyConversionException {
		return convertCurrency(value, defaultCurrencyUnit, convertTo);
	}

	public BigDecimal convertCurrency(BigDecimal value, CurrencyUnit base, CurrencyUnit convertTo)
			throws CurrencyConversionException {
		Quote quote;
		try {
			quote = getQuote(base);
		} catch (IOException | ParseException e) {
			throw new CurrencyConversionException(e);
		}
		BigDecimal conversionRate = quote.getConversionRate(convertTo);
		return value.divide(conversionRate);
	}

	public Quote getQuote(CurrencyUnit base) throws MalformedURLException, IOException, ParseException {
		Quote retval = baseToQuoteCache.get(base);
		if (retval == null) {
			retval = quoteSource.getQuote(base);
			baseToQuoteCache.put(base, retval);
		}
		return retval;
	}
}
