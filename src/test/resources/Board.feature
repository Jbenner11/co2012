Feature: Movements

    Scenario: A player can make a single move
   		Given Player Boot started on the Bow Street square
		When Player Boot rolls a 2 and a 1
		Then Player Boot should be on Vine Street

    Scenario: A player rolls doubles
		Given Player Thimble started on the Go square
		When Player Thimble rolls a 2 and a 2
    		And Player Thimble rolls a 1 and a 3
    	Then Player Thimble should be on Chance1
   
	Scenario: A player lands on the Go To Jail square
		Given Player Car started on the Fleet Street square
        When Player Car rolls a 5 and a 2
        Then Player Car should be on Jail
        	And Player Car is in Jail
        	
    Scenario: A player passes Go
		Given Player Hat started on the Park Lane square
		When Player Hat rolls a 2 and a 3
		Then Player Hat should be on Community Chest1
			And Player Hat should gain 200 for passing Go
   
    Scenario: A player moves onto Jail but is not in Jail
		Given Player Ship started on the Kings Cross Station square
		When Player Ship rolls a 2 and a 3
		Then Player Ship should be on Jail
			And Player Ship is not in Jail

    Scenario: Players can take turns to move
        Given Player Car started on the Go square
          And Player Hat started on the Go square
        When Player Car rolls a 2 and a 4
        And Player Hat rolls a 1 and a 3
        Then Player Car should be on Kings Cross Station
         And Player Hat should be on Whitechapel Road    

    Scenario: A player lands on a chance square and gains money
		Given Player Dog started on the Old Kent Road square
		When Player Dog rolls a 1 and a 5
		Then Player Dog should be on Chance1
			And Player Dog should gain 100 for landing on Chance1
			
	Scenario: A player lands on a chance square and loses money
		Given Player Thimble started on the Marylebone Station square
		When Player Thimble rolls a 2 and a 5
		Then Player Thimble should be on Chance2
			And Player Thimble should lose 100 for landing on Chance2
			
	Scenario: A player rolls 3 doubles
		Given Player Car started on the Go square
		When Player Car rolls a 1 and a 1
    		And Player Car rolls a 2 and a 2
    		And Player Car rolls a 3 and a 3
    	Then Player Car should be on Jail
    		And Player Car is in Jail	
			
    Scenario: A player lands on a community chest
		Given Player Ship started on the Electric Company square
		When Player Ship rolls a 3 and a 2
		Then Player Ship should be on Community Chest2
			And Player Ship should gain 50 for landing on Community Chest2
			
    Scenario: A player rolls doubles in jail
		Given Player Dog started on the Jail square
			And Player Dog is Jailed
		When Player Dog rolls a 2 and a 2
		Then Player Dog should be on Northumberland Avenue
			And Player Dog is not in Jail
			
    Scenario: A player wishes to leave jail early
		Given Player Ship started on the Jail square
			And Player Ship is Jailed
			And Player Ship has 500
		When Player Ship wants to leave Jail early
		Then Player Ship should have 450
			And Player Ship is not in Jail

   Scenario: A player is in Jail for 1 turn
		Given Player Dog started on the Jail square
			And Player Dog is Jailed
		When Player Dog rolls a 5 and a 2
		Then Player Dog should be on Jail
			And Player Dog is in Jail
		
    Scenario: A player has been in jail for 3 turns
		Given Player Boot started on the Jail square
			And Player Boot is Jailed
			And Player Boot has 600
		When Player Boot rolls a 4 and a 5
			And Player Boot rolls a 2 and a 3
			And Player Boot rolls a 1 and a 2 
			And Player Boot rolls a 2 and a 4
		Then Player Boot should be on Bow Street 
			And Player Boot should have 550
			And Player Boot is not in Jail

