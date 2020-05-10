# Nicklas

Feature: User login
	Description: The user logs in
	Actors: Worker
	
Scenario: User succesfully logs in
	Given that no user is logged in
	And user "aaa" enters ID "aaa"
	Then user is logged in
Scenario: User unsuccesfully log in
	Given that no user is logged in
	And user "bbb" enters ID "aaa"
	Then no user is logged in