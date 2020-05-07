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
		String print = String.format("Week %s Year %s", this.weekNumber, this.year);
		return print;
	}
	
	
	
//	TODO: Find a home for this method
	public Date enterDate() {
		Scanner scanner = new Scanner(System.in);

		Date date = new Date();
		System.out.println("Please enter date in the following format: (yyyy - weeknumber)");

		String input = scanner.nextLine();

		if (input.length() != 7) {
			throw new IllegalArgumentException("Date not correct format");
		}

		for (int i = 0; i < input.length(); i++) {

			if (Character.isAlphabetic(input.charAt(i))) {
				throw new IllegalArgumentException("Date not correct format");
			}
		}

		int year = Integer.parseInt(input.substring(0, 4));
		int week = Integer.parseInt(input.substring(5, 7));

		date.setDate(year, week);

		System.out.println("valid date entered: " + print());

		return date;

	}

}
