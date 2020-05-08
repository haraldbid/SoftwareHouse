package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import designPatterns.Date;
import designPatterns.Observable;
import designPatterns.Observer;
import designPatterns.Reporting;

public class Project implements Observer, Reporting {

	private Worker workerLoggedIn;
	private String projectTitle;
	private String projectNumberID;
	private Worker projectLeader;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	private ArrayList<Activity> listOfActivities = new ArrayList<Activity>();
	private Date startDate = new Date();
	private Date endDate = new Date();
	private Observable softwareHouse;
	private List<WeekReport> weekReports = new ArrayList<WeekReport>();

	public Project(Observable softwareHouse, Date startDate, Date endDate, String projectNumberID) {

		this.softwareHouse = softwareHouse;
		this.softwareHouse.register(this);
		this.startDate = startDate;
		this.endDate = endDate;
		// this.projectLeader = null;

		this.projectNumberID = projectNumberID;
	}

	public void modifyProjectNumberID(int year) {

		String yearStr = Integer.toString(startDate.getYear()).substring(2, 4);
		String runningCount = projectNumberID.substring(2, 6);

		this.projectNumberID = yearStr + runningCount;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		modifyProjectNumberID(startDate.getYear());
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(int year, int week) {
		startDate.setDate(year, week);
		modifyProjectNumberID(year);
	}

	public void setEndDate(int year, int week) {
		endDate.setDate(year, week);
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public boolean hasProjectTitle() {

		if (projectTitle == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getTitle() {

		return this.projectTitle;
	}

	public String getID() {
		return projectNumberID;
	}

	public boolean hasProjectLeader() {
		if (projectLeader == null) {
			return false;
		} else {
			return true;
		}
	}

	
	public Activity getActivity(String title) throws Exception {
		
		for(Activity a : listOfActivities) {
			if (a.getTitle().equals(title)) {
				return a;
			}
		}

		throw new Exception("Activity not found");
	}

	public Worker getProjectLeader() {
		if (hasProjectLeader()) {
			return projectLeader;
		} else {
			throw new IllegalArgumentException("Project has no leader yet.");
		}
	}

	public void appointProjectLeader(Worker appointedProjectLeader) {

		if (!this.hasProjectLeader())
			this.projectLeader = appointedProjectLeader;
		else
			throw new IllegalArgumentException("Project has leader assigned");

//		System.out.println("\nWorker " + appointedProjectLeader.getID() + " is appointed project leader of " + this.getID());

	}

	// JUST A SHELL
	public void createActivity(String title, Date startDate, Date endDate) {

		if (this.startDate.after(startDate) || this.endDate.before(endDate)) {
			throw new IllegalArgumentException("Date incongruent with project period");
		}

		if (isProjectLeaderLoggedIn()) {

			Activity activity = new Activity(softwareHouse, title, startDate, endDate, this);

			listOfActivities.add(activity);

		} else {

//			System.out.println("Only the Project Leader may add an activity.");
		}
	}

	public boolean isProjectLeaderLoggedIn() {
		if (workerLoggedIn.equals(projectLeader)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update(Worker loggedIn) {
		this.workerLoggedIn = loggedIn;
	}


	public void generateWeekReport(Date date) {

		boolean weekReportExists = false;
		Calendar cal = new GregorianCalendar();

		/*
		 * The first conditional checks if the entered date has not occured yet
		 */
//		if (cal.get(Calendar.YEAR) < date.getYear()
//				|| (cal.get(Calendar.YEAR) == date.getYear() 
//				&& cal.get(Calendar.WEEK_OF_YEAR) < date.getWeekNumber())) {
//			throw new IllegalArgumentException("Illgeal date entered");
//		}

		/*
	
public void generateWeekReport(Date date) throws IllegalArgumentException {
		
		boolean weekReportExists = false;
		Calendar cal = new GregorianCalendar();
		
		if(date.after(this.endDate))
			throw new IllegalArgumentException("Date incongruent with activity date");		
		/* 
		 * Checks if a week report already exists for that date
		 */
		for (WeekReport r : weekReports) {
			if (r.getDate().equals(date)) {
				weekReportExists = true;
			}
		}
		/*
		 * Report added to list if no reports have been genereated for the given date
		 */
		if (!weekReportExists) {
			WeekReport report = new WeekReport(this, date);

			weekReports.add(report);
		}
	}

	@Override
	public int[] numMinSpent(Date date) {
		/*
		 * 1. Entry of NumMinSpent is the total hours spent on project 2. Entry of
		 * NumMinSpent is the hours spent at the specified week
		 */
		int[] numMinSpent = { 0, 0 };

		/*
		 * For each activity, a report is generated for the given date. Then the total
		 * number of hours spent on each activity is added to the array.
		 */
		for (Activity a : listOfActivities) {
			a.generateWeekReport(date);
			numMinSpent[0] += a.getWeekReport(date).numMinSpent[0];
			numMinSpent[1] += a.getWeekReport(date).numMinSpent[1];
		}
		return numMinSpent;
	}

	public void printWeekReport(Date date) {
		generateWeekReport(date);
		for (WeekReport r : weekReports) {
			if (r.getDate().equals(date))
				r.printWeekReport();
		}
	}

	@Override
	public int getExpectedWorkingHours() {
		int expectedWorkingHours = 0;

		for (Activity a : listOfActivities) {
			expectedWorkingHours += a.getExpectedWorkingHours();
		}
		return expectedWorkingHours;
	}

	public ArrayList<Activity> getActivities() {
		return this.listOfActivities;
	}
}
