import java.util.ArrayList;
import java.util.HashMap;


public class Board {

  // Data Members

  private ArrayList<Square> squares = new ArrayList<>();
  private HashMap<String, Square> squareMap = new HashMap<>();
  public int id;
  public String name; 

  public enum Counters {
      DOG, SHIP, CAR, HAT, THIMBLE, BOOT        
  }
  
  // Constructors
  /**
   * The constructor initialises all the squares on the board.
   */ 
  public Board() {
    this.addSquare(new Square("GO",0));
    this.addSquare(new Property("Old Kent Road",1,60,2,2,50,"brown"));
    this.addSquare(new CommunityChest("Community Chest1",2,200));
    this.addSquare(new Property("Whitechapel Road",3,60,4,2,50,"brown"));
    this.addSquare(new Square("Income Tax",4));
    this.addSquare(new RailRoad("Kings Cross Station",5,200,25,2,100));
    this.addSquare(new Property("The Angel Islington",6,100,6,2,50,"light blue"));
    this.addSquare(new Chance("Chance1",7,100));
    this.addSquare(new Property("Euston Road",8,100,6,2,50,"light blue"));
    this.addSquare(new Property("Pentonville Road",9,120,8,2,60,"light blue"));
    this.addSquare(new Jail("Jail",10));
    this.addSquare(new Property("Pall Mall",11,140,10,2,70,"pink"));
    this.addSquare(new Utility("Electric Company",12,150,30,4,75));
    this.addSquare(new Property("Whitehall",13,140,10,2,70,"pink"));
    this.addSquare(new Property("Northumberland Avenue",14,160,12,2,80,"pink"));
    this.addSquare(new RailRoad("Marylebone Station",15,200,25,2,100));
    this.addSquare(new Property("Bow Street",16,180,14,2,90,"orange"));
    this.addSquare(new CommunityChest("Community Chest2",17,50));
    this.addSquare(new Property("Marlborough Street",18,180,14,2,90,"orange"));
    this.addSquare(new Property("Vine Street",19,200,16,2,100,"orange"));
    this.addSquare(new Square("Free Parking",20));
    this.addSquare(new Property("Strand",21,220,18,2,110,"red"));
    this.addSquare(new Chance("Chance2",22,-100));
    this.addSquare(new Property("Fleet Street",23,220,18,2,110,"red"));
    this.addSquare(new Property("Trafalgar Square",24,240,20,2,120,"red"));
    this.addSquare(new RailRoad("Fenchurch St Station",25,200,25,2,100));
    this.addSquare(new Property("Leicester Square",26,260,22,2,150,"yellow"));
    this.addSquare(new Property("Coventry Street",27,260,22,2,150,"yellow"));
    this.addSquare(new Utility("Water Works",28,150,30,4,75));
    this.addSquare(new Property("Piccadilly",29,280,22,2,150,"yellow"));
    this.addSquare(new GoToJail("Go To Jail",30));
    this.addSquare(new Property("Regent Street",31,300,26,2,200,"green"));
    this.addSquare(new Property("Oxford Street",32,300,26,2,200,"green"));
    this.addSquare(new CommunityChest("Community Chest3",33,300));
    this.addSquare(new Property("Bond Street",34,320,28,2,200,"green"));
    this.addSquare(new RailRoad("Liverpool St Station",35,200,25,2,100));
    this.addSquare(new Chance("Chance3",36,200));
    this.addSquare(new Property("Park Lane",37,350,35,2,175,"dark blue"));
    this.addSquare(new Square("Super Tax",38));
    this.addSquare(new Property("Mayfair",39,400,50,2,200,"dark blue"));
  }
  
  private void addSquare(Square square) {
    this.squares.add(square);
    this.squareMap.put(square.name, square);
  }
  
  public Square getSquareByName(String name) {
    return squareMap.get(name);
  }
  
  /**
   *Square by index.
  */
  public Square getSquareByIndex(int index) {
    Square location = this.squares.get(index);
    //s is the square to be returned
    return location;
  }
  
  /**
   * This gets the destination of the new square.
   * @param startingSquare The square you start at
   * @param number amount to move forward
   * @return the destination square
   */
  public Square getDestinationSquare(Square startingSquare, int number) {  
    int currentIndex = this.squares.indexOf(startingSquare);
    int destinationIndex = (currentIndex + number) % this.squares.size();
    Square landingSquare = this.squares.get(destinationIndex);
    return landingSquare;
  }

}// End of Board class

