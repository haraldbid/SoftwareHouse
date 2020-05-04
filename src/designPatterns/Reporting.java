package designPatterns;

public interface Reporting {

	public int[] numHoursSpent();
	public int getExpectedNumWorkingHours();
	public int getID();
	public String getTitle();
	public void generateWeekReport();
	
	
}
