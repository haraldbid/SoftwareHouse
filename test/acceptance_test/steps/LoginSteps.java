package acceptance_test.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import app.SoftwareHouse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	
	
	
	private SoftwareHouse softwarehouse = new SoftwareHouse();
	
	
	@Given("that no user is logged in")
	public void thatNoUserIsLoggedIn() {
	    assertFalse(softwarehouse.loggedIn());
	}
	@And("user {string} enters ID {string}") 
	public void userEntersID(String string1, String string2){
		softwarehouse.createWorker(string1);
	    // Write code here that turns the phrase above into concrete actions
		try{
			softwarehouse.logIn(string2);
		}catch(Exception e) {}
	}
	@Then("user is logged in")
	public void userIsLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(softwarehouse.loggedIn());
	}
	@Then("no user is logged in")
	public void userIsNotLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(softwarehouse.loggedIn());
	}
	
	
}
