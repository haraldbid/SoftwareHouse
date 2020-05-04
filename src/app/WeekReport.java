package app;

import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	
	public WeekReport(Reporting report) {
		
		this.report = report;
	}
	
	public void printWeekReport() {
		
		int remainingHours = report.getExpectedNumWorkingHours()-report.numHoursSpent();
		
		System.out.println(
				  "________________________________\n"
				+ report.getClass() +  " ID: "+ report.getTitle() +"\n"
				+ "Working hours spent: " + report.numHoursSpent() + "\n"
				+ "Working hours remaining: " + remainingHours + "\n"
				+ "________________________________");
	}

	
	
	
}
