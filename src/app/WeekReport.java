package app;

import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	int[] numHoursSpent;
	int expectedWorkingHours;
	int remainingHours;
	String type;
	
	
	
	public WeekReport(Reporting report) {
		
		this.report = report;
		this.numHoursSpent = report.numHoursSpent();
		this.expectedWorkingHours = report.getExpectedWorkingHours();
		this.remainingHours = this.expectedWorkingHours-this.numHoursSpent[1];
		
		if (report instanceof Activity)
			type = "Activity";
		if (report instanceof Project)
			type = "Project";
	}
	
	public void printWeekReport() {
		
		
		
		System.out.println(
				  "________________________________\n"
				+ type +  " title: "+ report.getTitle() +"\n"
				+ "Working hours spent: " + report.numHoursSpent() + "\n"
				+ "Working hours remaining: " + remainingHours + "\n"
				+ "________________________________");
	}
	
	public int[] getNumHoursSpent() {
		return this.numHoursSpent;
	}
	public int getExpectedWorkingHours() {
		return this.expectedWorkingHours;
	}
	public int getRemainingHours() {
		return this.remainingHours;
	}

	
	
	
}
