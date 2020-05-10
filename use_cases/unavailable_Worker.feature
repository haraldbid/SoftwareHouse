# Markus

Feature: Unavailable worker
	Description: Removes a worker from activities, if he is unavailable
	Actors: Worker
	
Scenario: Succesfully removed activity during unavailablity
Given that a worker is assigned to an activity
And the activity is while the worker is unavailable
And the worker becomes unavailable
Then the worker is unassigned from the activity


Scenario: no activity removed during unavailablity
Given that a worker is assigned to an activity
And the activity is not while the worker is unavailable
And the worker becomes unavailable
Then the worker is still assigned to the activity