package destiny.chancecube_import.scraper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import destiny.chancecube_import.scraper.config.ScraperConfig;
import destiny.chancecube_import.scraper.config.Source;

public class Scraper {
	private static final Logger log = LogManager.getLogger(Scraper.class);

	private final ScraperConfig scraperConfig;

	public Scraper(ScraperConfig config) {
		this.scraperConfig = config;
	}

	public void parse() throws IOException {
		for (Source source : scraperConfig.getSources()) {
			PricingSet prices = parseSource(source);
			if (prices != null) {
				for (ParsedCard card : prices.getCardPricingInfo()) {
					StringBuilder builder = new StringBuilder();
					builder.append("(");
					builder.append(card.getCardSetId());
					builder.append("): ");
					builder.append(card.getName());
					if (card.getTitle() != null) {
						builder.append(" - ");
						builder.append(card.getTitle());
					}
					builder.append(": ");
					if (card.getCost() != null) {
						builder.append(card.getCost());
					}
					System.out.println(builder.toString());
				}
			}
			savePricingSet(prices);
		}
	}

	private void savePricingSet(PricingSet prices) {
		StringWriter writer = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			objectMapper.writeValue(writer, prices);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(writer);
	}

	public PricingSet parseSource(Source source) {
		String id = source.getId();
		log.info("Scraping source '{}'", id);
		
		List<ParsedCard> allCardData = new LinkedList<>();
		for (URI nextPage : source.getFirstPages()) {

			log.info("Starting with page '{}'", nextPage);
			Class<? extends PageHandler> pageHandlerClass = source.getPageHandlerClass();
			PageHandler handler;
			try {
				handler = pageHandlerClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}

			while (nextPage != null) {
				log.info("Loading page '{}'", nextPage);
				Document document = loadPage(nextPage, handler);
				// process the document
				allCardData.addAll(handler.parse(nextPage, document));

				// get the next URI to process
				try {
					nextPage = handler.getNextPage(nextPage, document);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		PricingSet retval = null;
		if (!allCardData.isEmpty()) {
			retval = new PricingSet(id, allCardData);
		}

		return retval;
	}

	public ScraperConfig getScraperConfig() {
		return scraperConfig;
	}

	protected Document loadPage(URI pageLocation, PageHandler handler) {
		Document document = loadFromCache(pageLocation);
		// if not found in cache
		if (document == null) {
			document = retrievePage(pageLocation, handler);
			saveToCache(pageLocation, document);
		}
		return document;
	}

	private static File urlToFile(URI pageLocation) {
		File retval = new File("cache");
		retval = new File(retval, pageLocation.getRawAuthority());
		String path = pageLocation.getRawPath();
		String[] segments = path.split("/");
		for (int i = 0; i < segments.length; i++) {
			String segment = segments[i];
			if (i == segments.length - 1) {
				segment = segment + "---" + pageLocation.getQuery();
			}
			retval = new File(retval, segment);
		}
		return retval;
	}

	private void saveToCache(URI pageLocation, Document document) {
		File file = urlToFile(pageLocation);
		file.getParentFile().mkdirs();
		try (Writer writer = new FileWriter(file)) {
			writer.write(document.outerHtml());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Document loadFromCache(URI pageLocation) {
		File file = urlToFile(pageLocation);
		Document retval = null;
		if (file.exists()) {
			log.info("loading '{}' from cache at '{}'", pageLocation, file);
			String html;
			try {
				html = new String(Files.readAllBytes(file.toPath()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			retval = Jsoup.parse(html, pageLocation.toASCIIString());
		}

		return retval;
	}

	protected Document retrievePage(URI pageLocation, PageHandler handler) {
		// prepare the connection
		Connection connection = Jsoup.connect(pageLocation.toASCIIString());
		// customize the connection
		handler.setupConnection(connection);
		// retrieve the document
		Document document;
		try {
			document = connection.get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return document;
	}

	public static void main(String[] args) throws IOException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 FileReader reader = new FileReader(new File("scraper-config.json"));
		 ScraperConfig config = objectMapper.readValue(reader,
		 ScraperConfig.class);
		 new Scraper(config).parse();
	}
}
