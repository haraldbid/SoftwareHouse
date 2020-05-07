package ui;

import java.util.Scanner;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import designPatterns.Date;

public class Console {
	
//	private static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Console console = new Console();
//		console.testExamples();
		


		console.example();



		console.run();
		
	
		
	}

	// run() is the method which control logic of the program

	public void run() {

		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
		}
	}
	
	

	public void displayOptions() {
	}
	
	public void getAllNumberActivities(Date startDate, Date endDate) {
		softwareHouse.getAllWorkersActivities(startDate, endDate);
	}

	public void createWorker(String ID) {
		softwareHouse.createWorker(ID);
	}
	
	public void createProject(Date startDate, Date endDate) {
		softwareHouse.createProject(startDate, endDate);	
	}
	
	public void appointProjectLeader(String workerID, String projectID) {
		softwareHouse.getProject(projectID).appointProjectLeader(softwareHouse.getWorker(workerID));
	}
	
	public void createActivity(String projectID, String activityTitle, Date startDate, Date endDate) {
		softwareHouse.getProject(projectID).createActivity(activityTitle, startDate, endDate, softwareHouse.getProject(projectID));
	}
	
	public void addWorker(String projectID, String activityTitle, String workerID) {
		softwareHouse.getProject(projectID).getActivity(activityTitle).assignWorker(softwareHouse.getWorker(workerID));
		
	}
	
	public void logIn(String ID) {
		softwareHouse.logIn(ID);
	}
	
	public void logOut() {
		softwareHouse.logOut();
	}
	
	
	public void example() {
		
		createWorker("AB");
		createWorker("CDDD");
		createWorker("RFE");
		createProject(new Date(20,5), new Date(20,15));
		createProject(new Date(20,6), new Date(20,17));
		appointProjectLeader("RFE", "200000");
		appointProjectLeader("RFE", "200001");
		
		logIn("RFE");
		
		createActivity("200000", "test", new Date(20,7), new Date(20,9));
		createActivity("200000", "test1", new Date(20,7), new Date(20,14));
		createActivity("200001", "test2", new Date(20,8), new Date(20,12));
		createActivity("200001", "test3", new Date(20,9), new Date(20,15));
		createActivity("200001", "test5", new Date(20,10), new Date(20,16));
		
	

		addWorker("200000", "test", "AB");
		addWorker("200000", "test1", "AB");
		addWorker("200001", "test2", "RFE");
		addWorker("200001", "test3", "RFE");

		addWorker("200001", "test2", "AB");
		addWorker("200001", "test5", "AB");
		
		getAllNumberActivities(new Date (20,15), new Date(20, 20));
		
		logOut();

		
	}
	


}
