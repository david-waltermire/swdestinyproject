package destiny.chancecube_import.scraper;

import java.util.Collections;
import java.util.List;

public class PricingSet {
	private final String sourceId;
	private final List<ParsedCard> cardPricingInfo;

	public PricingSet(String sourceId, List<? extends ParsedCard> cardPricingInfo) {
		this.sourceId = sourceId;
		this.cardPricingInfo = Collections.unmodifiableList(cardPricingInfo);
	}

	public String getSourceId() {
		return sourceId;
	}

	public List<ParsedCard> getCardPricingInfo() {
		return cardPricingInfo;
	}

}
