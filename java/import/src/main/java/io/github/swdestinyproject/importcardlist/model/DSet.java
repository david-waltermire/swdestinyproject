
package io.github.swdestinyproject.importcardlist.model;

//TODO: Enum or Object for set?
public enum DSet {

  AWAKENINGS("AW", "Awakenings"),
  SPIRIT("SOR", "Spirit of Rebellion"),
  EMPIRE("EAW", "Empire at War"),
  LEGACIES("LEG", "Legacies"),
  UNKNOWN("UN?", "Unknown");

  public String getCode() {
    return code;
  }

  public String getLabel() {
    return label;
  }

  private String code;
  private String label;

  DSet(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public static DSet fromString(String text) {
    for (DSet s : DSet.values()) {
      if (s.code.equalsIgnoreCase(text)) {
        return s;
      }
    }
    return DSet.UNKNOWN;
  }

}
