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
		this.expectedWorkingHours = report.getExpectedWorkingHours()/60;
		this.remainingHours = (report.getExpectedWorkingHours() - this.numMinSpent[0]/60);
		this.date = date;

		if (report instanceof Activity)
			reportType = "Activity";
		if (report instanceof Project) {
			reportType = "Project";
			this.activities = ((Project) report).getActivities();
		}

	}
	public void printWorkerWeek(Worker worker, Date date) {
		System.out.println("---- Time worker for "+ worker.getID() + ", week:"+date.getWeekNumber()+"----");
		for (Activity activity : worker.getListActivitys()) {
			System.out.println("Activity: "+activity.getTitle());
			System.out.println("--------------------");
			int sum_h = 0;
			int sum_m = 0;
			for (TimeSheet time : activity.getTimeSheets()) {
				if(time.getWorker() == worker && time.getDate().getWeekNumber() == date.getWeekNumber() && time.getDate().getWeekNumber() == date.getWeekNumber()) {
					sum_h += time.getHoursWorked();
					sum_m += time.getMinutesInputed();		
				}
				System.out.println(sum_h+" Hours, "+sum_m+" minutes worked");
			}
			System.out.println("--------------------");
		}
	}

	public void printWeekReport() {

		int criticalTime = 1;
		
		System.out.println("________________________________\n" 
				+ reportType + " title: " + report.getTitle() + "\n"
				+ "Week: " + date.getWeekNumber() + " Year: " + date.getYear() +"\n"
				+ "-------------------------------- \n" 
				+ "Total working hours spent: " + numMinSpent[0]/60 + "\n"
				+ "Total working hours remaining: " + remainingHours + "\n" 
				+ "Week " + date.getWeekNumber() + " working hours: " + numMinSpent[1]/60 + "\n" 
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

//	public int[] getNumMinSpent() {
//		return this.numMinSpent;
//	}

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
