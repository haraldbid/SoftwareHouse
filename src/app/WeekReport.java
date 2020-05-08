package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import designPatterns.Date;
import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	int[] numMinSpent;
	int expectedWorkingHours;
	int remainingHours;
	String reportType;
	ArrayList<Activity> activities;
	Calendar cal = new GregorianCalendar();
	Date date;

	public WeekReport(Reporting report, Date date) {
		this.report = report;
		this.numMinSpent = report.numMinSpent(date);
		this.expectedWorkingHours = report.getExpectedWorkingHours();
		this.remainingHours = (report.getExpectedWorkingHours() - this.numMinSpent[0]/60);
		this.date = date;

		if (report instanceof Activity)
			reportType = "Activity";
		if (report instanceof Project) {
			reportType = "Project";
			this.activities = ((Project) report).getActivities();
		}
	}

	public void printWeekReport() {

		int criticalTime = 1;
		
		System.out.println("________________________________\n" 
				+ reportType + " title: " + report.getTitle() + "\n"
				+ "Week: " + date.getWeekNumber() + " Year: " + date.getYear() +"\n"
				+ "-------------------------------- \n" 
				+ "Expected working hours: " + this.expectedWorkingHours + "\n"
				+ "Total working hours spent: " + this.numMinSpent[0]/60 + "\n"
				+ "Total working hours remaining: " + remainingHours + "\n" 
				+ "Week " + date.getWeekNumber() + " working hours: " + this.numMinSpent[1]/60 + "\n" 
				+ "________________________________" + "\n");

//		Print activities that are critical (1 week left)
		if (report instanceof Project) {
			System.out.println("LIST OF CRITICAL ACITIVITIES: ");
			for (Activity a : activities) {
				if (a.getEndDate().getWeekNumber() == date.getWeekNumber()+criticalTime
						&& a.getEndDate().getYear() == date.getYear()) {
					System.out.println(a.getTitle());
				}
			}
			System.out.println("________________________________");
		}
	}
	
	public Date getDate() {
		return this.date;
	}

}
