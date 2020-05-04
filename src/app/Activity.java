package app;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import designPatterns.Date;

public class Activity {

	private int activityID;
	private String title;
	private Date startDate,endDate;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	private List<TimeSheet> timesheets = new ArrayList<TimeSheet>();
	
	
	public Activity(String title,Date startD, Date endD) {
		this.title = title;
		this.startDate = startD;
		this.endDate = endD;

	}

	public void inputAssistance(Worker worker, Worker helper,int hours, int minutes, Date date)  {
		if(hours < 0 || minutes < 0)
			throw new IllegalArgumentException("Only positive work time");
		if(this.searchWorker(worker.getID()) == null)
			throw new IllegalArgumentException("Worker is not assigned to activity");
		// Maybe throw exception if helper doesnt exist in software house?

		TimeSheet t = new TimeSheet(worker,date);
		t.addtimeWorked(hours, minutes);
		t.setHelper(helper);
	}
	
	public void assignWorker(Worker worker) {
		if(searchWorker(worker.getID()) == null)
			this.listWorkersActivity.add(worker);
		else
			throw new IllegalArgumentException("Worker is already assigned");
	}

	public void inputWorkTime(Worker worker, int hours, int minutes, Date date) {
		if(hours < 0 || minutes < 0)
			throw new IllegalArgumentException("Only positive work time");
		if(this.searchWorker(worker.getID()) == null)
			throw new IllegalArgumentException("Worker is not assigned to activity");
		TimeSheet time = new TimeSheet(worker,date);
		time.addtimeWorked(hours, minutes);
		timesheets.add(time);

	}
	
	public List<TimeSheet> getTimeSheets(){
		return this.timesheets;
	}
	public List<Worker> getWorkersAssigned(){
		return this.listWorkersActivity;
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


	public void setStartDate(Date date) {
		startDate = date;
	}

	public void setEndDate(Date date) {
		endDate = date;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}


}
