package destiny.chancecube_import.currency.source;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import destiny.chancecube_import.currency.CurrencyUnit;
import destiny.chancecube_import.currency.Quote;
import destiny.chancecube_import.currency.QuoteBuilder;
import destiny.chancecube_import.currency.QuoteSource;

public class FixerQuoteSource implements QuoteSource {
	private static final Logger log = LogManager.getLogger(FixerQuoteSource.class);

	private static final String API_URL = "https://api.fixer.io/latest";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public FixerQuoteSource() {
	}

	public Quote getQuote(CurrencyUnit base) throws MalformedURLException, IOException, ParseException {
		return getQuote(base, EnumSet.allOf(CurrencyUnit.class));
	}

	public Quote getQuote(CurrencyUnit base, EnumSet<CurrencyUnit> symbols) throws MalformedURLException, IOException, ParseException {
		URI api = buildURI(base, symbols);
		
		log.info("retrieving currency info for base '{}' from '{}'", base, api);
		QuoteBuilder builder = new QuoteBuilder(base);

		ObjectMapper objectMapper = new ObjectMapper();
		try (InputStream is = api.toURL().openConnection().getInputStream()) {
			JsonNode rootNode = objectMapper.readTree(is);
			Date date = DATE_FORMAT.parse(rootNode.get("date").textValue());
			builder.setDate(date);
			JsonNode ratesNode = rootNode.get("rates");
			Iterator<Map.Entry<String, JsonNode>> nodes = ratesNode.fields();
			while (nodes.hasNext()) {
				Map.Entry<String, JsonNode> entry = nodes.next();
				String key = entry.getKey();
				JsonNode rateNode = entry.getValue();
				builder.addRate(key, BigDecimal.valueOf(rateNode.asDouble()));
			}
		}
		return builder.build();
	}

	private URI buildURI(CurrencyUnit base, EnumSet<CurrencyUnit> symbols) {
		StringBuilder builder = new StringBuilder(API_URL);
		builder.append("?base=");
		builder.append(base.name());
		builder.append("&symbols=");

		boolean first = true;
		for (CurrencyUnit symbol : symbols) {
			if (first) {
				first = false;
			} else {
				builder.append(',');
			}
			builder.append(symbol.name());
		}
		return URI.create(builder.toString());
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		Quote quote = new FixerQuoteSource().getQuote(CurrencyUnit.USD);
		for (Map.Entry<CurrencyUnit, BigDecimal> entry : quote.getConversionRates().entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
