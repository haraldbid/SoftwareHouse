package designPatterns;

import app.WeekReport;
import app.Worker;

public interface Reporting {

	public int[] numMinSpent(Date date);
	public int getExpectedWorkingHours();
	public String getID();
	public String getTitle();
	public WeekReport generateWeekReport(Date date);
	public void printWeekReport(Date date);
	
}
