import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.PendingException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Stepdefs {
 // remove for testing

  Game testGame;

  private MonopolyDao persistence = new MonopolyDao();


  /** 
   * Always executed before any step definition.
   */ 
  @Before 
  public void beforeScenario() {
    testGame = new Game();
    testGame.name = "test-game-1";
  //  testGame.players.put(1, new Player(1, testBoard.square.get(0)));
   // testGame.players.put(2, new Player(2, testBoard.square.get(0)));
//   persistence.persistGame(testGame);
  }
  /** Always executed after a step definition.
   */
  @After
  public void afterScenario() {
    Board testGame = persistence.findGameByName("test-game-1");
    persistence.removeGame(testGame);
   // persistence.removeGame(persistence.findGameByName("test-board-2"));
  //  persistence.removeGame(persistence.findGameByName("test-board-3"));
  }

  // PLayer Movement
  /**
   * Set the player to start on the given square name.
   * @param playerName The player's piece.
   * @param squareName The square to start on.
   */
  @Given("^Player (.*) started on the (.*) square$")
  public void player_started_on(String playerName, String squareName) throws Throwable {
    Player player  = new Player(Board.Counters.valueOf(playerName.toUpperCase()));
    testGame.addPlayer(player);
    player.setPosition(testGame.board.getSquareByName(squareName));
  }
  
  @When("^Player (.*) rolls a (\\d+) and a (\\d+)$")
  public void player_rolls(String playerName, int dice1, int dice2) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    testGame.peformActionsAfterDiceRoll(player, dice1, dice2);
  }
  
  /**
   * Make sure the player is on a given square.
   * @param playerName The player name
   * @param squareName The square name
   */
  @Then("^Player (.*) should be on (.*)$")
  public void player_should_be_on(String playerName, String squareName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));  
    Square square = testGame.board.getSquareByName(squareName);
    assertEquals(player.getPosition(), square);
  }  
  
  @Then("^Player (.*) is in Jail$")
  public void player_is_in_Jail(String playerName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    assertTrue(player.jail);
  }
  
  @Then("^Player (.*) is not in Jail$")
  public void player_is_not_in_Jail(String playerName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    assertFalse(player.jail);
  }
  
  /**
   * Checks that the player gains money for passing GO.
   * @param playerName The player's name.
   * @param amt Amount to gain.
   */
  @Then("^Player (.*) should gain (\\d+) for passing Go$")
  public void player_pass_go(String playerName, int amt) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));  
    int oldMoney = player.getMoney();
    player.passGo();
    assertEquals(oldMoney + amt, player.getMoney()); 
  }
  
  /**
   * Checks the player gains the correct amount for landing on a chance.
   * @param playerName The player's name.
   * @param amt Amount to gain.
   * @param squareName The chance square to land on.
   */
  @Then("^Player (.*) should gain (\\d+) for landing on (.*)$")
  public void gain_on_chance(String playerName, int amt, String squareName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));  
    int oldMoney = player.getMoney();
    Square square = testGame.board.getSquareByName(squareName);
    if (square.getName().startsWith("Chance")) {
      Chance chance = (Chance) testGame.board.getSquareByName(squareName);
      player.addMoney(chance.getAmount());
    } else {
      CommunityChest chest = (CommunityChest) square;
      player.addMoney(chest.getReward());
    }
    assertEquals(oldMoney + amt, player.getMoney()); 
  } 
  
  /**
   * Checks the player gains the correct amount for landing on a chance.
   * @param playerName The player's name.
   * @param amt Amount to gain.
   * @param chanceSquare The chance square to land on.
   */
  @Then("^Player (.*) should lose (\\d+) for landing on (.*)$")
  public void landing_on_chance(String playerName, int amt, String chanceSquare) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));  
    int oldMoney = player.getMoney();
    Chance chance = (Chance) testGame.board.getSquareByName(chanceSquare);
    player.addMoney(chance.getAmount());
    assertEquals(oldMoney - amt, player.getMoney()); 
  }   
  
  @Given("^Player (.*) is Jailed$")
  public void player_is_Jailed(String playerName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    player.setJail(true);
  }

  @Given("^Player (.*) has (\\d+)$")
  public void player_has(String playerName,int amt) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    player.setMoney(amt);
  }
  
  /**
   * Set a player free from jail and subtracts £50.
   * @param playerName the name of the player
   */
  @When("^Player (.*) wants to leave Jail early$")
  public void player_Ship_wants_to_leave_jail_early(String playerName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    player.leaveJail();
  }

  @Then("^Player (.*) should have (\\d+)$")
  public void player_Ship_should_have(String playerName,int amt) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    assertEquals(player.getMoney(),amt);
  }
  
  
