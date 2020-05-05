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

public class Project implements Observer, Reporting{

	private Worker workerLoggedIn;
	private String projectTitle;
	private int projectNumberID;
	private Worker projectLeader;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	private ArrayList<Activity> listOfActivities = new ArrayList<Activity>();
	private Date startDate = new Date();
	private Date endDate = new Date();


	private Observable softwareHouse;
	private List<WeekReport> weekReports = new ArrayList<WeekReport>();
	
	public Project(Observable softwareHouse, Date startDate, Date endDate, String string) {
		this.softwareHouse = softwareHouse;
		this.softwareHouse.register(this);
		
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	

	public void setStartDate(int year, int week) {
		startDate.setDate(year, week);
	}
	
	public void setEndDate(int year, int week) {
		endDate.setDate(year, week);
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

		if (hasProjectTitle()) {
			return "Project title : " + projectTitle;
		} else {
			return "Project has no title yet";
		}
	}

	public int getID() {
		return projectNumberID;
	}

	public boolean hasProjectLeader() {
		if (projectLeader == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getProjectLeader() {
		if (hasProjectLeader()) {
			return "Project Leader is worker " + projectLeader.getID();
		} else {
			return "Project has no leader yet.";
		}
	}

	public void appointProjectLeader(Worker appointedProjectLeader) {
		this.projectLeader = appointedProjectLeader;
	}

	// JUST A SHELL
	public void createActivity(String title) {

		if (isProjectLeaderLoggedIn()) {
			
			Activity activity = new Activity (title);

			listOfActivities.add(activity);

		} else {

			System.out.println("Only the Project Leader may add an activity.");
		}
	}
	
	

	public boolean isProjectLeaderLoggedIn() {
		if (workerLoggedIn.equals(projectLeader.getID())) {
			return true;
		} else {
			return false;
		}
	}

	
	public void generateWeekReport() {
		WeekReport report = new WeekReport(this);
		
		weekReports.add(report);
		
//		report.printWeekReport();
	}


	@Override
	public int[] numHoursSpent() {
		int[] numHoursSpent = {0,0}; 
		
		for (Activity a : listOfActivities) {
			a.generateWeekReport();
			numHoursSpent[0] += a.getRecentWeekReport().numHoursSpent[0];
			numHoursSpent[1] += a.getRecentWeekReport().numHoursSpent[1];
		}
		return numHoursSpent;
	}



	@Override
	public int getExpectedWorkingHours() {
		int expectedWorkingHours = 0; 
		
		for (Activity a : listOfActivities) {
			expectedWorkingHours += a.getExpectedWorkingHours();
		}

		return expectedWorkingHours;
	
	}

	@Override
	public void update(Worker loggedIn) {
		this.workerLoggedIn = loggedIn;
		
	}

	public ArrayList<Activity> getActivities(){
		return this.listOfActivities;
	}



}
