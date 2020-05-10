# Harald

Feature: add time to activity
	Description: time worked is added to activity
	Actors: worker
	
Scenario: add time to activity
Given that an activity "activity1" exist
And a worker "aa" exist
And worker "aa" is assigned to activity
Then timesheet with "activity1" and "worker" is added to list in "activity1"

Scenario: add time to activity
Given that an activity "activity1" exist
And a worker "bb" exist
And worker "bb" is not assigned to activity
When time is added for worker "bb"
Then the error message: "Worker is not assigned to activity" is displayed