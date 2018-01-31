package com.swdap.library.model;

public enum DType {

	CHARACTER("character", "Character"), EVENT("event", "Event"), SUPPORT("support", "Support"), UPGRADE("upgrade",
			"Upgrade"), PLOT("plot", "Plot"), BATTLEFIELD("battlefield", "Battlefield"), UNKNOWN("unknown","Unknown");

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	private String code;
	private String label;

	DType(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public static DType fromString(String text) {
		for (DType s : DType.values()) {
			if (s.code.equalsIgnoreCase(text)) {
				return s;
			}
		}
		return DType.UNKNOWN;
	}
	
}
