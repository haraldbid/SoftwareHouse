package app;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Activity {

	private int activityID;
	private String title;
	private List<Worker> listWorkersActivity;
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
	
	
}
