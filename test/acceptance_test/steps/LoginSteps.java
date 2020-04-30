package acceptance_test.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import app.SoftwareHouse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	
	
	
	private SoftwareHouse softwareHouse;

	public LoginSteps(SoftwareHouse softwareHouse) {
		this.softwareHouse = softwareHouse;
	}
	
	
	
}
