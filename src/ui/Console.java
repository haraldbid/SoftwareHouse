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
		


		console.createWorker("AB");
		console.createWorker("CDDD");
		console.createWorker("RFE");
		console.createProject(new Date(20,5), new Date(20,15));
		console.createProject(new Date(20,6), new Date(20,17));
		console.appointProjectLeader("RFE", "200000");
		console.appointProjectLeader("RFE", "200001");



		console.run();
		
		console.createActivity("200000", "test", new Date(20,7), new Date(20,9));
		console.createActivity("200000", "test1", new Date(20,7), new Date(20,14));
		console.createActivity("200001", "test2", new Date(20,8), new Date(20,12));
		console.createActivity("200001", "test3", new Date(20,9), new Date(20,15));
		console.createActivity("200001", "test5", new Date(20,10), new Date(20,16));

		console.addWorker("200000", "test", "AB");
		console.addWorker("200000", "test1", "AB");
		console.addWorker("200001", "test2", "RFE");
		console.addWorker("200001", "test3", "RFE");

		console.addWorker("200001", "test2", "AB");
		console.addWorker("200001", "test5", "AB");
		
		console.getAllNumberActivities(new Date (20,15), new Date(20, 20));
		
		
		
	}

	// run() is the method which control logic of the program

	public void run() {

		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
		}
	}
	
	
//	public void testExamples() {
		//INIT
		
//		softwareHouse.createProject(new GregorianCalendar(1930,03-1,4), new GregorianCalendar(1931,03-1,4));
//		softwareHouse.getListOfProjects().get(0).createActivity("testActivity");
		
		
		
//	}
	
	

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
		softwareHouse.getProject(projectID).createActivity(activityTitle, startDate, endDate);
	}
	
	public void addWorker(String projectID, String activityTitle, String workerID) {
		softwareHouse.getProject(projectID).getActivity(activityTitle).assignWorker(softwareHouse.getWorker(workerID));
		
	}
	
//	public void getNumberOfActivities 


}
