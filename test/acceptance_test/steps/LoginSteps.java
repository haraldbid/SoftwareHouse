package acceptance_test.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import app.SoftwareHouse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSteps {

	
	private SoftwareHouse softwarehouse;
	
	@Given("that no user is logged in")
	public void thatNoUserIsLoggedIn() {
		SoftwareHouse.deleteSoftwareHouse();
		softwarehouse = SoftwareHouse.getInstance();
	    assertFalse(softwarehouse.loggedIn());
	}
	
	@And("user {string} enters ID {string}") 
	public void userEntersID(String string1, String string2){
		softwarehouse.createWorker(string1);
		try{
			softwarehouse.logIn(string2);
		}catch(Exception e) {}
	}
	@Then("user is logged in")
	public void userIsLoggedIn() {
		assertTrue(softwarehouse.loggedIn());
	}
	@Then("no user is logged in")
	public void userIsNotLoggedIn() {
		assertFalse(softwarehouse.loggedIn());
	}
	
	
}
