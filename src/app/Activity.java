package app;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import designPatterns.Reporting;

public class Activity implements Reporting {

	private int activityID;
	private String title;
	private int expectedWorkingHours;
	private int numHoursSpent;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	private Calendar startDate = new GregorianCalendar();
	private Calendar endDate = new GregorianCalendar();
	private TimeSheet timeSheet;
	
	public Activity() {
		
	}
	
	
	public void requestAssistance(Worker worker) {
		
		
	
	}
	

	public void halfHoursWorked(int halfHours) {
//		timeSheet.
	}

	public void setStartDate(int year, int month, int day) {
		startDate.set(year, month, day);
	}
	
	public void setEndDate(int year, int month, int day) {
		endDate.set(year, month, day);
	}
	
	public Calendar getStartDate() {
		return this.startDate;
	}
	
	public Calendar getEndDate() {
		return this.endDate;
	}
	
	public void setExpectedWorkingHours(int expectedWorkingHours) {
		this.expectedWorkingHours = expectedWorkingHours;
	}


//	TODO: Calculate num of hours spent from timesheets
	@Override
	public int numHoursSpent() {
		return 3;
		
	}
	
	
}
