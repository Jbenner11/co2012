
public class Property extends BuyableSquare {

  // Data members
  private String color;
  private int numOfHouses;

  /**
   * Property Basics.
   */
  public Property(String name, int location, int cost, int rent, int multiplier,
      int mortgagePrice, String color) {
    super(name, location, cost, rent, multiplier, mortgagePrice);
    this.color = color;
    this.numOfHouses = 0;
  }

  // Instance methods
  public String getColor() {
    return color;
  }
  
  public int getNumOfHouses() {
    return numOfHouses;
  }
  
  /**
   * add Houses.
   */
  public void addHouse(int amountOfHouses) {
    if (numOfHouses + amountOfHouses <= 5) {
      numOfHouses += amountOfHouses;
    }
  }
  
  public void sellHouse(int amountOfHouses) {
    numOfHouses -= amountOfHouses;
  }

}// End of Property class
