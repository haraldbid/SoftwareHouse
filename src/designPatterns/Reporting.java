package designPatterns;

import app.Worker;

public interface Reporting {

	public int[] numMinSpent(Date date);
	public int getExpectedWorkingHours();
	public String getID();
	public String getTitle();
	public void generateWeekReport(Date date);
	
}
