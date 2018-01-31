package io.github.swdestinyproject.pricing;

import java.math.BigDecimal;

public interface ParsedCard {
	String getName();
	String getTitle();
	CardSet getCardSet();
	int getCardSetId();
	BigDecimal getCost();
}
