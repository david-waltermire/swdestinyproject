
package com.swdap.library.model;

public class Card {

	String name;
	DSet set;
	DType type;
	DAffiliation affil;
	DFaction faction;
	DRarity rarity;
	//DSubType subtype;

	CardDie die;

	String subtitle;

	int health;
	int points;
	int ePoints;
	int cost;
	int positionInSet;

	String code;
	String text;
	String flavor;
	
	String passive;
	String action;
	String powerAction;
	String special;

	boolean has_Die;
	boolean is_unique;
	boolean has_errata;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DSet getSet() {
		return set;
	}

	public void setSet(DSet set) {
		this.set = set;
	}

	public DType getType() {
		return type;
	}

	public void setType(DType type) {
		this.type = type;
	}

	public DAffiliation getAffil() {
		return affil;
	}

	public void setAffil(DAffiliation affil) {
		this.affil = affil;
	}

	public DFaction getFaction() {
		return faction;
	}

	public void setFaction(DFaction faction) {
		this.faction = faction;
	}

	public DRarity getRarity() {
		return rarity;
	}

	public void setRarity(DRarity rarity) {
		this.rarity = rarity;
	}

	public CardDie getDie() {
		return die;
	}

	public void setDie(CardDie die) {
		this.die = die;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getePoints() {
		return ePoints;
	}

	public void setePoints(int ePoints) {
		this.ePoints = ePoints;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPositionInSet() {
		return positionInSet;
	}

	public void setPositionInSet(int positionInSet) {
		this.positionInSet = positionInSet;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPassive() {
		return passive;
	}

	public void setPassive(String passive) {
		this.passive = passive;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPowerAction() {
		return powerAction;
	}

	public void setPowerAction(String powerAction) {
		this.powerAction = powerAction;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public boolean isHas_Die() {
		return has_Die;
	}

	public void setHas_Die(boolean has_Die) {
		this.has_Die = has_Die;
	}

	public boolean isIs_unique() {
		return is_unique;
	}

	public void setIs_unique(boolean is_unique) {
		this.is_unique = is_unique;
	}

	public boolean isHas_errata() {
		return has_errata;
	}

	public void setHas_errata(boolean has_errata) {
		this.has_errata = has_errata;
	}


	public void setFlavor(String asString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Card [name=" + name + ", set=" + set + ", type=" + type + ", affil=" + affil + ", faction=" + faction
				+ ", rarity=" + rarity + ", die=" + die + ", subtitle=" + subtitle + ", health=" + health + ", points="
				+ points + ", ePoints=" + ePoints + ", cost=" + cost + ", positionInSet=" + positionInSet + ", code="
				+ code + ", text=" + text + ", flavor=" + flavor + ", passive=" + passive + ", action=" + action
				+ ", powerAction=" + powerAction + ", special=" + special + ", has_Die=" + has_Die + ", is_unique="
				+ is_unique + ", has_errata=" + has_errata + "]";
	}
	
	
	
}
