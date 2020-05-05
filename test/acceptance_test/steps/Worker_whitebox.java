package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.SoftwareHouse;
import app.Worker;

public class Worker_whitebox {
	
	@Test
	public void testInputA() {
		String ID = "";
		String E_meg = "";
		try {
			Worker worker = new Worker(new SoftwareHouse(), ID);			
		} catch (Exception e) {
			E_meg = e.getMessage();
		}
		assertTrue(E_meg.equals("ID is at minimum 1 and maximum 4 letters long."));
		
	}
	@Test
	public void testInputB() {
		String ID = "abcdefg";
		String E_meg = "";
		try {
			Worker worker = new Worker(new SoftwareHouse(), ID);			
		} catch (Exception e) {
			E_meg = e.getMessage();
		}
		assertTrue(E_meg.equals("ID is at minimum 1 and maximum 4 letters long."));
		
	}
	@Test
	public void testInputC() {
		String ID = "123";
		String E_meg = "";
		try {
			Worker worker = new Worker(new SoftwareHouse(), ID);			
		} catch (Exception e) {
			E_meg = e.getMessage();
		}
		assertTrue(E_meg.equals("ID must be composed of letters."));
		
	}
	@Test
	public void testInputD() {
		SoftwareHouse house = new SoftwareHouse();
		String ID = "abc";
		house.createWorker(ID);	
		String E_meg = "";
		try {
			house.createWorker(ID);			
		} catch (Exception e) {
			E_meg = e.getMessage();
		}
		assertTrue(E_meg.equals("ID is already used by another worker."));
		
	}
	@Test
	public void testInputE() {
		SoftwareHouse house = new SoftwareHouse();
		String ID = "abc";
		
		String E_meg = "";
		try {
			house.createWorker(ID);			
		} catch (Exception e) {
			E_meg = e.getMessage();
		}
		assertTrue(house.getListOfWorkers().size()>0);
		
	}
}
