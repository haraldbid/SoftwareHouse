package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
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
						+ "Enter a corresponding number for the following menu options: \n" + "1) Access projects \n"
						+ "2) Create new project \n" + "3) Add worker \n" + "4) Log out");

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
						System.out.println(
								"Enter " + i + " to access project: " + softwareHouse.getListOfProjects().get(i).getID()
										+ " Title: " + softwareHouse.getListOfProjects().get(i).getTitle());
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
				System.out.println("You have selected project " + project.getID());
				System.out.println("Enter a corresponding number for the following menu options:");
				System.out.println(
						"1) Create new activity \n" + "2) Appoint project leader \n" + "3) Print weekly report");

				try {
					selectionInput = scanner.nextInt();
					checkValidInput(selectionInput, 0, 3);
					
					if(selectionInput == 12)
						state=State.MAINSCREEN;
					
					if (selectionInput == 3) {
						
						project.printWeekReport(enterDate());
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				

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

	public void example() {
//		Workers
		softwareHouse.createWorker("MARK");
		softwareHouse.createWorker("MART");
		softwareHouse.createWorker("HARA");
		softwareHouse.createWorker("NICK");
		Worker worker1 = softwareHouse.getWorker(0);
		Worker worker2 = softwareHouse.getWorker(1);
		Worker worker3 = softwareHouse.getWorker(2);
		Worker worker4 = softwareHouse.getWorker(3);

//		Projects

		softwareHouse.createProject(new Date(2025, 1), new Date(2025, 52));
		softwareHouse.createProject(new Date(2020, 4), new Date(2020, 42));
		Project project1 = softwareHouse.getListOfProjects().get(0);
		Project project2 = softwareHouse.getListOfProjects().get(1);
		String projectID1 = project1.getID();
		String projectID2 = project2.getID();

//		Project leaders
		project1.appointProjectLeader(worker1);
		project2.appointProjectLeader(worker1);

		softwareHouse.logIn(worker1.getID());
//		Activities
		project1.createActivity("TestActivity1", project1.getStartDate(), project1.getEndDate());
		project1.createActivity("testActivity2", new Date(2025, 7), new Date(2025, 8));
		project2.createActivity("testActivity3", new Date(2025, 30), new Date(2025, 32));

		Activity activity1 = project1.getActivities().get(0);
		Activity activity2 = project1.getActivities().get(1);
		Activity activity3 = project2.getActivities().get(0);
		
//		Expected time
		activity1.setExpectedWorkingHours(80);
		activity2.setExpectedWorkingHours(150);
		activity3.setExpectedWorkingHours(90);
//		Worker added to activity
		activity1.assignWorker(worker1);
		activity1.assignWorker(worker2);
		activity1.assignWorker(worker3);
		activity1.assignWorker(worker4);
		activity2.assignWorker(worker1);
		activity2.assignWorker(worker4);
		activity3.assignWorker(worker1);

//		Add time sheets for workers
		activity1.inputWorkTime(worker1, 12, 30, new Date(2025, 12));
		activity1.inputWorkTime(worker2, 2, 30, new Date(2025, 7));
		activity1.inputWorkTime(worker2, 5, 0, new Date(2025, 3));
		activity1.inputWorkTime(worker3, 20, 0, new Date(2040, 4));
		activity1.inputWorkTime(worker1, 12, 30, new Date(2025,40));
		
		activity3.inputWorkTime(worker1, 40, 30, new Date(2025, 30));
		activity3.inputWorkTime(worker1, 35, 30, new Date(2025, 31));


		ArrayList<Activity> arr = new ArrayList<Activity>();
		Random r = new Random();
//		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		arr.add(activity1);
		arr.add(activity2);
		arr.add(activity3);
//		GENERATES INPUTWORKEDHOURS FOR EVERY ACTIVITY
		
		for (int i = 0; i < 5; i++) {
			for (Activity a : arr) {
				a.inputWorkTime(worker1, r.nextInt(20), 30, new Date(2025, ThreadLocalRandom.current()
						.nextInt(a.getStartDate().getWeekNumber(), (a.getEndDate().getWeekNumber() + 1))));
			}
		}
		
		project2.printWeekReport(new Date(2025,31));
		
		softwareHouse.logOut();
		
//		softwareHouse.getAllWorkersActivities(new Date(2025,8), new Date(2045,12));

	}

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

//		System.out.println("valid date entered: " + print());

		return date;

	}
	
	
	public void printAllProjectsInfo() {
		for(Project p : softwareHouse.getListOfProjects()) {
			System.out.println(p.getID() + " activities:");
			for(Activity a : p.getActivities()) {
				System.out.println(a.getTitle());
			}
			System.out.println("________________"
					+ "");
		}
	}

}
