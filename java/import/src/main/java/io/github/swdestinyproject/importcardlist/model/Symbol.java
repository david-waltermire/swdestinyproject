
package io.github.swdestinyproject.importcardlist.model;

public enum Symbol {
	MELEE("MD", "Melee Damage"), RANGED("RD", "Ranged Damage"), INDIRECT("ID", "Indirect Damage"), DISCARD("Dc",
			"Discard"), DISRUPT("Dr", "Disrupt"), FOCUS("F", "Focus"), RESOURCE("R", "Resource"), SHIELD("Sh",
					"Shield"), SPECIAL("Sp", "Special"), BLANK("-", "Blank"), UNKNOWN("?", "Unknown");

	private String code;
	private String label;

	Symbol(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return this.code;
	}

	public String getLabel() {
		return this.label;
	}

	public static Symbol fromString(String text) {
		for (Symbol s : Symbol.values()) {
			if (s.code.equalsIgnoreCase(text)) {
				return s;
			}
		}
		return Symbol.UNKNOWN;
	}

	public String toString() {
		return this.label;
	}
}