package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import app.SoftwareHouse;

public class Console {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Console console = new Console();
		

		console.test("AB");
		console.test("CDDD");
		
		console.run();
	}
//		String s = "1030 02 10";
//		System.out.println(s.substring(8,10));
		
		
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Calendar date = new GregorianCalendar();  
		    date.set(920, 2, 20);
		    System.out.println(date.getTime());  
		
		
//		console.enterDate();
		
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
	

	public void displayOptions() {
		
	}
	
	public void test(String ID) {
		softwareHouse.createWorker(ID);

	//test() is used to test program features
	public void test() {
		softwareHouse.createWorker("AB");
	}
	
	
	
	
	private Calendar enterDate() {
		Calendar date = new GregorianCalendar();
		
		System.out.println("Please enter date in the following format: (yyyy mm dd)");
		
		String d = scanner.nextLine();
		
		if (d.length() != 10) {
			throw new IllegalArgumentException("Date not correct format");
		}
		
		for (int i = 0 ; i < d.length(); i ++) {
			
			if (Character.isAlphabetic(d.charAt(i))) {
				throw new IllegalArgumentException("Date not correct format");
			}
		}
		
		int year = Integer.parseInt(d.substring(0, 4));
		int month = Integer.parseInt(d.substring(5, 7));
		int day = Integer.parseInt(d.substring(8, 10));
		
		date.set(year, month, day);
		
		
		
		System.out.println("valid date entered");
		
		return date;
´}	

}
