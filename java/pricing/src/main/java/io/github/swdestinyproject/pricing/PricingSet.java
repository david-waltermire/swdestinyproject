package io.github.swdestinyproject.pricing;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PricingSet {
	@JsonProperty(value="source-id", index=2)
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
