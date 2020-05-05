package app;
import java.util.ArrayList;
import java.util.List;

import designPatterns.Date;
import designPatterns.Reporting;

public class Activity implements Reporting {

	private int activityID;
	private String title;
	private int expectedWorkingHours;
	private int[] numHoursSpent;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	private Date startDate;
	private Date endDate;

	private List<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
	private List<WeekReport> weekReports = new ArrayList<WeekReport>();
	
	public Activity(String title, Date startDate, Date endDate) {
		this.title = title;
		
		
	}

//	TODO: HELPER SKAL SELV REGISTRERE TIDEN :)
	public void inputAssistance(Worker worker, Worker helper,int hours, int minutes, Date date) {
		if(hours < 0 || minutes < 0)
			throw new IllegalArgumentException("Only positive work time");
		if(this.searchWorker(worker.getID()) == null)
			throw new IllegalArgumentException("Worker is not assigned to activity");
		// Maybe throw exception if helper doesnt exist in software house?

		TimeSheet t = new TimeSheet(worker,date);
		t.addtimeWorked(hours, minutes);
		t.setHelper(helper);
		this.timeSheets.add(t);
	}
	
	public void assignWorker(Worker worker) {
		if(searchWorker(worker.getID()) == null)
			this.listWorkersActivity.add(worker);
		else
			throw new IllegalArgumentException("Worker is already assigned");
	}

	public void inputWorkTime(Worker worker, int hours, int minutes, Date date) {
		if(hours < 0 || minutes < 0) // 1 || 2
			throw new IllegalArgumentException("Only positive work time");
		if(this.searchWorker(worker.getID()) == null) // Whitebox 3
			throw new IllegalArgumentException("Worker is not assigned to activity");
		TimeSheet time = new TimeSheet(worker,date); // 3
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
	
	public List<TimeSheet> getTimeSheets(){
		return this.timeSheets;
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

	
	public void setExpectedWorkingHours(int expectedWorkingHours) {
		this.expectedWorkingHours = expectedWorkingHours;
	}
	

//	TODO: Calculate num of hours spent from timesheets
	//1. entry contains information about total hours spent on activity, 2. entry for the week
	@Override
	public int[] numHoursSpent() {
		
		Calendar cal = new GregorianCalendar();
		
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
	
	public WeekReport getRecentWeekReport() {
		return this.weekReports.get(weekReports.size()-1);
	}
	
	public void generateWeekReport() {
		WeekReport report = new WeekReport(this);
		
		weekReports.add(report);
		
//		report.printWeekReport();
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
