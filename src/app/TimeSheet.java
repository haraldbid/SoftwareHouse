package app;

import designPatterns.Date;

public class TimeSheet {
	
	//private boolean availability;
	int minutesWorked;
	Worker worker;
	Worker helper;
	Date date;
	
	
	public TimeSheet(Worker worker) {
		this.worker = worker;
		this.minutesWorked = 0;
		
	}
	public void addtimeWorked(int hours, int minutes) {
		this.minutesWorked += (hours*60 + minutes);
	}
	public void setHelper(Worker worker) {
		this.helper = worker;
	}
	

}
