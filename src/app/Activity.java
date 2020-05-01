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


	private List<TimeSheet> timesheets = new ArrayList<TimeSheet>();
	public Activity(String title) {
		this.title = title;

	}

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
		timesheets.add(time);

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
