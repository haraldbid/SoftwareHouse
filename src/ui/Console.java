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
	static int stage = -1;
	static boolean commandError = false;
	static boolean exitProgram = false;

	public static void main(String[] args) {

		Console console = new Console();

		console.example();
//		console.printAllProjectsInfo();

		while (!exitProgram) {
			console.displayOptions();
		}
	}

	// run() is the method which control logic of the program

	public void run() {

		if (!softwareHouse.loggedIn()) {
			System.out.println("You need to enter ID to log in: ");
			try {
				softwareHouse.logIn(scanner.next());
				stage = 0;
			} catch (Exception e) {
//				stage = -1;
				System.out.println(e);
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

		if (stage == -1) {
			run();
		}

		else if (stage == 0) {
			stage0();
		}

		else if (stage == 1) {
			stage1();
		} else if (stage == 2) {
			stage2();
		} else if (stage == 3) {
			stage3();
		} else if (stage == 4) {
			stage4();
		} else if (stage == 5) {
			stage5();
		} else if (stage == 6) {
			stage6();
		} else if (stage == 7) {
			stage7();
		} else if (stage == 8) {
			stage8();
		} else if (stage == 9) {
			stage9();
		} else if (stage == 10) {
			stage10();
		} else if (stage == 11) {
			stage11();
		} else if (stage == 12) {
			stage12();
		} else if (stage == 13) {
			stage13();
		} else if (stage == 14) {
			stage14();
		} else if (stage == 15) {
			stage15();
		} else if (stage == 16) {
			stage16();
		} else if (stage == 17) {

		} else if (stage == 18) {
			stage18();
		} else if (stage == 19) {
			stage19();
		} else if (stage == 20) {
			stage20();
		} else if (stage == 80) {

			softwareHouse.logOut();
			stage = -1;
			space();

		} else if (stage == 99) {
			exitProgram = true;
			println("Ciao.");
		} else {
			commandError = true;
			stage = 0;
		}

	}

	public void options() {
		println("\nPress 0 to go back.\n" + "Press " + stage + " to repeat action.\n");
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
				+ "Press 5 to edit project timeframe.\n" + "Press 6 to give a project a title.\n" + "\n"
				+ "Press 7 to create activity.\n" + "Press 8 to view activity timeframe\n"
				+ "Press 9 to edit activity timeframe\n" + "Press 10 to add worker to activity\n"
				+ "Press 11 to check workers availabilities.\n" + "\n" + "Press 12 to fill timesheet.\n"
				+ "Press 13 to seek assistance.\n" + "Press 14 to fill assistance timesheet.\n" + "\n"
				+ "Press 15 to see all projects.\n" + "Press 16 to see all activities of a project.\n" + "\n"
				+ "Press 18 to give worker sick leave, holliday, ect.\n" + "\n" + "Press 19 to get week report.\n"
				+ "\n" + "Press 80 to log out.\n" + "\n" + "Press 99 to exit program\n");

		if (commandError) {
			System.out.println("Command not recognized");
			commandError = false;
		}

		stage = scanner.nextInt();

		space();
	}

	// Create worker
	public void stage1() {
		println("Enter ID of worker you wish to create : ");
		try {
			softwareHouse.createWorker(scanner.next());
		} catch (Exception e) {
			System.out.println(e);
		}
		options();
	}
	
	// Create project
	public void stage2() {
		Date[] SE = startAndEndDate();
		try {
			SE[0].checkChronology(SE[1]);
			softwareHouse.createProject(SE[0], SE[1]);
		} catch (Exception e) {
			System.out.println(e);
		}
		space();
		options();
	}
	
	// Appoint project leader
	public void stage3() {
		println("Enter ID of worker you wish to appoint :");
		String worker = scanner.next();
		println("Enter ID of project you wish to appoint worker " + worker + " as leader of :");
		String project = scanner.next();
		try {
			softwareHouse.getProject(project).appointProjectLeader(softwareHouse.getWorker(worker));
		} catch (Exception e) {
				System.out.println(e);
		}
		space();
		options();
	}
	
	// View project timeframe
	public void stage4() {
		println("Enter ID of project you wish to see timeframe of :");
		String projectID = scanner.next();
		try {
			println("Start date : " + softwareHouse.getProject(projectID).getStartDate().print());
			println("End date : " + softwareHouse.getProject(projectID).getEndDate().print());

		} catch (Exception e) {

			System.out.println(e);
		}

		space();
		options();
	}

	// Edit project timeframe
	public void stage5() {
		println("Enter project ID of project you wish to edit timeframe of : ");
		String projectID = scanner.next();
		Date[] SE = startAndEndDate();

		try {

			SE[0].checkChronology(SE[1]);
			softwareHouse.getProject(projectID).setEndDate(SE[1]);
			softwareHouse.getProject(projectID).setStartDate(SE[0]);

		} catch (Exception e) {
			System.out.print(e);
		}

		space();
		options();
	}
	
	// Give project a title
	public void stage6() {
		println("Enter ID of project you wish to give a title to : ");
		String projectID = scanner.next();
		println("Enter title : ");
		String title = scanner.next();
		try {
			softwareHouse.getProject(projectID).setProjectTitle(title);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		space();
		options();
	}

	// Create activities
	public void stage7() {
		println("Enter ID of project you wish to create an activity for : ");
		String projectID = scanner.next();
		println("Enter number of activities you wish to create : ");
		int nbActivities = scanner.nextInt();
		for (int i = 0; i < nbActivities; i++) {
			Date[] SE = startAndEndDate();
			println("Enter title of activity : ");
			String activityTitle = scanner.next();
			try {
				softwareHouse.getProject(projectID).createActivity(activityTitle, SE[0], SE[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			println("");
		}
		space();
		options();
	}
	
	// View activity timeframe
	public void stage8() {
		println("Enter ID of project of activity you wish to view timeframe of : ");
		String projectID = scanner.next();
		println("Enter title of the activity : ");
		String title = scanner.next();
		try {
			println("Start date : " + softwareHouse.getProject(projectID).getActivity(title).getStartDate().print());
			println("End date : " + softwareHouse.getProject(projectID).getActivity(title).getEndDate().print());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		space();
		options();
	}

	// Edit activity timeframe

	public void stage9() {
		println("Enter ID of project you wish to edit activity timeframe of : ");
		String projectID = scanner.next();
		println("Enter number of activities you wish to edit timeframe of : ");
		int nbEdits = scanner.nextInt();
		for (int i = 0; i < nbEdits; i++) {
			println("Enter title of relevant activity :");
			String activityTitle = scanner.next();
			Date[] SE = startAndEndDate();
			try {
				SE[0].checkChronology(SE[1]);
				softwareHouse.getProject(projectID).getActivity(activityTitle).setStartDate(SE[0]);
				softwareHouse.getProject(projectID).getActivity(activityTitle).setEndDate(SE[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block

				System.out.println(e);
			}
			println("");
		}

		space();
		options();
	}

	// Add workers to activities
	public void stage10() {
		println("Enter ID of project that contains the activities you wish to add workers to :");
		String projectID = scanner.next();
		println("Enter number of activities you wish to add workers to :");
		int nbActivities = scanner.nextInt();
		for (int i = 0; i < nbActivities; i++) {
			println("Enter title of activity you wish to add workers to :");
			String activityTitle = scanner.next();
			println("Enter number of workers you wish to add : ");
			int nbWorkers = scanner.nextInt();
			for (int j = 0; j < nbWorkers; j++) {
				println("Enter ID of worker you wish to add to activity '" + activityTitle + "' :");
				String workerID = scanner.next();
				try {
					softwareHouse.getProject(projectID).getActivity(activityTitle)
							.assignWorker(softwareHouse.getWorker(workerID));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		}
		space();
		options();
	}

	
	// Check workers availabilities

	public void stage11() {
		println("Enter start and end dates of period you wish to see how many activities each worker is working on :");
		Date[] SE = startAndEndDate();
		try {
			SE[0].checkChronology(SE[1]);
			softwareHouse.getAllWorkersActivities(SE[0], SE[1]);
		} catch (Exception e) {
			System.out.println(e);
		}
		space();
		options();
	}
	
	// Fill timesheets
	public void stage12() {
		println("Enter project ID of activity you wish to fill timesheet of :");
		String projectID = scanner.next();
		println("Enter title of relevant activity :");
		String activityTitle = scanner.next();
		Date date = enterDate(0);
		println("Enter number of hours worked :");
		int hours = scanner.nextInt();
		println("Enter number of minutes :");
		int minutes = scanner.nextInt();
		try {
			softwareHouse.getProject(projectID).getActivity(activityTitle)
					.inputWorkTime(softwareHouse.getWorkerLoggedIn(), hours, minutes, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		space();
		options();
	}

	public void stage13() {
		// REQUEST ASSISTANCE NOT IMPLEMENTED :(
	}

	
	// Fill assistance timesheet
	public void stage14() {
		println("Enter project ID of activity you've seeked help for : ");
		String projectID = scanner.next();
		println("Enter title of relevant activity :");
		String activityTitle = scanner.next();
		println("Enter ID of worker who helped you :");
		String helper = scanner.next();
		Date date = enterDate(0);
		println("Enter number of hours :");
		int hours = scanner.nextInt();
		println("Enter number of minutes :");
		int minutes = scanner.nextInt();
		try {
			softwareHouse.getProject(projectID).getActivity(activityTitle).inputAssistance(
					softwareHouse.getWorkerLoggedIn(), softwareHouse.getWorker(helper), hours, minutes, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		space();
		options();
	}
	
	// View all projects
	public void stage15() {
		for (Project p : softwareHouse.getListOfProjects()) {
			String project = String.format("Project ID : %s    Title : %s     Start date : %s       End date : %s",
					p.getID(), p.getTitle(), p.getStartDate().print(), p.getEndDate().print());
			System.out.println(project);
		}
		space();
		options();
	}
	
	// View activities of a project
	public void stage16() {
		println("Enter ID of project you wish to see activities of :");
		String projectID = scanner.next();
		println("Activities of project " + projectID + " :\n");
		try {
			for (Activity a : softwareHouse.getProject(projectID).getActivities()) {
				println("Activity " + a.getTitle() + " :  Start date : " + a.getStartDate().print() + "    End Date : "
						+ a.getEndDate().print());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		space();
		options();
	}
	
	// Set worker unavailable
	public void stage18() {
		println("Enter ID of worker you wish to set unavailable :");
		String workerID = scanner.next();
		println("Enter timeframe where worker " + workerID + " will be unavailable :");
		Date[] SE = startAndEndDate();
		try {
			SE[0].checkChronology(SE[1]);
			softwareHouse.getWorker(workerID).setUnavailable(SE[0], SE[1]);
		} catch (Exception e) {
			System.out.println(e);
		}
		space();
		options();
	}

	// Print activity week report
	public void stage19() {
		println("Enter project ID of activity you wish to print week report for :");
		String projectID = scanner.next();
		println("Enter title of relevant activity :");
		String activityTitle = scanner.next();
		Date date = enterDate(0);
		try {
			softwareHouse.getProject(projectID).getActivity(activityTitle).printWeekReport(date);
		} catch (Exception e) {
			System.out.println(e);
		}
		space();
		options();
	}
	
	// Print project week report
	public void stage20() {
		println("Enter ID of project you wish to print week report for :");
		String projectID = scanner.next();
		Date date = enterDate(0);
		try {
			softwareHouse.getProject(projectID).printWeekReport(date);
		} catch (Exception e) {
			System.out.println(e);
		}
		space();
		options();
	}
	
	// Print info of all projects
	public void stage21() {
		printAllProjectsInfo();
		space();
		options();
	}

	public Date[] startAndEndDate() {
		Date[] SE = new Date[2];
		SE[0] = enterDate(1);
		SE[1] = enterDate(2);
		return SE;
	}

	public Date enterDate(int scenario) {
		String startOrEnd;
		if (scenario == 0) {
			startOrEnd = "";

		} else if (scenario == 1) {
			startOrEnd = " start";
		} else {
			startOrEnd = " end";
		}

		Scanner scanner = new Scanner(System.in);

		Date date = new Date();
		System.out.println("Please enter" + startOrEnd + " date in the following format: (yyyy - weeknumber)");

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

	public void printAllProjectsInfo() {

		System.out.println("LIST OF ALL PROJECTS AND CORRESPONDING ACITIVITIES");
		System.out.println("_____________________________________");

		for (Project p : softwareHouse.getListOfProjects()) {
			String projectInfo = String.format("Project:\n " + "%s ID: %s Date: %s - %s", p.getTitle(), p.getID(),
					p.getStartDate().print(), p.getEndDate().print() + "\n - - - - - - - - \n" + "Activities:");

			System.out.println(projectInfo);
			for (Activity a : p.getActivities()) {
				String activityInfo = String.format("%s Date: %s-%s", a.getTitle(), a.getStartDate().print(),
						a.getEndDate().print());

				System.out.println(activityInfo);
			}
			System.out.println("_____________________________________");
		}

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
		project1.setProjectTitle("SecretProject");
		project2.appointProjectLeader(worker1);

		softwareHouse.logIn(worker1.getID());
//		Activities
		project1.createActivity("HideIP", project1.getStartDate(), project1.getEndDate());
		project1.createActivity("Research", new Date(2025, 7), new Date(2025, 8));
		project1.createActivity("WireMoney", new Date(2025, 30), new Date(2025, 32));

		Activity activity1 = project1.getActivities().get(0);
		Activity activity2 = project1.getActivities().get(1);
		Activity activity3 = project1.getActivities().get(2);

//		Expected time
		activity1.setExpectedWorkingHours(80);
		activity2.setExpectedWorkingHours(150);
		activity3.setExpectedWorkingHours(120);
//		Worker added to activity
		try {
			activity1.assignWorker(worker1);
			activity1.assignWorker(worker2);
			activity1.assignWorker(worker3);
			activity1.assignWorker(worker4);

			activity2.assignWorker(worker1);
			activity2.assignWorker(worker2);

			activity3.assignWorker(worker1);
			activity3.assignWorker(worker3);
			activity3.assignWorker(worker4);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		Add time sheets for workers

		try {
			activity1.inputWorkTime(worker1, 10, 30, new Date(2025, 12));
			
			activity2.inputWorkTime(worker2, 50, 30, new Date(2025,8));

			activity3.inputWorkTime(worker1, 40, 0, new Date(2025, 30));
			activity3.inputWorkTime(worker4, 20, 0, new Date(2025,31));

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Activity> arr = project1.getActivities();
		Random r = new Random();

//		GENERATES INPUTWORKEDHOURS FOR EVERY ACTIVITY

		for (int i = 0; i < 5; i++) {
			for (Activity a : arr) {
				try {
					a.inputWorkTime(worker1, r.nextInt(20), 30, new Date(2025, ThreadLocalRandom.current()
							.nextInt(a.getStartDate().getWeekNumber(), (a.getEndDate().getWeekNumber() + 1))));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Fairly good example to show weekreport
		try {
			project1.printWeekReport(new Date(2025, 12));
//			activity3.printWeekReport(new Date(2025, 31));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		softwareHouse.logOut();

//		softwareHouse.getAllWorkersActivities(new Date(2025,8), new Date(2045,12));
	}

	public Date enterDate() throws IllegalArgumentException {

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

		return date;

	}

}
