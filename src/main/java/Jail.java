public class Jail extends Square {

  public Jail(String name, int location) {
    super(name,location);
  }
  
  // Data members
  private int numOfPlayerInJail;
  // Need a map to remember number of rounds left for each player in jail.
  
  public Jail() {
    this.numOfPlayerInJail = 0;
  }
  
  public void goJail() {
    this.numOfPlayerInJail++;
  }
  
  public boolean did_Pay() {
    return true;
  }

}// End of Jail class
