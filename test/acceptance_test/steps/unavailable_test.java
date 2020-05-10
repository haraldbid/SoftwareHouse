package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import app.Activity;
import app.Project;
import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class unavailable_test {
	private SoftwareHouse softwareHouse;
	private Activity activity;
	private Project project;
	private Worker worker;
	private int numAct;
	
	
	@Given("that a worker is assigned to an activity")
	public void workerIsAssigned() {
		
		SoftwareHouse.deleteSoftwareHouse();
		softwareHouse = SoftwareHouse.getInstance();
		softwareHouse.createWorker("llll");
		
		softwareHouse.createProject(new Date(2020,10), new Date(2020,20));
		project = softwareHouse.getListOfProjects().get(0);
		project.appointProjectLeader(softwareHouse.getWorkerByIndex("llll"));
		softwareHouse.logIn("llll");
		project.createActivity("Activity", new Date(2020,12), new Date(2020,14));
		softwareHouse.createWorker("zzzz");
		worker = softwareHouse.getWorkerByIndex("zzzz");
		try {
			activity = project.getActivity("Activity");
			activity.assignWorker(worker);
		} catch (Exception e) {	}
		softwareHouse.logOut();
		numAct = worker.getNumActivities(new Date(2020,10), new Date(2020,20));
		
	}
	@And("the worker becomes unavailable")
	public void workerUnavailable() {
		worker.setUnavailable(new Date(2020,11), new Date(2020,15));
	
	}
	@And("the activity is while the worker is unavailable")
	public void activityDuringUnavailable() {
	    activity.setStartDate(new Date(2020,12));
	    activity.setEndDate(new Date(2020,14));
	}
	@Then("the worker is unassigned from the activity")
	public void removeActivity() {
	    assertTrue(numAct > worker.getNumActivities(new Date(2020,10), new Date(2020,20)));
	    
	}
	@And("the activity is not while the worker is unavailable")
	public void activityNotDuringUnavailable() {
		activity.setStartDate(new Date(2020,16));
	    activity.setEndDate(new Date(2020,18));
	
	}
	@Then("the worker is still assigned to the activity")
	public void stillAssigned() {
		
		 assertTrue(numAct == worker.getNumActivities(new Date(2020,10), new Date(2020,20)));
		
	}
	
	
	

}
