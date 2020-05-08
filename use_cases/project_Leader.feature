Feature: Assign project leader
	Description: A worker is assigned as project leader
	Actors: Worker
	
Scenario: Assign worker as projectleader without leader
Given that a project "Project_test" exist
And a worker with id "abc" exist
And project has no project leader
Then "abc" is assigned as projectleader


Scenario: Assign worker as projectleader with leader
Given that a project "Project_test" exist
And a worker with id "abc" exist
And project has a project leader
And "abc" is assigned as projectleader
Then Error message "Project has leader assigned" is displayed