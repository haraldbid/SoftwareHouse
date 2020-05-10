package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class Activity_whitebox {	
	private SoftwareHouse softwareHouse;
	private Project project;
	private Activity activity;
	private Worker worker_aa;
	
	public Activity_whitebox() {
		softwareHouse = new SoftwareHouse();
		softwareHouse.createWorker("aa");
		worker_aa = softwareHouse.getWorkerByIndex("aa");
		
		softwareHouse.createProject(new Date(2020,10),new Date(2020,14));
		project = softwareHouse.getListOfProjects().get(0);
		project.appointProjectLeader(worker_aa);
		
		softwareHouse.logIn("aa");
		
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
	public void testInputA() {
		
		activity.assignWorker(worker_aa);
		
		String eMeg = null;
		try {
			activity.inputWorkTime(worker_aa, -5, 20, new Date(2020,12));			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(eMeg.equals("Only positive work time"));
	}
	@Test
	public void testInputB() {

		activity.assignWorker(worker_aa);
		String eMeg = null;
		try {
			activity.inputWorkTime(worker_aa, 5, -20, new Date(10,2020));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(eMeg.equals("Only positive work time"));
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
	public void testInputD() {
	
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
