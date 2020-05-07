package ui;

import java.util.Scanner;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import designPatterns.Date;

public class Console {

	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);

	enum State {
		LOGIN, MAINSCREEN, PROJECTSELECTED, ACTIVITYSELECTED
	}

	public static void main(String[] args) {

		Console console = new Console();

		console.example();
		console.run();
	}

	// run() is the method which control logic of the program

	
	public void run() {

		State state = State.LOGIN;
		int selectionInput = -1;
		Project project = null;

		while (!softwareHouse.getExitRequest()) {

			if (!softwareHouse.loggedIn()) {
				System.out.println("\n \n \n \n \n");
				System.out.println("You need to enter ID to log in: ");

				try {
					softwareHouse.logIn(scanner.next());
					state = State.MAINSCREEN;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n\n\n");
				}
			}

			if (state.equals(State.MAINSCREEN)) {
				System.out.println("\n \n \n \n \n");
				System.out.println("Welcome to mainscreen.\n "
						+ "Enter a corresponding number for the following menu options: \n" 
						+ "1) Access projects \n"
						+ "2) Create new project \n" 
						+ "3) Add worker \n" 
						+ "4) Log out");

				try {
					selectionInput = scanner.nextInt();
					checkValidInput(selectionInput, 1, 4);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

//				ACCESS TO PROJECTS
				if (selectionInput == 1) {
					System.out.println("\n \n \n \n \n");
					for (int i = 0; i < softwareHouse.getListOfProjects().size(); i++) {
						System.out.println("Enter " + i + " to access project: "
								+ softwareHouse.getListOfProjects().get(i).getID() 
								+" Title: "
								+ softwareHouse.getListOfProjects().get(i).getTitle());
					}

					try {
						selectionInput = scanner.nextInt();
						checkValidInput(selectionInput, 0, softwareHouse.getListOfProjects().size());
						project = softwareHouse.getListOfProjects().get(selectionInput);
						state = State.PROJECTSELECTED;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

			if (state.equals(State.PROJECTSELECTED)) {
				System.out.println("You have selected project" + project.getID());
				System.out.println("Enter a corresponding number for the following menu options:");
				System.out.println("1) Create new activity \n"
						+ "2) Appoint project leader");
				
			}
		}

	}
	
	

//	TODO: Create method to check valid input (given interval of valid input, and the input.)
	public boolean checkValidInput(int input, int lowerBound, int upperBound) throws IllegalArgumentException {
		if (input <= upperBound && input >= lowerBound) {
			return true;
		} else
			throw new IllegalArgumentException("Invalid input");
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
		softwareHouse.getProject(projectID).createActivity(activityTitle, startDate, endDate,
				softwareHouse.getProject(projectID));
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
		createProject(new Date(20, 5), new Date(20, 15));
		createProject(new Date(20, 6), new Date(20, 17));
		appointProjectLeader("RFE", "200000");
		appointProjectLeader("RFE", "200001");
        
		logIn("RFE");
        
		createActivity("200000", "test", new Date(20, 7), new Date(20, 9));
		createActivity("200000", "test1", new Date(20, 7), new Date(20, 14));
		createActivity("200001", "test2", new Date(20, 8), new Date(20, 12));
		createActivity("200001", "test3", new Date(20, 9), new Date(20, 15));
		createActivity("200001", "test5", new Date(20, 10), new Date(20, 16));
        
		addWorker("200000", "test", "AB");
		addWorker("200000", "test1", "AB");
		addWorker("200001", "test2", "RFE");
		addWorker("200001", "test3", "RFE");
        
		addWorker("200001", "test2", "AB");
		addWorker("200001", "test5", "AB");
        
		getAllNumberActivities(new Date(20, 15), new Date(20, 20));
        
		logOut();
        
	}

}
