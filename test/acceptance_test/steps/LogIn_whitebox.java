package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import app.SoftwareHouse;

/**
*@author Nicklas
*/

public class LogIn_whitebox {
	
	@Test
	public void testInputA() {
		SoftwareHouse.deleteSoftwareHouse();
		SoftwareHouse softwarehouse = SoftwareHouse.getInstance();
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
		SoftwareHouse.deleteSoftwareHouse();
		SoftwareHouse softwarehouse = SoftwareHouse.getInstance();
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
		SoftwareHouse.deleteSoftwareHouse();
		SoftwareHouse softwarehouse = SoftwareHouse.getInstance();
		softwarehouse.createWorker("aaa");
		String eMeg = null;
		try {
			softwarehouse.logIn("aaa");			
		} catch (Exception e) {
			eMeg = e.getMessage(); 
		}
		assertTrue(softwarehouse.loggedIn());
	}
	
}
