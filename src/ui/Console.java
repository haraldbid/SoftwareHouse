package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import app.Activity;
import app.SoftwareHouse;

public class Console {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	SoftwareHouse softwareHouse = new SoftwareHouse();
	Activity activity = new Activity("testAct");
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Console console = new Console();
		console.testExamples();
		
//		console.test();
//
//		console.run();
		
}
	
	
	
	//run() is the method which control logic of the program

	public void run() {

		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
		}
	}
	
	
	public void testExamples() {
		//INIT
		
		softwareHouse.createProject(new GregorianCalendar(1930,03-1,4), new GregorianCalendar(1931,03-1,4));
		softwareHouse.getListOfProjects().get(0).createActivity("testActivity");
		
		
		
		
		
	}

	public void displayOptions() {
	}


	
//	TODO: catch bad input (non-existing dates)
	private Calendar enterDate() {
		Calendar date = new GregorianCalendar();

		System.out.println("Please enter date in the following format: (yyyy mm dd)");

		String input = scanner.nextLine();

		if (input.length() != 10) {
			throw new IllegalArgumentException("Date not correct format");
		}

		for (int i = 0; i < input.length(); i++) {

			if (Character.isAlphabetic(input.charAt(i))) {
				throw new IllegalArgumentException("Date not correct format");
			}
		}

		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7))-1; //Calendar class numbers months from 0-11
		int day = Integer.parseInt(input.substring(8, 10));
		

		date.set(year, month, day);

		
		System.out.println("valid date entered: " + dateFormat.format(date.getTime()));

		return date;
	}

}
