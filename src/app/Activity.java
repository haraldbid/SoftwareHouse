package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import designPatterns.Date;
import designPatterns.Observable;
import designPatterns.Observer;
import designPatterns.Reporting;

public class Activity implements Observer, Reporting {

	private String activityID;
	private String title;
	private int expectedWorkingHours;
	private List<Worker> listWorkersActivity = new ArrayList<Worker>();
	private Date startDate;
	private Date endDate;
	private Worker workerLoggedIn;
	private Project project;
	private Observable softwareHouse;

	private List<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
	private List<WeekReport> weekReports = new ArrayList<WeekReport>();

	public Activity(Observable softwareHouse, String title, Date startDate, Date endDate, Project project) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;

		this.project = project;
		this.softwareHouse = softwareHouse;
		this.softwareHouse.register(this);
		this.workerLoggedIn = softwareHouse.getWorkerLoggedIn();
	}

	@Override
	public void update(Worker loggedIn) {
		this.workerLoggedIn = loggedIn;
	}

	public void inputAssistance(Worker worker, Worker helper, int hours, int minutes, Date date) throws Exception {
		if (hours < 0 || minutes < 0)
			throw new IllegalArgumentException("Only positive work time");
		if (this.searchWorker(worker.getID()) == null)
			throw new IllegalArgumentException("Worker is not assigned to activity");

		TimeSheet t = new TimeSheet(worker, date);
		t.addtimeWorked(hours, minutes);
		t.setHelper(helper);
		this.timeSheets.add(t);
	}

	public void assignWorker(Worker worker) throws Exception {
		if (workerLoggedIn == project.getProjectLeader()) {
			if (searchWorker(worker.getID()) == null) {
				this.listWorkersActivity.add(worker);
				worker.addActivity(this);
			} else {
				throw new IllegalArgumentException("Worker is already assigned");
			}
		} else {
			throw new IllegalArgumentException("Only Project Leader can assign a worker to an activity.");
		}

	}

	public void inputWorkTime(Worker worker, int hours, int minutes, Date date) throws Exception {

		assert worker != null && minutes < 60 : "Pre condition for inputWorkTime()";

		if (this.startDate.after(date) || this.getEndDate().before(date)) {
			throw new IllegalArgumentException("Date incongruent with project period");
		}
		if (hours < 0 || minutes < 0) // 1 || 2
			throw new IllegalArgumentException("Invalid input");
		if (this.searchWorker(worker.getID()) == null) // Whitebox 3
			throw new IllegalArgumentException("Worker is not assigned to activity");
		TimeSheet time = new TimeSheet(worker, date); // 3
		time.addtimeWorked(hours, minutes);
		timeSheets.add(time);

		assert timeSheets.get(timeSheets.size() - 1).getWorker().equals(worker) : "Post condition for inputWorkTime()";
		assert timeSheets.get(timeSheets.size() - 1).getHoursWorked() == hours : "Post condition for inputWorkTime()";
		assert timeSheets.get(timeSheets.size() - 1)
				.getMinutesInputed() == minutes : "Post condition for inputWorkTime()";
	}

	public Worker searchWorker(String ID) {
		for (Worker worker : listWorkersActivity) {
			if (worker.getID().equals(ID))
				return worker;
		}
		return null;
	}

	public String getTitle() {
		return this.title;
	}

	public List<TimeSheet> getTimeSheets() {
		return this.timeSheets;
	}

	public void setStartDate(Date date) {
		if ((date.after(project.getStartDate()) || date.equals(project.getStartDate())) && (date.before(project.getEndDate()) || date.equals(project.getEndDate()))) {
		startDate = date;
		} else {
			throw new IllegalArgumentException("Start and/or end date of activity not within project timeframe.");
		}
	}

	public void setEndDate(Date date) {
		if ((date.after(project.getStartDate()) || date.equals(project.getStartDate())) && (date.before(project.getEndDate()) || date.equals(project.getEndDate()))) {
			endDate = date;
			} else {
				throw new IllegalArgumentException("Start and/or end date of activity not within project timeframe.");
			}
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

	// 1. entry contains information about total hours spent on activity, 2. entry
	// for the week
	@Override
	public int[] numMinSpent(Date date) {

		int[] numMinSpent = { 0, 0 };

		for (TimeSheet t : timeSheets) {
			if (t.getDate().before(date) || t.getDate().equals(date)) {
				numMinSpent[0] += t.getMinutesWorked();
			}
			if (t.getDate().equals(date)) {
				numMinSpent[1] += t.getMinutesWorked();
			}
		}

		return numMinSpent;

	}


	public WeekReport getRecentWeekReport() {
		return this.weekReports.get(weekReports.size() - 1);
	}

	public WeekReport generateWeekReport(Date date) {
		boolean weekReportExists = false;

		if (this.startDate.after(date) && this.endDate.before(date))
			return null;

		for (WeekReport r : weekReports) {
			if (r.getDate().equals(date)) {
				weekReportExists = true;
				return r;
			}
		}

		if (!weekReportExists) {
			WeekReport report = new WeekReport(this, date);

			weekReports.add(report);
			return report;
		}
		return null;
	}

	@Override
	public int getExpectedWorkingHours() {
		return this.expectedWorkingHours;
	}

	public WeekReport getWeekReport(Date date) {
		for (WeekReport r : weekReports) {
			if (r.getDate().equals(date)) {
				return r;
			}
		}
		return null;
	}

	public void printWeekReport(Date date) throws IllegalArgumentException {

		if (date.after(this.endDate))
			throw new IllegalArgumentException("Date incongruent with activity date");

		generateWeekReport(date);
		for (WeekReport r : weekReports) {
			if (r.getDate().equals(date))
				r.printWeekReport();
		}
	}
	
	public void deleteWorker(Worker worker) {
		int i = 0;
		while (!listWorkersActivity.get(i).equals(worker)) {
			i++;
		}
		listWorkersActivity.remove(i);
	}

	public String stringWorkers() {
		String str = "";
		for (int i = 0; i < listWorkersActivity.size(); i++) {
			str += listWorkersActivity.get(i).getID() + " ";
		}
		return str;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
