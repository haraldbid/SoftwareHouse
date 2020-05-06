package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class designByContract {
	// https://www.youtube.com/watch?v=4CNrRcc8Cwc
	
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

}
