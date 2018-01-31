package io.github.swdestinyproject.currency;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface Quote {
	CurrencyUnit getBase();
	Date getDate();
	Map<CurrencyUnit, BigDecimal> getConversionRates();
	BigDecimal getConversionRate(CurrencyUnit convertTo) throws UnsupportedSymbolException;
}
