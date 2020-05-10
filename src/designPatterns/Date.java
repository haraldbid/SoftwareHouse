package designPatterns;

import java.util.Scanner;

public class Date {
	int weekNumber, year;

	public Date() {
		this.weekNumber = 1;
		this.year = 2000;
	}

	public Date(int year, int week) {
		this.weekNumber = week;
		this.year = year;
	}

	public int getWeekNumber() {
		return this.weekNumber;
	}

	public void setDate(int yearNumber, int weekNumber) {
		setWeekNumber(weekNumber);
		setYear(yearNumber);
	}

	public void setWeekNumber(int weekNumber) {
		if (weekNumber > 53 && weekNumber < 1)
			throw new IllegalArgumentException("Weeknumber must be between 1 and 53");
		this.weekNumber = weekNumber;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean equals(Date date) {
		if (this.year == date.year && this.weekNumber == date.weekNumber) {
			return true;
		}
		return false;
	}

	public boolean after(Date date) {
		if (this.year > date.year) {
			return true;
		} else if (this.year == date.year && this.weekNumber > date.weekNumber) {
			return true;
		}
		return false;
	}

	public boolean before(Date date) {
		if (this.year < date.year) {
			return true;
		} else if (this.year == date.year && this.weekNumber < date.weekNumber) {
			return true;
		}
		return false;
	}

	public String print() {
		String print = String.format("%s/%s", this.year, this.weekNumber);
		return print;
	}
	
	public void checkChronology(Date endDate) {
		if (this.after(endDate)) {
			throw new IllegalArgumentException("Start date can't be after end date.");
		}
	}
	

}
