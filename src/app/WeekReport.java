package app;

import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	
	public WeekReport(Reporting report) {
		
		this.report = report;
	}
	
	public void printWeekReport() {
		System.out.println(
				  "________________________________\n"
				+ report.getClass() +  " ID: "+ report.getTitle() +"\n"
				+ "Working hours spent: " + report.numHoursSpent() + "\n"
				+ "________________________________");
		
							
	}

	
	
	
	
}
