public class CommunityChest extends Square {

  // Data members
  private int reward;

  // Constructor
  /**
   * CommunityChest Constructor.
   */
  public CommunityChest(String name, int location, int amount) {
    super(name, location);
    // code for generating reward amount.
    this.reward = amount; // Temporally set at 100
  }

  // Instance methods
  public int getReward() {
    return reward;
  }

}// End of CommunityChest class
