package io.github.swdestinyproject.pricing;

import java.math.BigDecimal;
import java.net.URI;

import io.github.swdestinyproject.currency.CurrencyUnit;

public class DefaultParsedCard implements ParsedCard {
	private String name;
	private String title;
	private CardSet cardSet;
	private int cardSetId;
	private BigDecimal cost;
	private CurrencyUnit currencyUnit;
	private URI href;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CardSet getCardSet() {
		return cardSet;
	}

	public void setCardSet(CardSet cardSet) {
		this.cardSet = cardSet;
	}

	public int getCardSetId() {
		return cardSetId;
	}

	public void setCardSetId(int id) {
		this.cardSetId = id;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public CurrencyUnit getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(CurrencyUnit currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
}
