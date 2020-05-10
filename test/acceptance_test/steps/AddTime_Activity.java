package acceptance_test.steps;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class AddTime_Activity {
	private static SoftwareHouse softwarehouse;
	private static Activity activity;
	private static Worker worker;
	private static String error;

	
	@Given("that an activity {string} exist")
	public void that_an_activity_exist(String string) {
	    // Write code here that turns the phrase above into concrete actions
		SoftwareHouse.deleteSoftwareHouse();
		softwarehouse = SoftwareHouse.getInstance();
		softwarehouse.createProject(new Date(2020,10), new Date(2020,12));
		softwarehouse.createWorker("m");
		Project p = softwarehouse.getListOfProjects().get(0);
//		System.out.print(p.getProjectLeader().getID());
		p.appointProjectLeader(softwarehouse.getWorkerByIndex("m"));
		try {
			
			softwarehouse.logIn("m");
			p.createActivity(string,new Date(2020,10),new Date(2020,12));
			activity = p.getActivity(string);
		} catch (Exception e) {}
	}

	@Given("a worker {string} exist")
	public void a_worker_exist(String string) {
	    // Write code here that turns the phrase above into concrete actions
		softwarehouse.createWorker(string);
		worker = softwarehouse.getWorkerByIndex(string);
	}

	@Given("worker {string} is assigned to activity")
	public void worker_is_assigned_to_activity(String string){
	    // Write code here that turns the phrase above into concrete actions
		try {
			activity.assignWorker(worker);
		} catch (Exception e) {}
//	    assertTrue(activity.searchWorker(string).getID().equals(string));
	}

	@Then("timesheet with {string} and {string} is added to list in {string}")
	public void timesheet_with_and_is_added_to_list_in(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    try {
			activity.inputWorkTime(worker, 8, 1,new Date(2020,10));
		} catch (Exception e) {	}
	    SoftwareHouse.deleteSoftwareHouse();
	}
	
	@Given("worker {string} is not assigned to activity")
	public void worker_is_not_assigned_to_activity(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(activity.searchWorker(string)!= null);
	}
	@When("time is added for worker {string}")
	public void time_is_added_for_worker(String string) throws IllegalArgumentException{
	    // Write code here that turns the phrase above into concrete actions
	    try {
	    	activity.inputWorkTime(worker, 8, 12,new Date(2020,10));			
		} catch (Exception e) {
			error = e.getMessage();
		}
	}
	
	@Then("the error message: {string} is displayed")
	public void the_error_message_is_displayed(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(error.equals(string));
		SoftwareHouse.deleteSoftwareHouse();
	}
	
}
