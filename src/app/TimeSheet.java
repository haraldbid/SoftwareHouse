package app;

import designPatterns.Date;

public class TimeSheet {
	
	//private boolean availability;
	private int minutesWorked;
	private Worker worker;
	private Worker helper;
	private Date date;
	
	
	public TimeSheet(Worker worker,Date week) {
		this.worker = worker;
		this.minutesWorked = 0;
		this.date = week;
		
	}
	public void addtimeWorked(int hours, int minutes) {
		this.minutesWorked += (hours*60 + minutes);
	}
	public void setHelper(Worker worker) {
		this.helper = worker;
	}
	
	public int getMinutesWorked() {
		return this.minutesWorked;
	}
	
	public Date getDate() {
		return this.date;
	}
}
