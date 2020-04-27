package ui;

import java.util.Scanner;

import app.SoftwareHouse;

public class Console {

	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Console console = new Console();
		
		console.test();
		
		console.run();
	}
	
	
	public void run() {
		
		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
		}
		
	}
	
	public void test() {
		softwareHouse.createWorker("AB");
	}

}
