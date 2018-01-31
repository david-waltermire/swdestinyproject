package com.swdap.library.model;

public enum DFaction {
	RED("red","Command"),
	YELLOW("yellow","Rouge"),
	BLUE("blue","Force"),
	GENERAL("general","General"),
	UNKNOWN("unknown","Unknown");

	
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	private String code;
	private String label;

	DFaction(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public static DFaction fromString(String text) {
		for (DFaction s : DFaction.values()) {
			if (s.code.equalsIgnoreCase(text)) {
				return s;
			}
		}
		return DFaction.UNKNOWN;
	}
}
