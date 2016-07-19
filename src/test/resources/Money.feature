Feature: Things dealing with  money

    Scenario: A player lands on an owned property
    	Given Player Boot started on the Go square
			And Player Boot owns Old Kent Road
			And Player Car started on the Park Lane square
			And Player Car has 200 
			And Player Boot has 200 
    	When Player Car rolls a 2 and a 2
    	Then Player Car should be on Old Kent Road
    		And Player Car owes 2 rent to Player Boot
    		And Player Car should have 198
    		And Player Boot should have 202
		
	Scenario: Player buys a property
		Given Player Ship started on the Go square
			And Player Ship has 500
		When Player Ship rolls a 5 and a 2
		Then Player Ship should be on The Angel Islington
		When Player Ship buys The Angel Islington for 100
		Then Player Ship should have 400
			And Player Ship should own The Angel Islington

			
	Scenario: Player becomes bankrupt
		Given Player Hat started on the Go square
			And Player Hat owns Old Kent Road
			And Player Car started on the Park Lane square
			And Player Car has 1 
    	When Player Car rolls a 2 and a 2
    	Then Player Car should be on Old Kent Road
    		And Player Car owes 2 rent to Player Hat
    		And Player Car is bankrupt
    		
    Scenario: Player can buy houses
    	Given Player Thimble started on the Go square
    		And Player Thimble has 500
    		And Player Thimble owns Old Kent Road
    		And Player Thimble owns Whitechapel Road
    	When Player Thimble buys a house for 10 on Old Kent Road
    	Then Old Kent Road has 1 house
    		And Player Thimble should have 490
    		
    		