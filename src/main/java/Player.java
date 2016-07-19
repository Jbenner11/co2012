import java.util.ArrayList;

public class Player {

  // Data Members
  private int money;
  Board.Counters playerPiece;
  Square position;
  boolean jail;
  int id;
  ArrayList<BuyableSquare> purchasedSquare = new ArrayList<>();
  int doublesRolled;
  int turnsInJail;
  boolean bankrupt;
 
  // Constructors
  /**
   * Argumentless constructor.
   */
  public Player() {
    money = 200;
    playerPiece = null;
    position = null;
    jail = false;
    int doublesRolled = 0;
    turnsInJail = 0;
    bankrupt = false;
  }
  
  /**
   * Player Constructor with values.
   */
  public Player(Board.Counters playerPiece) {
    money = 200;
    this.playerPiece = playerPiece;
    position = null;
    jail = false;
    doublesRolled = 0;
    turnsInJail = 0;
    bankrupt = false;
  }

  // Instance methods
  public void setPosition(Square newPosition) {
    position = newPosition;
  }
  
  public Square getPosition() {
    return position;
  }
  
  public void setPlayerPiece(String piece) {
    this.playerPiece = Board.Counters.valueOf(piece.toUpperCase());
  }
  /**
   *Get Player Piece.
   */
  public String getPlayerPiece() { 
    String str = "";
    switch (this.playerPiece) {
      case DOG: str = "Dog"; break;
      case SHIP: str =  "Ship"; break;
      case CAR: str =  "Car"; break;
      case HAT: str =  "Hat"; break;
      case THIMBLE: str =  "Thimble"; break;
      case BOOT: str =  "Boot"; break;
      default: str =  ""; break;
    }
    return str;
  }

  public int getMoney() {
    return money;
  }
  
  public void setMoney(int amount) {
    money = amount;
  }
  
  public void addMoney(int amount) {
    this.money += amount;
  }

  /**
   * Subtracts money from player, goes bankrupt if player money - amount < 0.
   * @param amount amount to subtract
   */
  public void subtractMoney(int amount) {
    if (money - amount < 0) {
      money = 0;
      bankrupt = true;
    } else {
      this.money -= amount;
    }
  }

  public void passGo() {
    money += 200;
  }

  public int getDoubles() {
    return doublesRolled; 
  }
  
  public void setDoubles(int amount) {
    doublesRolled = amount;
  }
  
  public ArrayList getProperties() {
    return purchasedSquare;
  }

  /**
   *Purchase square.
   */
  public boolean purchaseSquare(BuyableSquare square) {
    if (square.getOwner() == null) {
      square.setOwner(this);
      purchasedSquare.add(square);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Rent paying.
   */
  public void payRent(int rentAmount, Player receiver) {
    this.subtractMoney(rentAmount);
    receiver.addMoney(rentAmount);
    // N.B. Haven't deal with if the player goes bankrupt.
  }

  /**
   * Jail Time Remaining.
   */
  public int remainingJailTime() {
    return 3 - turnsInJail;
  }

  public boolean jailCheck() {
    return jail;
  }
  

  public void setJail(boolean toggle) {
    jail = toggle;
  }
  
  public void leaveJail() {
    setJail(false);
    subtractMoney(50);
  }  
  
  /**
   * Trade Property.
   */
  public void tradeProperty(Property propertyGiven, Property propertyReceived, Player otherPlayer) {
    
  }
  
  public void sellProperty(Property property, int moneyOffered, Player receiver) {
      
  }



}// End of Player class
