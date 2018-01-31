package io.github.swdestinyproject.currency;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.EnumSet;

public interface QuoteSource {
	Quote getQuote(CurrencyUnit base) throws MalformedURLException, IOException, ParseException;
	Quote getQuote(CurrencyUnit base, EnumSet<CurrencyUnit> symbols) throws MalformedURLException, IOException, ParseException;
}
