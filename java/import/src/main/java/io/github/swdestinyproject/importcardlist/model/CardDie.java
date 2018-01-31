package io.github.swdestinyproject.importcardlist.model;

public class CardDie {
  
  DieSide side0;
  DieSide side1;
  DieSide side2;
  DieSide side3;
  DieSide side4;
  DieSide side5;
  
  
  public DieSide getSide1() {
    return side1;
  }
  public void setSide1(DieSide side1) {
    this.side1 = side1;
  }
  public DieSide getSide2() {
    return side2;
  }
  public void setSide2(DieSide side2) {
    this.side2 = side2;
  }
  public DieSide getSide3() {
    return side3;
  }
  public void setSide3(DieSide side3) {
    this.side3 = side3;
  }
  public DieSide getSide4() {
    return side4;
  }
  public void setSide4(DieSide side4) {
    this.side4 = side4;
  }
  public DieSide getSide5() {
    return side5;
  }
  public void setSide5(DieSide side5) {
    this.side5 = side5;
  }
  public DieSide getSide0() {
    return side0;
  }
  public void setSide0(DieSide side0) {
    this.side0 = side0;
  }
  
public String toString()
{
  return "Side0: " + getSide0() + "Side1: " + getSide1() + "Side2: " + getSide2() + 
      "Side3: " + getSide3() + "Side4: " + getSide4() + "Side5: " + getSide5();
}
  
}
