package ui;

import java.util.Scanner;

import app.Activity;
import app.SoftwareHouse;
import designPatterns.Date;

public class Console {
	
	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Console console = new Console();

		console.run();
	}

	// run() is the method which control logic of the program

	public void run() {

		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
		}
	}
	
	
//	public void testExamples() {
//		//INIT
//		
////		softwareHouse.createProject(new GregorianCalendar(1930,03-1,4), new GregorianCalendar(1931,03-1,4));
//		softwareHouse.getListOfProjects().get(0).createActivity("testActivity");
//		
//		
		
//	}

	public void displayOptions() {
	}

	public void test(String ID) {
		softwareHouse.createWorker(ID);
	}
	
	public void createProject(String ID) {
		
	}


}
