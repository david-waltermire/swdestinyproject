package com.swdap.library.model;

public enum DAffiliation {
	
	HERO("hero","Hero"),
	VILLAIN("villain","Villain"),
	NEUTRAL("neutral","Neutral"),
	UNKNOWN("unknown","Unknown");
	
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	private String code;
	private String label;

	DAffiliation(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public static DAffiliation fromString(String text) {
		for (DAffiliation s : DAffiliation.values()) {
			if (s.code.equalsIgnoreCase(text)) {
				return s;
			}
		}
		return DAffiliation.UNKNOWN;
	}

}
