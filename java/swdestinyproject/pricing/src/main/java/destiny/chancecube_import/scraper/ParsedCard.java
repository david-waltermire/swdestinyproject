package destiny.chancecube_import.scraper;

import java.math.BigDecimal;

public interface ParsedCard {
	String getName();
	String getTitle();
	CardSet getCardSet();
	int getCardSetId();
	BigDecimal getCost();
}
