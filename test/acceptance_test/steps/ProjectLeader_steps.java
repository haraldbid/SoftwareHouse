package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import app.SoftwareHouse;
import app.Worker;
import designPatterns.Date;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ProjectLeader_steps {
	
	private SoftwareHouse shouse;
	private String error_mg;
	
	@Given("that a project {string} exist")
	public void thatAProjectExist(String string) {
		SoftwareHouse.deleteSoftwareHouse();
	    shouse = SoftwareHouse.getInstance();
	    shouse.createProject(new Date(2020,10), new Date(2020,15));
	    assertTrue(shouse.getListOfProjects().size()>0);
	
	}

	@Given("a worker with id {string} exist")
	public void aWorkerWithIdExist(String string) {
	   
		shouse.createWorker(string);
		assertTrue(shouse.getListOfWorkers().size()>0);
	}

	@Given("project has no project leader")
	public void projectHasNoProjectLeader() {
	    assertTrue(!shouse.getListOfProjects().get(0).hasProjectLeader());
	}

	@Then("{string} is assigned as projectleader")
	public void isAssignedAsProjectleader(String string) {
		
		try {
			shouse.getListOfProjects().get(0).appointProjectLeader(shouse.getWorkerByIndex(string));			
		} catch (Exception e) {
			error_mg = e.getMessage();
		}
	    assertTrue(shouse.getListOfProjects().get(0).hasProjectLeader());
	}

	@Given("project has a project leader")
	public void projectHasAProjectLeader() {
	    assertTrue(!shouse.getListOfProjects().get(0).hasProjectLeader());
	    shouse.getListOfProjects().get(0).appointProjectLeader(new Worker("aaa"));
	    
	}

	@Then("Error message {string} is displayed")
	public void errorMessageIsDisplayed(String string) {
	    assertTrue(error_mg.equals(string));
		
		
	}
	
}
