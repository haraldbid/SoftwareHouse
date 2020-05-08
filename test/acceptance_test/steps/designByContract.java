package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class designByContract {
	// https://www.youtube.com/watch?v=4CNrRcc8Cwc
	
	@Test
	public void addTimeContract() throws Exception {

		int minutes = 30;
		int hours = 8;
		String ID = "bbb";
		Activity activity = null;
		SoftwareHouse softwareHouse = new SoftwareHouse();
		softwareHouse.createProject(new Date(2020,10), new Date(2020,12));
		softwareHouse.createWorker("aaa");
		Project p = softwareHouse.getListOfProjects().get(0);
		p.appointProjectLeader(softwareHouse.getWorkerByIndex("aaa"));
		softwareHouse.logIn("aaa");
		p.createActivity("Test",new Date(2020,10),new Date(2020,12));
		activity = p.getActivity("Test");
		softwareHouse.createWorker(ID);
		activity.assignWorker(softwareHouse.getWorkerByIndex(ID));
		
		activity.inputWorkTime(softwareHouse.getWorkerByIndex(ID), hours, minutes, new Date(11,2020));
		
	}
	
	@Test
	public void workerContract() {

		String ID = "aaa";
		String ID2= "bbb";
		String ID3 = "ddd";
		String ID4= "ccc";
		SoftwareHouse softwareHouse = new SoftwareHouse();
		
		softwareHouse.createWorker(ID);
		softwareHouse.createWorker(ID2);
		softwareHouse.createWorker(ID3);
		softwareHouse.createWorker(ID4);
	

	}
	@Test
	public void logInContract() {

		String ID = "aaa";

		SoftwareHouse softwareHouse = new SoftwareHouse();
		softwareHouse.createWorker(ID);
		softwareHouse.logIn(ID);
	

	}
}