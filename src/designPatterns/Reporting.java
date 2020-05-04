package designPatterns;

public interface Reporting {

	public int[] numHoursSpent();
	public int getExpectedWorkingHours();
	public int getID();
	public String getTitle();
	public void generateWeekReport();
	
	
}