
package io.github.swdestinyproject.importcardlist.customParse;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import io.github.swdestinyproject.importcardlist.model.Card;
import io.github.swdestinyproject.importcardlist.model.CardDie;
import io.github.swdestinyproject.importcardlist.model.DSet;
import io.github.swdestinyproject.importcardlist.model.DieSide;

/**
 * 
 * Where the "magic" happens. Implements the JsonDeserializer class from GSON to allow it to be
 * integrated into GSON's deserialization process. Consumes a JsonObject that represents a single
 * card in the SWDESTINYDB format.
 * 
 * @author sab3
 *
 */
public class CardDeserializer implements JsonDeserializer<Card> {

  /**
   * 
   * This is the method called by the GSON parser.
   * 
   */
  public Card deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    Card card = new Card();
    deserializePrimatives(card, jsonObject);
    if (jsonObject.has("sides")) {
      card.setHas_Die(true);
      deserializeDie(card, jsonObject);
    }
    deserializeTypes(card, jsonObject);
    return card;
  }

  //TODO: Finish the type deserializers
  private void deserializeTypes(Card card, JsonObject obj) {
    String points = "";
    JsonElement e = obj.get("points");
    if (e != null && !e.isJsonNull()) {
      points = e.getAsString();
      int slashLoc = points.indexOf("/");
      if (slashLoc == -1) {
        card.setPoints(e.getAsInt());
      } else {
        card.setPoints(Integer.parseInt(points.substring(0, slashLoc)));
        card.setePoints(Integer.parseInt(points.substring(slashLoc + 1)));
      }
    }

    e = obj.get("set_code");
    if (e != null && !e.isJsonNull()) {
      card.setSet(DSet.fromString(e.getAsString()));
    } else {
      card.setSet(DSet.UNKNOWN);
    }

  }

  /**
   * 
   * If the card in question has a die, this will generate a CardDie object from the JSON (SWDDB
   * Format)
   * 
   * @param card
   *          The card object being generated.
   * @param obj
   *          A JSON Object representing the Card
   */
  private void deserializeDie(Card card, JsonObject obj) {
    CardDie die = new CardDie();

    DieStringParser dieParser = new DieStringParser(obj.get("sides").getAsJsonArray());

    die.setSide0(dieParser.parse(0));
    die.setSide1(dieParser.parse(1));
    die.setSide2(dieParser.parse(2));
    die.setSide3(dieParser.parse(3));
    die.setSide4(dieParser.parse(4));
    die.setSide5(dieParser.parse(5));

    card.setDie(die);
  }

  // TODO: All this nonsense
  private void deserializePrimatives(Card card, JsonObject obj) {

    JsonElement e = obj.get("name");
    if (e != null && !e.isJsonNull()) {
      card.setName(e.getAsString());
    } else {
      card.setName("UNKNOWN NAME");
    }

    e = obj.get("subtitle");
    if (e != null && !e.isJsonNull()) {
      card.setSubtitle(e.getAsString());
    } else {
      card.setSubtitle("UNKNOWN SUBTITLE");
    }

  }

  // TODO: Maybe do this??
  private Boolean check(JsonObject obj, String memberName) {
    return (obj.has(memberName) && !obj.get(memberName).isJsonNull());

  }

}
