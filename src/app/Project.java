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
	private String projectNumberID;
	private Worker projectLeader;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	private List<Activity> listOfActivities = new ArrayList<Activity>();
	private Date startDate = new Date();
	private Date endDate = new Date();


	private Observable softwareHouse;
	private List<WeekReport> weekreports = new ArrayList<WeekReport>();
	
	public Project(Observable softwareHouse, Date startDate, Date endDate, String projectNumberID) {
		this.softwareHouse = softwareHouse;
		this.softwareHouse.register(this);
		
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.projectNumberID = projectNumberID;
	}
	
	
	


	public void modifyProjectNumberID (int year) {
		
		String yearStr = Integer.toString(year);
		String runningCount = projectNumberID.substring(2,6);
		
		this.projectNumberID = yearStr + runningCount;
		
		
	}



	public void setStartDate(int year, int week) {
		startDate.setDate(year, week);
		modifyProjectNumberID(year);
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
	public void createActivity(String title, Date startDate, Date endDate) {

		if (isProjectLeaderLoggedIn()) {
			
			Activity activity = new Activity (title, startDate, endDate);

			listOfActivities.add(activity);

		} else {

			System.out.println("Only the Project Leader may add an activity.");
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
	
	public void generateWeekReport() {
		WeekReport report = new WeekReport(this);
		
		report.printWeekReport();
	}



	@Override
	public int[] numHoursSpent() {
		
		int[] numHoursSpent = {0,0}; 
		
		for (Activity a : listOfActivities) {
			
		}

		return numHoursSpent;
	}



	@Override
	public int getExpectedWorkingHours() {
		int numHoursSpent = 0; 
		
		for (Activity a : listOfActivities) {
			numHoursSpent += a.getExpectedWorkingHours();
		}

		return numHoursSpent;
	
	}



}
