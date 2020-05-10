
package acceptance_test.steps;

import org.junit.Test;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.WeekReport;
import app.Worker;
import designPatterns.Date;

/**
*@author Martin
*/

public class weekReport_generalTest {
		private SoftwareHouse softwareHouse;
		private Activity activity;
		private Project project;
		private Worker worker;
		/*
		 * Class only used to test the print funtion of weekreport
		 * Therefore no assertStatements, since the output of interest is printed in console
		 */

	public weekReport_generalTest() throws Exception {
		SoftwareHouse.deleteSoftwareHouse();
		softwareHouse = SoftwareHouse.getInstance();
		softwareHouse.createWorker("oooo");
		worker = softwareHouse.getWorkerByIndex("oooo");
		softwareHouse.createProject(new Date(2020,10), new Date(2020,14));
		project = softwareHouse.getListOfProjects().get(0);
		project.appointProjectLeader(softwareHouse.getWorkerByIndex("oooo"));
		softwareHouse.logIn("oooo");
		project.createActivity("Activity", new Date(2020,10), new Date(2020,14));
		
		try {
			activity = softwareHouse.getListOfProjects().get(0).getActivity("Activity");
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		activity.assignWorker(worker);
		activity.inputWorkTime(worker, 8, 30, new Date(2020,12));
	}
	
	@Test
	public void printTest() {
		WeekReport week = activity.generateWeekReport(new Date(2020,12));
		week.printWeekReport();
	
	}
	@Test
	public void printProjectTest() {
		
		WeekReport week2 = null;

		week2 = project.generateWeekReport(new Date(2020,14));
		week2.printWeekReport();

	}
	
}
