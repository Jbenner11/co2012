import java.util.HashMap;
import java.util.Map;

public class Game {

  String name;
  Board board = new Board();

  Map<Board.Counters, Player> players = new HashMap<>();
    
  public void addPlayer(Player player) {
    players.put(player.playerPiece, player);
  }

  public Player getPlayer(Board.Counters counter) {
    return players.get(counter);
  }

  /**
   * Checks if the player landed on the go to jail square, and sends them to jail if true.
   * Also checks if player rolled 3 doubles and sends them to jail if true.
   * @param player the player to be moved
   * @param die1 the value of the first dice
   * @param die2 the value of the second dice
   */
  public void peformActionsAfterDiceRoll(Player player, int die1, int die2) {  
    if (die1 == die2) {
      player.doublesRolled++;      
    }
    
    if (player.jail) {
      if (die1 == die2 || player.turnsInJail == 3) {
        player.leaveJail();
      } else {
        player.turnsInJail++;
        return;
      }
      
    }
    
    Square locationToMoveplayerTo = 
        this.board.getDestinationSquare(player.getPosition(), die1 + die2);
    
    // Did player roll 3 doubles
    if (player.doublesRolled == 3) {
      locationToMoveplayerTo = this.board.getSquareByName("Jail");
      player.setJail(true);
      player.doublesRolled = 0;
    }
    
      // Is there a teleport happening.
    if (locationToMoveplayerTo == this.board.getSquareByName("Go To Jail")) {
      locationToMoveplayerTo = this.board.getSquareByName("Jail");
      player.setJail(true);
    }
    
    player.setPosition(locationToMoveplayerTo);
  }
}