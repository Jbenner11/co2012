public class Square {

  // Data members
  protected String name;
  protected int location;

  // Constructors
  public Square() {
    name = "";
    location = 0;
  }

  public Square(String name, int location) {
    this.name = name;
    this.location = location;
  }

  // Instance methods
  public String getName() {
    return name;
  }

  public int getLocation() {
    return location;
  }
  
  public String toString() {
    return name;
  }

}// End of Square class
