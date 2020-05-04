package acceptance_test.steps;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import app.Activity;
import app.SoftwareHouse;
import app.Worker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class AddTime_Activity {

	private Activity activity;
	private Worker worker;
	private String error;
//	public static Activity activity;
	public AddTime_Activity() {
//		Activity ac = new Activity("activity1");
//		ac.assignWorker(new Worker(new SoftwareHouse(), "aa"));
	}
	@Given("that an activity {string} exist")
	public void thatnActivityExist(String string) {
		activity = new Activity(string);
	}

	@Given("a worker {string} exist")
	public void a_worker_exist(String string) {
	    // Write code here that turns the phrase above into concrete actions
		worker = new Worker(new SoftwareHouse(),string);
	}

	@Given("worker {string} is assigned to activity")
	public void worker_is_assigned_to_activity(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    activity.assignWorker(worker);
	    assertTrue(activity.searchWorker(string).getID().equals(string));
	}

	@Then("timesheet with {string} and {string} is added to list in {string}")
	public void timesheet_with_and_is_added_to_list_in(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    activity.inputWorkTime(worker, 8, 1);
	    
	}
	
	@Given("worker {string} is not assigned to activity")
	public void worker_is_not_assigned_to_activity(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(activity.searchWorker("bb")!= null);
	}
	@When("time is added for worker {string}")
	public void time_is_added_for_worker(String string) throws IllegalArgumentException{
	    // Write code here that turns the phrase above into concrete actions
	    try {
	    	activity.inputWorkTime(worker, 8, 12);			
		} catch (Exception e) {
			error = e.getMessage();
		}
	}

	@Then("the error message: {string} is displayed")
	public void the_error_message_is_displayed(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(error.equals(string));
	}
	
}