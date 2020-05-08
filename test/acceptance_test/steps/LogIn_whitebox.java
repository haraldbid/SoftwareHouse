package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Activity;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;

public class LogIn_whitebox {
	@Test
	public void testInputA() {
		SoftwareHouse softwarehouse = new SoftwareHouse();
		String eMeg = null;
		try {
			softwarehouse.logIn("aaa");			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(eMeg.equals("Login failed."));
	}
	@Test
	public void testInputB() {
		SoftwareHouse softwarehouse = new SoftwareHouse();
		softwarehouse.createWorker("bbb");
		String eMeg = null;
		try {
			softwarehouse.logIn("aaa");			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(eMeg.equals("Login failed."));
	}
	@Test
	public void testInputC() {
		SoftwareHouse softwarehouse = new SoftwareHouse();
		softwarehouse.createWorker("aaa");
		String eMeg = null;
		try {
			softwarehouse.logIn("aaa");			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(softwarehouse.loggedIn());
	}
	
	public static boolean assertionsDisabled() {
	    return !SoftwareHouse.class.desiredAssertionStatus();
	}
}
