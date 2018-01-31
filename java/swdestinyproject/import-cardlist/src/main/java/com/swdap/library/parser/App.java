
package com.swdap.library.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.swdap.library.model.Card;
import com.swdap.library.model.DAPSet;
import com.swdap.library.model.customParse.CardDeserializer;

/**
 * Testing Driver
 *
 */
public class App {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    FileReader cardsFile = null;
    FileReader setsFile = null;

    // System.out.println("Insert json filename (resources folder): ");
    // String fileName = in.nextLine();
    // try {
    // fileIn = loadJson(fileName);
    // } catch (FileNotFoundException e) {
    // System.out.println("No such file, try again. Theres no error handling sorry. :(");
    // e.printStackTrace();
    // }

    try {
      cardsFile = loadJson("library/cards.json");
      setsFile = loadJson("library/sets.json");
    } catch (Exception e) {
      e.printStackTrace();
    }

    GsonBuilder gsonBuilder = new GsonBuilder();

    JsonDeserializer<Card> deserializer = new CardDeserializer();
    gsonBuilder.registerTypeAdapter(Card.class, deserializer);

    Gson customGson = gsonBuilder.create();

    Card[] cards = customGson.fromJson(cardsFile, Card[].class);

    DAPSet[] sets = customGson.fromJson(setsFile, DAPSet[].class);

    System.out.println(sets[0]);
    interactiveDebug(cards);
    in.close();
  }

  private static FileReader loadJson(String fileName) throws FileNotFoundException {
    App.class.getClassLoader().getResource(fileName).getFile();
    File file = new File(App.class.getClassLoader().getResource(fileName).getFile());
    return new FileReader(file);
  }

  private static void interactiveDebug(Card[] cards) {
    ArrayList<Card> aw = new ArrayList<Card>();
    ArrayList<Card> sor = new ArrayList<Card>();
    ArrayList<Card> eaw = new ArrayList<Card>();
    ArrayList<Card> leg = new ArrayList<Card>();
    ArrayList<Card> twop = new ArrayList<Card>();
    ArrayList<Card> rivals = new ArrayList<Card>();

    for (Card card : cards) {
      switch (card.getSet().getCode()) {
      case "AW":
        aw.add(card);
        break;
      case "SOR":
        sor.add(card);
        break;
      case "EAW":
        eaw.add(card);
        break;
      case "LEG":
        leg.add(card);
        break;
      case "2p":
        twop.add(card);
        break;
      case "RIV":
        rivals.add(card);
        break;
      }
    }
    Scanner in = new Scanner(System.in);
    boolean cont = true;
    System.out.println("Cards parsed successfully.");
    while (cont) {
      ArrayList<Card> tempList = null;
      System.out.println("Which set do you want to open? (\"AW\",\"SOR\",\"EAW\",\"LEG\",\"RIV\") ");
      String setString = in.nextLine();

      switch (setString) {
      case "AW":
        tempList = aw;
        break;
      case "SOR":
        tempList = sor;
        break;
      case "EAW":
        tempList = eaw;
        break;
      case "LEG":
        tempList = leg;
        break;
      case "2p":
        tempList = twop;
        break;
      case "RIV":
        tempList = rivals;
        break;
      }
      System.out.println("Which card would you like info on? (Card Number): ");
      int cardNumber = 0;

      try {
        cardNumber = Integer.parseInt(in.nextLine());
      } catch (Exception e) {
        System.out.println("Something went wrong! :(");
        e.printStackTrace();
      }

      System.out.println("---------------------------------------------------");
      System.out.println(tempList.get(cardNumber));
      System.out.println("---------------------------------------------------");

    }
    in.close();
  }
}
