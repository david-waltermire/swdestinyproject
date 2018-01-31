package destiny.chancecube_import.scraper.handler;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import destiny.chancecube_import.scraper.AbstractPageHandler;
import destiny.chancecube_import.scraper.ParsedCard;

public class DiscountGamesIncPageHandler extends AbstractPageHandler {

	public DiscountGamesIncPageHandler() {
	}

	@Override
	public URI getNextPage(URI pageUri, Document page) throws IOException {
		Element element = page.selectFirst("div.pages a.next");
		URI retval = null;
		if (element != null) {
			String href = element.attr("href");
			retval = pageUri.resolve(href);
		}
		return retval;
	}

	@Override
	public List<ParsedCard> parse(URI pageUri, Document page) {
		Elements elements = page.select("ul.products-grid li");
		List<ParsedCard> cards = new LinkedList<>();
		for (Element element : elements) {
			// TODO: implement parser
		}
		return Collections.emptyList();;
	}

}
