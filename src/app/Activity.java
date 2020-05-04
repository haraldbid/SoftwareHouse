package app;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import designPatterns.Date;
import designPatterns.Reporting;

public class Activity implements Reporting {





	private int activityID;
	private static int activityIDCount = 0;
	private String title;
	private int expectedWorkingHours;
	private int[] numHoursSpent;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	private List<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
	private List<WeekReport> weekReports = new ArrayList<WeekReport>();
	private Date startDate,endDate;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	
	
	public Activity(String title,Date startD, Date endD) {
		this.title = title;
		this.startDate = startD;
		this.endDate = endD;
		this.activityID = activityIDCount + 1;

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
		timeSheets.add(time);

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
