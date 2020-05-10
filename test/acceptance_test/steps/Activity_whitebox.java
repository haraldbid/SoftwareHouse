package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

/**
*@author Harald
*/

public class Activity_whitebox {	
	private SoftwareHouse softwareHouse;
	private Project project;
	private Activity activity;
	private Worker worker_aa;
	
	public Activity_whitebox() {
			SoftwareHouse.deleteSoftwareHouse();
			softwareHouse = SoftwareHouse.getInstance();
			softwareHouse.createWorker("ffff");
			worker_aa = softwareHouse.getWorkerByIndex("ffff");
			
			softwareHouse.createProject(new Date(2020,10),new Date(2020,14));
			project = softwareHouse.getListOfProjects().get(0);
			project.appointProjectLeader(worker_aa);
			
			softwareHouse.logIn("ffff");
			
			project.createActivity("testAC", new Date(2020,10), new Date(2020,14));
			try {
				activity = project.getActivity("testAC");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
	}
	//Input assistance: negative time 
	@Test
	public void testInputA()  {

		try {
			activity.assignWorker(worker_aa);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String eMeg = null;
		try {
			activity.inputWorkTime(worker_aa, -5, 20, new Date(2020,12));			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(eMeg.equals("Invalid input"));

	}
	@Test
	public void testInputB() {
		
//		try {
//			activity.assignWorker(worker_aa);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			
//		}
		String eMeg = null;
		try {
			activity.inputWorkTime(worker_aa, 5, -20, new Date(2020,12));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(eMeg.equals("Invalid input"));


	}
	@Test
	public void testInputC() {

		String eMeg = null;
		try {
			activity.inputWorkTime(worker_aa, 5, 20, new Date(2020,12));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(eMeg.equals("Worker is not assigned to activity"));


	}
	@Test
	public void testInputD() throws Exception {
	
		activity.assignWorker(worker_aa);
		String eMeg = null;
		try {
			activity.inputWorkTime(worker_aa, 5, 20, new Date(2020,12));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(activity.getTimeSheets().size()>0);


	}
}
