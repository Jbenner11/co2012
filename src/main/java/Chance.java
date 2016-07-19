public class Chance extends Square {
  
  private int amount;

  // Constructor
  public Chance(String name, int location, int amount) {
    super(name, location);
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
  
  // Instance methods
  public int generateAmount() {
    return -1; /* amount generated from Chance */
  }

}// End of Chance class
