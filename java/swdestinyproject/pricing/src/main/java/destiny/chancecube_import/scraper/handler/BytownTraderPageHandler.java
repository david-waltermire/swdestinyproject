package destiny.chancecube_import.scraper.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import destiny.chancecube_import.currency.CurrencyUnit;
import destiny.chancecube_import.scraper.AbstractPageHandler;
import destiny.chancecube_import.scraper.CardSet;
import destiny.chancecube_import.scraper.DefaultParsedCard;
import destiny.chancecube_import.scraper.ParsedCard;

public class BytownTraderPageHandler extends AbstractPageHandler {
	private static final Pattern ID_PATTERN_SWD = Pattern.compile("swd([0-9]{1,2}-?[0-9]+).*");

	public BytownTraderPageHandler() throws IOException {
		super();
	}

	public void setupConnection(Connection connection) {
		connection.cookie("currency", "USD");
	}

	@Override
	public URI getNextPage(URI pageUri, Document page) throws IOException {
		Elements linkNext = page.select("link[rel=next]");
		URI retval = null;
		if (!linkNext.isEmpty()) {
			String href = linkNext.attr("href");
			retval = pageUri.resolve(href);
		}
		return retval;
	}

	@Override
	public List<ParsedCard> parse(URI pageUri, Document page) {
		List<ParsedCard> cards = new ArrayList<ParsedCard>();
		for (Element element : page.select("div.collection-main li")) {
			DefaultParsedCard card = new DefaultParsedCard();

			Element anchor = element.getElementsByTag("a").first();
			String name = anchor.attr("title");
			int index = name.indexOf(" - ");
			if (index == -1) {
				card.setName(name);
			} else {
				card.setName(name.substring(0, index));
				card.setTitle(name.substring(index+3));
			}

			// parse card ID from string like:
			// /collections/swd-singles/products/1001-captain-phasma-elite-trooper-awakenings
			String href = anchor.attr("href");
			card.setHref(pageUri.resolve(href));
			String text = href.split("/")[4];

			String id = null;
			String setNumber = null;
			{
				Matcher matcher = ID_PATTERN_SWD.matcher(text);
				if (matcher.matches()) {
					id = matcher.group(1);
					index = id.indexOf('-');
					if (index > -1) {
						setNumber = id.substring(0, index);
						id = id.substring(index+1);
					} else {
						setNumber = Integer.toString(Integer.valueOf(id)/1000);
						id = Integer.toString(Integer.valueOf(id) % 1000);
					}
				} else {
					index = text.indexOf('-');
					id = text.substring(0, index);
					setNumber = Integer.toString(Integer.valueOf(id)/1000);
					id = Integer.toString(Integer.valueOf(id) % 1000);
				}

				if (setNumber != null) {
					CardSet cardSet = null;
					switch (setNumber) {
					case "1":
						cardSet = CardSet.AWAKENINGS;
						break;
					case "2":
						cardSet = CardSet.SPIRIT_OF_REBELLION;
						break;
					case "7":
						cardSet = CardSet.EMPIRE_AT_WAR;
						break;
					case "8":
						cardSet = CardSet.TWO_PLAYER_GAME;
						break;
					case "11":
						cardSet = CardSet.LEGACIES;
						break;
					default:
						throw new RuntimeException(element.toString());
					}
					if (cardSet != null) {
						card.setCardSet(cardSet);
					}
				}
				card.setCardSetId(Integer.parseInt(id));
			}

			Elements priceElement = element.select("span.price");
			String cost = null;
			if (!priceElement.hasClass("sold-out")) {
				cost = priceElement.text();
				Pattern p = Pattern.compile("([0-9.]+)\\$ CAD");
				Matcher matcher = p.matcher(cost);
				if (matcher.matches()) {
					card.setCost(new BigDecimal(matcher.group(1)));
					card.setCurrencyUnit(CurrencyUnit.CAD);
				} else {
					cost = null;
				}
			}
			cards.add(card);
		}
		return cards;
	}

}
