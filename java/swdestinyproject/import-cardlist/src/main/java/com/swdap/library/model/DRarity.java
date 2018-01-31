package com.swdap.library.model;

public enum DRarity {
	COMMON("C","Common"),
	UNCOMMON("U","Uncommon"),
	RARE("R","Rare"),
	LEGENDARY("L","Legendary"),
	UNKNOWN("U?","Unknown");
	
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	private String code;
	private String label;

	DRarity(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public static DRarity fromString(String text) {
		for (DRarity s : DRarity.values()) {
			if (s.code.equalsIgnoreCase(text)) {
				return s;
			}
		}
		return DRarity.UNKNOWN;
	}

}
