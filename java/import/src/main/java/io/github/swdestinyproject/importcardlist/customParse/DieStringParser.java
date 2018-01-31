
package io.github.swdestinyproject.importcardlist.customParse;

import com.google.gson.JsonArray;

import io.github.swdestinyproject.importcardlist.model.DieSide;
import io.github.swdestinyproject.importcardlist.model.Symbol;

/**
 * 
 * A parser object that takes a JsonArray object of Die sides and provides a means to parse each
 * side. Done as a separate object to support more modular configuration and options in the future.
 * 
 * @author sab3
 *
 */
public class DieStringParser {

  JsonArray sides;

  public DieStringParser(JsonArray sides) {
    setSides(sides);
  }

  public void setSides(JsonArray sides) {
    this.sides = sides;
  }

  /**
   * 
   * Parses a single side of a die. No clever solution here, it's a manually unrolled parse tree.
   * 
   * @param i
   *          The die side (from "sides") to parse. (0-5)
   * @return A constructed DieSide object fully representing the string stored in the sides array.
   */
  public DieSide parse(int i) {
    DieSide side = new DieSide();
    String dieString = sides.get(i).getAsString();
    side.setFullString(dieString);

    int c = 0;
    if (dieString.charAt(c) == '+') {
      side.setModifier(true);
      c += 1;
    }

    if (Character.isDigit(dieString.charAt(c))) {
      side.setValue(Character.getNumericValue(dieString.charAt(c)));
      c += 1;
    }

    String symbolString = "Unknown";
    if (dieString.length() > c + 1 && Character.isLetter(dieString.charAt(c + 1))) {
      symbolString = dieString.substring(c, c + 2);
      c += 2;
    } else {
      symbolString = dieString.substring(c, c + 1);
      c += 1;
    }
    side.setSymbol(Symbol.fromString(symbolString));

    if (dieString.length() > c) {
      side.setCost(Character.getNumericValue(dieString.charAt(c)));
    }

    return side;

  }

}
