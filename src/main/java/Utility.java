public class Utility extends BuyableSquare {

  // Constructor
  public Utility(String name, int location, int cost, int rent, int multiplier,
      int mortgagePrice) {
    super(name, location, cost, rent, multiplier, mortgagePrice);
  }

  // Instance methods
  public void setMultiplier(int multiplier) {
    super.multiplier = multiplier;
  }

}// End of Utility class
