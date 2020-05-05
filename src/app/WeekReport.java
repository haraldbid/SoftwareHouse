package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import designPatterns.Date;
import designPatterns.Reporting;

public class WeekReport {

	Reporting report;
	int[] numHoursSpent;
	int expectedWorkingHours;
	int remainingHours;
	String reportType;
	ArrayList<Activity> activities;
	Calendar cal = new GregorianCalendar();
	Date date;

	public WeekReport(Reporting report, Date date) {

		this.report = report;
		this.numHoursSpent = report.numHoursSpent(date);
		this.expectedWorkingHours = report.getExpectedWorkingHours();
		this.remainingHours = this.expectedWorkingHours - this.numHoursSpent[1];
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
				+ "-------------------------------- \n" 
				+ "Total working hours spent: " + numHoursSpent[0] + "\n"
				+ "Total working hours remaining: " + remainingHours + "\n" 
				+ "Week " + cal.get(Calendar.WEEK_OF_YEAR)
				+ " working hours: " + numHoursSpent[1] + "\n" + "________________________________" + "\n");

//		TODO: Print activities that are critical (1 week left)
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

	public int[] getNumHoursSpent() {
		return this.numHoursSpent;
	}

	public int getExpectedWorkingHours() {
		return this.expectedWorkingHours;
	}

	public int getRemainingHours() {
		return this.remainingHours;
	}
	
	public Date getDate() {
		return this.date;
	}

}
