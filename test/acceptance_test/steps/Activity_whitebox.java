package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class Activity_whitebox {
	
	//---------Example for reference----------
//	@Test(expected = Error.class)
//	public void testInputDataSetA() {
//		int[] ar = {};
//		sut.minmax(ar);}
//	
//	@Test
//	public void testInputDataSetB() {
//		int[] ar = {17};
//		sut.minmax(ar);
//		assertEquals(17,sut.getMin());
//		assertEquals(17,sut.getMax());
//	}
	
	//Input assistance: negative time 
	@Test
	public void testInputA() {
		Worker worker = new Worker(new SoftwareHouse(),"aa");
		Activity activity = new Activity("testAc", new Date(10,2020), new Date(11,2020));
		activity.assignWorker(worker);
		String eMeg = null;
		try {
			activity.inputWorkTime(worker, -5, 20, new Date(10,2020));			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(eMeg.equals("Only positive work time"));
	}
	@Test
	public void testInputB() {
		Worker worker = new Worker(new SoftwareHouse(),"aa");
		Activity activity = new Activity("testAc", new Date(10,2020), new Date(11,2020));
		activity.assignWorker(worker);
		String eMeg = null;
		try {
			activity.inputWorkTime(worker, 5, -20, new Date(10,2020));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(eMeg.equals("Only positive work time"));
	}
	@Test
	public void testInputC() {
		Worker worker = new Worker(new SoftwareHouse(),"aa");
		Activity activity = new Activity("testAc", new Date(10,2020), new Date(11,2020));
		String eMeg = null;
		try {
			activity.inputWorkTime(worker, 5, 20, new Date(10,2020));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(eMeg.equals("Worker is not assigned to activity"));
	}
	@Test
	public void testInputD() {
		Worker worker = new Worker(new SoftwareHouse(),"aa");
		Activity activity = new Activity("testAc", new Date(10,2020), new Date(11,2020));
		activity.assignWorker(worker);
		String eMeg = null;
		try {
			activity.inputWorkTime(worker, 5, 20, new Date(10,2020));			
		} catch (Exception e) {
			eMeg = e.getMessage();
		}
		assertTrue(activity.getTimeSheets().size()>0);
	}
}
