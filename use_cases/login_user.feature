Feature: login user
	Description: the user logs in
	Actors: worker
	
Scenario: User logs in
	Given that no user is logged in
	And user enters ID
	Then user is logged in