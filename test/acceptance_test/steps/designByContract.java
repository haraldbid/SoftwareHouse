package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class designByContract {
	
	
	@Test
	public void addTimeContract() {

		int minutes = 30;
		int hours = 8;
		String ID = "bbb";
		
		SoftwareHouse softwareHouse = new SoftwareHouse();
		Activity activity = new Activity("Test",new Date(10,20),new Date(12,20));
		softwareHouse.createWorker(ID);
		activity.assignWorker(softwareHouse.getWorkerByIndex(ID));
		
		activity.inputWorkTime(softwareHouse.getWorkerByIndex(ID), hours, minutes, new Date(11,20));
		
		// Pre AssertStatements
		assertTrue(minutes < 60);
		assertTrue(softwareHouse.getWorkerByIndex(ID) != null);
		assertTrue(activity != null);
		//Post Assert statements
		assertTrue(activity.getTimeSheets().get(activity.getTimeSheets().size()-1).getWorker().getID() == ID);
		assertTrue(activity.getTimeSheets().get(activity.getTimeSheets().size()-1).getHoursWorked() == hours);
		assertTrue(activity.getTimeSheets().get(activity.getTimeSheets().size()-1).getMinutesWorked()- hours*60 == minutes);
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
	
			//Pre Assert Statements
			assertTrue(softwareHouse!= null);
			//Post Assert Statements
			assertTrue(softwareHouse.getWorkerByIndex(ID).getID() instanceof String);
			assertTrue(softwareHouse.getWorkerByIndex(ID).getID().length() > 0);
			assertTrue(softwareHouse.getWorkerByIndex(ID).getID().length() != 0);
			assertTrue(softwareHouse.getWorkerByIndex(ID) != null);
			int idCount = 0;
			for (Worker worker : softwareHouse.getListOfWorkers()) {
				if(worker.getID().equals(ID))idCount++;
			}
			assertTrue(idCount == 1); // Only one worker with given ID
	}

}
