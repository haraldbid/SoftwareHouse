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

		console.run();
	}

	// run() is the method which control logic of the program

	public void run() {

		State state = State.LOGIN;
		int selectionInput = -1;
		Project project;

		while (!softwareHouse.getExitRequest()) {

			if (!softwareHouse.loggedIn()) {
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
						System.out.println("Enter " + i + "to access project: "
								+ softwareHouse.getListOfProjects().get(i).getTitle());
					}
					
					try{
						selectionInput = scanner.nextInt();
						checkValidInput(selectionInput, 0, softwareHouse.getListOfProjects().size());
						project = softwareHouse.getListOfProjects().get(selectionInput);
						
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}

			if (state.equals(State.PROJECTSELECTED)) {
				
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

	public void test(String ID) {
		softwareHouse.createWorker(ID);
	}

	public void createProject(String ID) {

	}

}
