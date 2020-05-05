package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	int[] numHoursSpent;
	int expectedWorkingHours;
	int remainingHours;
	String type;
	ArrayList <Activity> activities;
	Calendar cal = new GregorianCalendar();
	int test;
	
	
	
	public WeekReport(Reporting report) {
		
		this.report = report;
		this.numHoursSpent = report.numHoursSpent();
		this.expectedWorkingHours = report.getExpectedWorkingHours();
		this.remainingHours = this.expectedWorkingHours-this.numHoursSpent[1];
		
		if (report instanceof Activity)
			type = "Activity";
		if (report instanceof Project) {
			type = "Project";
			this.activities = ((Project) report).getActivities();
		}
			
		
	}
	
	public void printWeekReport() {
		
		System.out.println(
				  "________________________________\n"
				+ type +  " title: "+ report.getTitle() +"\n"
				+ "-------------------------------- \n"
				+ "Total working hours spent: " + numHoursSpent[0] + "\n"
				+ "Total working hours remaining: " + remainingHours + "\n"
				+ "Week " + cal.get(Calendar.WEEK_OF_YEAR) + " working hours: " + numHoursSpent[1] + "\n"
				+ "________________________________" + "\n");
		
		if (report instanceof Project) {
			System.out.println("LIST OF INCOMPLETE ACITIVITIES: ");
			for(Activity a : activities) {
				System.out.println(a.getTitle());
			}
			System.out.println("________________________________");
		}
		
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
