package designPatterns;

public class Date {
	int weekNumber, year;
	
	public Date(int week,int year) {
		this.weekNumber = week;
		this.year = year;
	}

	public int getWeekNumber() {
		return this.weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		if(weekNumber > 52 && weekNumber < 0) 
			throw new IllegalArgumentException("Weeknumber must be between 0 and 52");
		this.weekNumber = weekNumber;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	

}
