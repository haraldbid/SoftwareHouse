Feature: Add a new project
	Description: A new project is created
	Actors: Worker
	
	
Scenario: A new project is created
When a project with name "Project_test" is created
Given that a proctID is generated with the date and running number
And project has title "Project_test"
Then "Project_test" exists in array

Scenario: The start date is changed for at project
When a project with name "Project_test" is created
Given that a proctID is generated with the date and running number
And the start Date is changed
Then The project has a new ID with the new date.

