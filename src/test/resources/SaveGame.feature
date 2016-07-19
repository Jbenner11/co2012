Feature: Save and Load Game

	Scenario: Persist a game that has started
        Given Player Car started on the Go square
          And Player Hat started on the Go square
        When Player Car rolls a 2 and a 4
        And Player Hat rolls a 1 and a 3
        Then Player Car should be on Kings Cross Station
         And Player Hat should be on Whitechapel Road
        When we save the game as test-game-1
        Then the board has a name
		
	Scenario: Load a saved game 
		Given we load the saved game test-board-1
