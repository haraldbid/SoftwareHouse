# Martin

Feature: generate weekreports
	Description: a week report is generated
	Actors: worker
	
Scenario: request weekreport, when not existing
When a Activity is created and time is logged
And a weekreport is not created for week 12
And a weekreport for week 12 is requested
Then a weekreport is created for week 12


Scenario: request weekreport when existing
When a Activity is created and time is logged
And a weekreport for week 12 is requested
And a weekreport is created for week 12
Then the existing week report is returned


