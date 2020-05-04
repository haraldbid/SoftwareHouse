package ui;


import java.util.Scanner;

import app.SoftwareHouse;
import designPatterns.Date;

public class Console {

	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Console console = new Console();
		
		console.test();
		console.test();
		console.run();
	}
	
	
	
	//run() is the method which control logic of the program
	public void run() {
		
		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
		}
		
	}
	
	//test() is used to test program features
	public void test() {
		softwareHouse.createWorker("AB");
	}
	
	
	
	
	private Date enterDate() {
		Date date = new Date();
		
		System.out.println("Please enter date in the following format:  ");
		
		
		
		return date;
		
	}
	

}
