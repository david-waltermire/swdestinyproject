package io.github.swdestinyproject.currency;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

public class QuoteBuilder {
	private final CurrencyUnit base;
	private Date date;
	private EnumMap<CurrencyUnit, BigDecimal> rates = new EnumMap<CurrencyUnit, BigDecimal>(CurrencyUnit.class);

	public QuoteBuilder(CurrencyUnit base) {
		this.base = base;
	}

	public QuoteBuilder setDate(Date date) {
		this.date = date;
		return this;
	}

	public QuoteBuilder addRate(String unitName, BigDecimal rate) {
		CurrencyUnit unit = CurrencyUnit.valueOf(unitName);
		return addRate(unit, rate);
	}

	public QuoteBuilder addRate(CurrencyUnit unit, BigDecimal rate) {
		rates.put(unit, rate);
		return this;
	}

	public Quote build() {
		return new BuildableQuote(base, date, rates);
	}

	private static class BuildableQuote implements Quote {
		private CurrencyUnit base;
		private Date date;
		private Map<CurrencyUnit, BigDecimal> rates;

		public BuildableQuote(CurrencyUnit base, Date date, EnumMap<CurrencyUnit, BigDecimal> rates) {
			this.base = base;
			this.date = date;
			this.rates = Collections.unmodifiableMap(rates);
		}

		@Override
		public CurrencyUnit getBase() {
			return base;
		}

		@Override
		public Date getDate() {
			return date;
		}

		@Override
		public Map<CurrencyUnit, BigDecimal> getConversionRates() {
			return rates;
		}

		@Override
		public BigDecimal getConversionRate(CurrencyUnit convertTo) throws UnsupportedSymbolException {
			return rates.get(convertTo);
		}
		
	}
}
