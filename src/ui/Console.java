package ui;

import java.util.Scanner;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class Console {

	SoftwareHouse softwareHouse = new SoftwareHouse();
	Scanner scanner = new Scanner(System.in);
	static int stage = -1;
	static boolean commandError = false;
	static boolean exitProgram = false;



	public static void main(String[] args) {

		Console console = new Console();

		console.example();

		while (!exitProgram) {
			console.displayOptions();
		}


	}

	// run() is the method which control logic of the program

	public void run() {

		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			softwareHouse.logIn(scanner.next());
			stage = 0;
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

		if (stage == -1) {
			run();
		}

		if (stage == 0) {
			stage0();
		}

		if (stage == 1) {
			stage1();					
		} else if (stage == 2) {
			stage2();
		} else if (stage == 3) {
			stage3();
		} else if (stage == 4) {

		} else if (stage == 5) {

		} else if (stage == 6) {

		} else if (stage == 7) {

		} else if (stage == 8) {

		} else if (stage == 9) {

		} else if (stage == 10) {

		} else if (stage == 11) {

		} else if (stage == 12) {

		} else if (stage == 13) {

		} else if (stage == 14) {

		} else if (stage == 15) {

		} else if (stage == 16) {

		} else if (stage == 17) {

		} else if (stage == 18) {

		} else if (stage == 19) {

		} else if (stage == 20) {

		} else if (stage == 80) {

			softwareHouse.logOut();
			stage = -1;
			space();

		} else if (stage == 99) {
			exitProgram = true;
		} else {
			commandError = true;
			stage = 0;
		}

	}

	public void getAllNumberActivities(Date startDate, Date endDate) {
		softwareHouse.getAllWorkersActivities(startDate, endDate);
	}

	public void createWorker(String ID) {
		try {
		softwareHouse.createWorker(ID);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void createProject(Date startDate, Date endDate) {
		softwareHouse.createProject(startDate, endDate);
	}


	public void appointProjectLeader(String workerID, String projectID) throws Exception {
		softwareHouse.getProject(projectID).appointProjectLeader(softwareHouse.getWorker(workerID));
	}

	public void createActivity(String projectID, String activityTitle, Date startDate, Date endDate) throws Exception {
		softwareHouse.getProject(projectID).createActivity(activityTitle, startDate, endDate,
				softwareHouse.getProject(projectID));
	}


	public void addWorker(String projectID, String activityTitle, String workerID) throws Exception {
		softwareHouse.getProject(projectID).getActivity(activityTitle).assignWorker(softwareHouse.getWorker(workerID));

	}

	public void logIn(String ID) {
		softwareHouse.logIn(ID);
	}

	public void logOut() {
		softwareHouse.logOut();
	}

	public void example() {


//		softwareHouse.createWorker("Nick");
//		softwareHouse.createWorker("Markus");
//		Worker worker1 = softwareHouse.getWorker(0);
//		Worker worker2 = softwareHouse.getWorker(1);
		softwareHouse.createProject(new Date(2020,4), new Date(2021,4));
//		softwareHouse.createProject(new Date, endDate);

//		String ProjectID1 = softwareHouse.getListOfProjects().get(0).getID();
		
		
		createWorker("AB");
		createWorker("CDDD");
		createWorker("RFE");
		createProject(new Date(2020, 5), new Date(2023, 15));
		createProject(new Date(2023, 6), new Date(2040, 17));
		
		try {
			appointProjectLeader("RFE", "200000");
			appointProjectLeader("RFE", "200001");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logIn("RFE");

		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		getAllNumberActivities(new Date(20, 15), new Date(20, 20));


		logOut();

	}
	
	public void options() {
		println("\nPress 0 to go back.\n"
				+ "Press " + stage + " to repeat action.\n");
		stage = scanner.nextInt();
		space();
	}

	public void space() {
		System.out.print("\n"
				+ "---------------------------------------------------------------------------------------------------------"
				+ "\n");
	}
	
	public void println(String string) {
		System.out.println(string);
	}
	
	
	public void stage0() {
		System.out.println("Press 1 to create a worker.\n" + "\n" + "Press 2 to create project.\n"
				+ "Press 3 to appoint project leader\n" + "Press 4 to view project timeframe\n"
				+ "Press 4 to edit project timeframe.\n" + "Press 5 to give a project a title.\n" + "\n"
				+ "Press 5 to create activity.\n" + "Press 6 to view activity timeframe\n"
				+ "Press 6 to edit activity timeframe\n" + "Press 7 to add worker to activity\n"
				+ "Press 8 to check workers availabilities.\n" + "\n" + "Press 9 to fill timesheet.\n"
				+ "Press 10 to seek assistance.\n" + "Press 11 to fill assistance timesheet.\n" + "\n"
				+ "Press 11 to see all projects.\n" + "Press 12 to see all activities of a project.\n"
				+ "Press 13 to see number of activities workers are assigned to within a given period.\n" + "\n"
				+ "Press 16 to give worker sick leave, holliday, ect.\n" + "\n" + "Press 17 to get week report.\n"
				+ "\n" + "Press 80 to log out.\n" + "\n" + "Press 99 to exit program\n");

		if (commandError) {
			System.out.println("Command not recognized");
			commandError = false;
		}

		stage = scanner.nextInt();

		space();
	}
	
	public void stage1() {
		println("Enter ID of worker you wish to create : ");
		createWorker(scanner.next());
		options();
	}
	
	// TODO mangler exceptions til ulovlige start/end dates
	public void stage2() {
		Date[] SE = startAndEndDate();
		softwareHouse.createProject(SE[0], SE[1]);
		space();
		options();
	}
	
	public void stage3() {
		println("Enter ID of worker you wish to appoint :");
		String worker = scanner.next();
		println("Enter ID of project you wish to appoint worker " + worker + " as leader of :");
		String project = scanner.next();
		try {
			softwareHouse.getProject(project).appointProjectLeader(softwareHouse.getWorker(worker));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		space();
		options();

	}
	
	public Date[] startAndEndDate() {
		Date[] SE = new Date[2];
		SE[0] = enterDate(true);
		SE[1] = enterDate(false);
		return SE;
	}

	public Date enterDate(boolean startEnd) {
		String startOrEnd;
		if (startEnd) {
			startOrEnd = "start";
		} else { startOrEnd = "end";}
		Scanner scanner = new Scanner(System.in);

		Date date = new Date();
		System.out.println("Please enter " + startOrEnd + " date in the following format: (yyyy - weeknumber)");

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

		return date;


	}

}
