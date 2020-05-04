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

public class Project implements Observer {

	private String workerLoggedIn;
	private String projectTitle;
	private String projectNumberID;
	private Worker projectLeader;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	private Date startDate = new Date();
	private Date endDate = new Date();
	private List<Activity> listOfActivities;
	private Observable softwareHouse;
	
	public Project(Observable softwareHouse) {
		this.softwareHouse = softwareHouse;
		this.softwareHouse.register(this);
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

	public String getProjectTitle() {

		if (hasProjectTitle()) {
			return "Project title : " + projectTitle;
		} else {
			return "Project has no title yet";
		}
	}

	public String getProjectNumberID() {
		return "Project number ID : " + projectNumberID;
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
	public void addActivity(Activity activity) {

		if (isProjectLeaderLoggedIn()) {

			listOfActivities.add(activity);

		} else {

			System.out.println("Only the Project Leader may add an activity.");
		}
	}
	
	
	// SHOULDNT BE HERE
	public void addWorkerToActivity(Worker worker, Activity activity) {

		if (isProjectLeaderLoggedIn()) {
//			activity.
		} else {
			System.out.println("Only the Project Leader may add a worker to an activity");
		}

	}

	public boolean isProjectLeaderLoggedIn() {
		if (workerLoggedIn.equals(projectLeader.getID())) {
			return true;
		} else {
			return false;
		}
	}





	@Override
	public void update(String loggedIn) {
		this.workerLoggedIn = loggedIn;
	}

}
