package app;

import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	
	public WeekReport(Reporting report) {
		
		this.report = report;
	}
	
	public void printWeekReport() {
		System.out.println("___________________________\n"
				+ report.getTitle() );
		
							
		
		
		
		
		report.numHoursSpent();
	}

	
	
	
	
}