// saves initial game 
  @When("^we save the game as (.*)$")
  public void we_save_the_game_as(String saveGameName) throws Throwable {
    testGame.name = saveGameName;
    persistence.persistGame(testGame.board);
  }
  
  @Then("^the board has a name$")
  public void the_game_has_an_id() throws Throwable {
    System.out.println(testGame.board.id);
    assertTrue(testGame.name != "");
  }

 // load a saved game
  @Given("^we load the saved game (.+)$")
  public void we_load_the_saved_game(String gameName) throws Throwable {
    testGame.board = persistence.findGameByName(gameName);
  } 
    

  /**
   * sets the player to own a property.
   * @param playerName player name.
   * @param property property name.
   */
  @Given("^Player (.*) owns (.*)$")
  public void player_owns(String playerName, String property) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    Property prop = (Property) testGame.board.getSquareByName(property);
    prop.setOwner(player);
  }
  
  /**
   * Player pays rent to another player.
   * @param playerName1 first player.
   * @param fee amount owed.
   * @param playerName2 second player.
   */
  @Then("^Player (.*) owes (\\d+) rent to Player (.*)$")
  public void player_owes_rent(String playerName1,int fee,String playerName2) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName1.toUpperCase()));
    Player player2 = testGame.getPlayer(Board.Counters.valueOf(playerName2.toUpperCase()));
    player2.addMoney(fee);
    player.subtractMoney(fee);
    assertNotEquals(player.getMoney(), player2.getMoney());   
  }
  
  /**
   * Player buys a property.
   * @param playerName name of the player.
   * @param squareName name of the square.
   * @param amt price of square.
   */
  @When("^Player (.*) buys (.*) for (\\d+)$")
  public void player_buys(String playerName, String squareName, int amt) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    BuyableSquare prop = (BuyableSquare) testGame.board.getSquareByName(squareName);
    prop.setOwner(player);
    player.subtractMoney(amt);
  }

  @Then("^Player (.*) should own (.*)$")
  public void player_should_own(String playerName, String squareName) throws Throwable {
    BuyableSquare prop = (BuyableSquare) testGame.board.getSquareByName(squareName);
    assertEquals(prop.getOwner(),playerName);
  }

  @Then("^Player (.*) is bankrupt$")
  public void player_Car_is_bankrupt(String playerName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    assertTrue(player.bankrupt);
  }
  
  /**
   * Player can buy a house when they own a colour group.
   * @param playerName The name of the player.
   * @param amt Price of the house.
   * @param squareName Property to buy the house on.
   */
  @When("^Player (.*) buys a house for (\\d+) on (.*)$")
  public void player_buys_a_house(String playerName, int amt, String squareName) throws Throwable {
    Player player = testGame.getPlayer(Board.Counters.valueOf(playerName.toUpperCase()));
    Property prop = (Property) testGame.board.getSquareByName(squareName);
    prop.addHouse(1);
    player.subtractMoney(amt);
  }

  @Then("^(.*) has (\\d+) house$")
  public void old_Kent_Road_has_house(String squareName, int amt) throws Throwable {
    Property prop = (Property) testGame.board.getSquareByName(squareName);
    assertEquals(prop.getNumOfHouses(), amt);
  }



}



