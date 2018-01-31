package io.github.swdestinyproject.pricing.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.github.swdestinyproject.currency.CurrencyUnit;
import io.github.swdestinyproject.pricing.AbstractPageHandler;
import io.github.swdestinyproject.pricing.CardSet;
import io.github.swdestinyproject.pricing.DefaultParsedCard;
import io.github.swdestinyproject.pricing.ParsedCard;

public class CoolStuffIncPageHandler extends AbstractPageHandler {

	public CoolStuffIncPageHandler() {
	}

	@Override
	public URI getNextPage(URI pageUri, Document page) throws IOException {
		Elements elements = page.select("div#mainContent > table.mySearch tfoot span#nextLink a");
		URI retval = null;
		if (!elements.isEmpty()) {
			String href = elements.last().attr("href");
			retval = pageUri.resolve(href);
		}
		return retval;
	}

	@Override
	public List<ParsedCard> parse(URI pageUri, Document page) {
		Elements elements = page.select("div#mainContent table.mySearch tbody tr[itemtype=http://schema.org/Product]");
		List<ParsedCard> cards = new LinkedList<>();
		for (Element element : elements) {
			DefaultParsedCard card = new DefaultParsedCard();

			String name = element.select("span[itemprop=name]").text();
			int index = name.indexOf(" - ");
			if (index == -1) {
				card.setName(name);
			} else {
				card.setName(name.substring(0, index));
				card.setTitle(name.substring(index + 3));
			}

			String href = element.select("a.productLink").attr("href");
			card.setHref(pageUri.resolve(href));

			String cardSetName = element.selectFirst("h4").text();
			String[] path = cardSetName.split(" » ");
			String setName = path[path.length - 1];
			switch (setName) {
			case "Legacies":
				card.setCardSet(CardSet.LEGACIES);
				break;
			case "Empire At War":
				card.setCardSet(CardSet.EMPIRE_AT_WAR);
				break;
			case "Spirit of Rebellion":
				card.setCardSet(CardSet.SPIRIT_OF_REBELLION);
				break;
			case "Awakenings":
				card.setCardSet(CardSet.AWAKENINGS);
				break;
			case "Star Wars: Destiny Two-Player Game":
				card.setCardSet(CardSet.TWO_PLAYER_GAME);
				break;
			default:
				throw new RuntimeException(setName);
			}

			for (Element priceNode : element.select("table.userTable tbody tr")) {
				String pCondition = priceNode.select("td.pCondition").first().text();
				if ("Near Mint".equals(pCondition)) {
					String price = priceNode.select("td.pPrice span[itemprop=price]").first().text();
					if (price.startsWith("$")) {
						price = price.substring(1);
					}
					card.setCost(new BigDecimal(price));
					card.setCurrencyUnit(CurrencyUnit.USD);
					break;
				}
			}
			cards.add(card);
		}
		return Collections.unmodifiableList(cards);
	}

	@Override
	public void setupConnection(Connection connection) {
	}

}
