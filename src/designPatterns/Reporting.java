package designPatterns;

import app.Worker;

public interface Reporting {

	public int[] numHoursSpent();
	public int getExpectedWorkingHours();
	public String getID();
	public String getTitle();
	public void generateWeekReport();
	void update(Worker loggedIn);
	
	
}
