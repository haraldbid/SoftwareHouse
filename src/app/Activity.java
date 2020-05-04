package app;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import designPatterns.Reporting;

public class Activity implements Reporting {

	private int activityID;
	private static int activityIDCount = 0;
	private String title;
	private int expectedWorkingHours;
	private int[] numHoursSpent;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	private Calendar startDate = new GregorianCalendar();
	private Calendar endDate = new GregorianCalendar();

	private List<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
	private List<WeekReport> weekReports = new ArrayList<WeekReport>();
	
	public Activity(String title) {
		this.title = title;
		this.activityID = activityIDCount + 1;
	}

//	TODO: HELPER SKAL SELV REGISTRERE TIDEN :)
	public void inputAssistance(Worker worker, Worker helper,int hours, int minutes) {
		if(hours < 0 || minutes < 0)
			throw new IllegalArgumentException("Only positive work time");
		if(this.searchWorker(worker.getID()) == null)
			throw new IllegalArgumentException("Worker is not assigned to activity");
		// Maybe throw exception if helper doesnt exist in software house?

		TimeSheet t = new TimeSheet(worker);
		t.addtimeWorked(hours, minutes);
		t.setHelper(helper);
	}
	
	public void assignWorker(Worker worker) {
		this.listWorkersActivity.add(worker);
	}

	public void inputWorkTime(Worker worker, int hours, int minutes) {
		if(hours < 0 || minutes < 0)
			throw new IllegalArgumentException("Only positive work time");
		if(this.searchWorker(worker.getID()) == null)
			throw new IllegalArgumentException("Worker is not assigned to activity");
		TimeSheet time = new TimeSheet(worker);
		time.addtimeWorked(hours, minutes);
		timeSheets.add(time);

	}
	public Worker searchWorker(String ID) {
		for (Worker worker : listWorkersActivity) {
			if(worker.getID().equals(ID))
				return worker;
		}
		return null; // Throw exception?
	}
	public String getTitle() {
		return this.title;
	}


	public void setStartDate(Calendar date) {
		startDate = date;
	}
	
	public void setEndDate(Calendar date) {
		endDate = date;
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
	public int[] numHoursSpent() {
		
		Calendar cal = new GregorianCalendar();
		//1. entry contains information about total hours spent on activity, 2. entry for the week
		int[] numHoursSpent = {0,0}; 
		
		for (TimeSheet t : timeSheets) {
			numHoursSpent[0] += t.getMinutesWorked();
			
			if (cal.get(Calendar.WEEK_OF_YEAR) == t.getDate().getWeekNumber()) {
				numHoursSpent[1] += t.getMinutesWorked();
			}
		}
		
		this.numHoursSpent = numHoursSpent;
		
		return numHoursSpent;
	}
	
	public void generateWeekReport() {
		WeekReport report = new WeekReport(this);
		
		weekReports.add(report);
		
		report.printWeekReport();
	}

	@Override
	public int getID() {
		
		return this.activityID;
	}

	@Override
	public int getExpectedWorkingHours() {
		return this.expectedWorkingHours;
	}

}
