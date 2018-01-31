package com.swdap.library.model;

public class DieSide {
  
  int cost;
  int value;
  Symbol symbol;
  boolean modifier;
  String fullString;
  
  public String getFullString() {
    return fullString;
  }
  public void setFullString(String fullString) {
    this.fullString = fullString;
  }
  public int getCost() {
    return cost;
  }
  public void setCost(int cost) {
    this.cost = cost;
  }
  public int getValue() {
    return value;
  }
  public void setValue(int value) {
    this.value = value;
  }
  public Symbol getSymbol() {
    return symbol;
  }
  public void setSymbol(Symbol symbol) {
    this.symbol = symbol;
  }
  public boolean isModifier() {
    return modifier;
  }
  public void setModifier(boolean modifier) {
    this.modifier = modifier;
  }
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("[cost=");
    builder.append(cost);
    builder.append(", value=");
    builder.append(value);
    builder.append(", symbol=");
    builder.append(symbol);
    builder.append(", modifier=");
    builder.append(modifier);
    builder.append(", fullString= '");
    builder.append(fullString);
    builder.append("']\n");
    return builder.toString();
  }
  
  
  

}
