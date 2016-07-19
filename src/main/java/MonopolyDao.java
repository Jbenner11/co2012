

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MonopolyDao {

  Connection connection;
  
  /**
  *Constructor.
  */
  public MonopolyDao() {
    try {
      Class.forName("com.mysql.jdbc.Driver" );
      connection = DriverManager.getConnection("jdbc:mysql://" + System.getProperty("db_host") 
      + ":" + System.getProperty("db_port")  
      + "/" + System.getProperty("db_name")  
      + "?user=" + System.getProperty("db_user")  
      + "&password=" + System.getProperty("db_pass"));
    } catch (Exception e) {
      System.err.println("ERROR: failed to get database connection.");
      e.printStackTrace();
    }
  }
  /**
  *Update player.
  */
  public void persistPlayer(Player player, Board board) {
    if (player.id != 0) {
      this.updatePlayer(player, board);
    } else {
      this.addPlayer(player, board);
    }
  }
  

  private void updatePlayer(Player player, Board board) {
    
    try {
      String updateGame = "UPDATE player SET piece = '" + player.getPlayerPiece() 
          + "', square = " + player.position.getLocation()
          + ", game = " + board.id 
          + ", money = " + player.getMoney()
          + " where `id` = " + player.id;  
      Statement statement = connection.createStatement();
      statement.executeUpdate(updateGame);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Add the game to the database.
   * @param board The board to add the player to.
   * @param player the player to add
   */
  private void addPlayer(Player player, Board board) {
    
    try {
      String insertPlayer = "INSERT INTO player (piece, square, game, money) values ('" 
          + player.getPlayerPiece() + "', " + player.position.getLocation() 
          +  ", " + board.id + "," + player.getMoney() + ")";
      
      Statement statement = connection.createStatement();
      statement.executeUpdate(insertPlayer, Statement.RETURN_GENERATED_KEYS);
      ResultSet keys = statement.getGeneratedKeys();
      
      while (keys.next()) {  
        player.id = keys.getInt(1);
      } 
      keys.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Final all of the players for a given game.
   * @param board The board to find the players for.
   * @return A map of their name to the player (as board requires them).
   */
  public Map<Board.Counters, Player> findPlayersByGame(Board board) {
    
    Map<Board.Counters, Player> players = new HashMap<>();
    Player player = new Player();
    try {
      
      String selectPlayers = "SELECT id, piece, square FROM player WHERE game =" + board.id;
      
      Statement statement = connection.createStatement();
      ResultSet playerResults = statement.executeQuery(selectPlayers);
      
      while (playerResults.next()) {
        player.setPlayerPiece(playerResults.getString("piece"));
        player.id = playerResults.getInt("id");
        player.setPosition(board.getSquareByIndex(playerResults.getInt("square")));
        player.setMoney(playerResults.getInt("money"));
        findPropertyByPlayer(player);
        players.put(player.playerPiece, player);
      }
      
    } catch (SQLException e) {  
      e.printStackTrace();
    }
    
    return players;
  }
  
  /*************
   * Board Stuff.
   *************/
  
  /**
   * Find a given with a given name.
   * @param name the name to search for.
   * @return A game if it was found.
   */
  public Board findGameByName(String name) {
    
    Board result = null;
    
    try {
      
      String selectGame = "SELECT id FROM game WHERE name ='" + name + "'";
      
      Statement statement = connection.createStatement();
      ResultSet gameResults = statement.executeQuery(selectGame);
      
      if (gameResults.next()) {

        result = new Board();
        result.id = gameResults.getInt("id");
        result.name = name;
        this.findPlayersByGame(result); 
      }
      
    } catch (SQLException e) {  
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Update or add a game to the database depending on whether it is already there.
   * @param board The board.
   */
  public void persistGame(Board board) {
    if (board.id != 0) {
      this.updateGame(board);
    } else {
      this.addGame(board);
    }
  }
  
  /**
   * Update a game in the database
   * @param board The board to update.
   */
  private void updateGame(Board board) {
    

    try {
      String updateGame = "UPDATE game SET name = '" + board.name 
          + "' WHERE `id` = " + board.id;      
      Statement statement = connection.createStatement();
      statement.executeUpdate(updateGame);
      

      
      

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Add the game to the database.
   * @param board The board to add.
   */
  private void addGame(Board board) {
    
    try {
      String insertGame = "INSERT INTO game (name) values ('" + board.name + "')";
      
      Statement statement = connection.createStatement();
      statement.executeUpdate(insertGame, Statement.RETURN_GENERATED_KEYS);
      ResultSet keys = statement.getGeneratedKeys();
      
      while (keys.next()) {  
        board.id = keys.getInt(1);
      } 
      keys.close();
      
      this.updateGame(board);
      
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   *Properties.
   */
  public void persistProperties(BuyableSquare square) {
    if (square.id != 0) {
      this.updateProperties(square);
    } else {
      this.addProperties(square);
    }
  }
  
  private void updateProperties(BuyableSquare square) {
    try {
      String updateProperties = "UPDATE properties SET player = '" + square.getOwner()
          + ", property = " + square.getProperty()
          + ", location = " + square.getLocation()
          + ", cost = " + square.getPrice()
          + ", rent = " + square.getRent()
          + ", multiplier = " + square.getMultiplier()
          + ", mortgage = " + square.getMortgage()
          + "' WHERE `id` = " + square.id;      
      Statement statement = connection.createStatement();
      statement.executeUpdate(updateProperties);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  private void addProperties(BuyableSquare square) {
    
    try {
      String insertProperties = "INSERT INTO properties ( player, property, location,"
           + "cost, rent, multiplier, mortgage)"
           + "values ('" 
           + square.getOwner() 
           + "," + square.getProperty() 
           + "," + square.getLocation()
           + "," + square.getPrice()
           + "," + square.getRent()
           + "," + square.getMultiplier()
           + "," + square.getMortgage() + "')";
      Statement statement = connection.createStatement();
      statement.executeUpdate(insertProperties, Statement.RETURN_GENERATED_KEYS);
      ResultSet keys = statement.getGeneratedKeys();
      
      while (keys.next()) {  
        square.id = keys.getInt(1);
      } 
      keys.close();
      
      this.updateProperties(square);
      
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  /**
   *Find player properties.
   */
  public BuyableSquare findPropertyByPlayer(Player player) {
    
    BuyableSquare result = null;
    
    try {
      
      String selectProperty = "SELECT property FROM properties WHERE player ='" 
          + player.getPlayerPiece() + "'";
      
      Statement statement = connection.createStatement();
      ResultSet propertiesResults = statement.executeQuery(selectProperty);
      
      while (propertiesResults.next()) {

        result = new BuyableSquare( propertiesResults.getString("property"),
               propertiesResults.getInt("location"),
               propertiesResults.getInt("cost"),
               propertiesResults.getInt("rent"),
               propertiesResults.getInt("multiplier"),
               propertiesResults.getInt("mortgage"));
        result.setOwner(player);
      }
      
    } catch (SQLException e) {  
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Delete a game from the database.s
   * @param testBoard The board to delete
   */
  public void removeGame(Board testBoard) {
    
    if (testBoard != null) {      
      try {
        String deleteGame = "DELETE FROM game WHERE id = " + testBoard.id;
        Statement statement = connection.createStatement();
        statement.executeUpdate(deleteGame);
        
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }  
  }
}