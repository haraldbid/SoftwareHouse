package app;

import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	int[] numHoursSpent;
	int expectedWorkingHours;
	int remainingHours;
	
	
	
	public WeekReport(Reporting report) {
		
		this.report = report;
		this.numHoursSpent = report.numHoursSpent();
		this.expectedWorkingHours = report.getExpectedWorkingHours();
		this.remainingHours = this.expectedWorkingHours-this.numHoursSpent[1];
	}
	
	public void printWeekReport() {
		
		
		
		System.out.println(
				  "________________________________\n"
				+ report.getClass() +  " ID: "+ report.getTitle() +"\n"
				+ "Working hours spent: " + report.numHoursSpent() + "\n"
				+ "Working hours remaining: " + remainingHours + "\n"
				+ "________________________________");
	}

	
	
	
}
