Feature: Twitter API

	Background:
    	Given Twitter instance
 
 	@showtime
	Scenario: Follow & un-follow the followers of AlanSanie
		Given user "AlanSanie"
		When request my credentials
		 And request the list of followers
		 And get user profile for each follower
		 And examine my friendships with the followers 
		 And follow and un-follow each user except the protected ones
		Then report the protected users

	
	@discovery
	Scenario: API calls discovery
		Given user "AlanSanie"
		When request my credentials
		 And request the list of followers
		 And examine my friendship with "AlanSanie"
		 And follow and un-follow "AlanSanie"
