package acceptance_test.steps;

import static org.junit.Assert.assertTrue;

import app.SoftwareHouse;
import designPatterns.Date;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addProject {
	
	private SoftwareHouse softwareHouse;
	private String ID ;

	
	@When("a project with name {string} is created")
	public void aProjectWithNameIsCreated(String string) {
		this.softwareHouse = new SoftwareHouse();
		this.softwareHouse.createProject(new Date(2020,10), new Date(2020,14));
		this.softwareHouse.getListOfProjects().get(0).setProjectTitle(string);
	}
	
	@Given("that a proctID is generated with the date and running number")
	public void thatAProctIDIsGeneratedWithTheDateAndRunningNumber() {
	    ID = softwareHouse.getListOfProjects().get(0).getID();
		assertTrue(ID != null);
	}
	@Given("project has title {string}")
	public void projectHasTitle(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(softwareHouse.getProjectByID(ID).hasProjectTitle());
	}
	@Then("{string} exists in array")
	public void existsInArray(String string) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertTrue(softwareHouse.getProjectByID(ID).getID().equals(ID));
	}
	
	@Given("the start Date is changed")
	public void theStartDateIsChanged() {
		Date start = new Date(21,10);
		Date end = new Date(21,14);
	    softwareHouse.getProjectByID("200000").setStartDate(start.getYear(),start.getWeekNumber());;
	    softwareHouse.getProjectByID("210000").setEndDate(end.getYear(), end.getWeekNumber());
	    
	    assertTrue(softwareHouse.getProjectByID("210000").getStartDate().getYear() == start.getYear());
	    assertTrue(softwareHouse.getProjectByID("210000").getEndDate().getYear() == end.getYear());

	    
	}

	@Then("The project has a new ID with the new date.")
	public void theProjectHasANewIDWithTheNewDate() {
		assertTrue(softwareHouse.getProjectByID("210000").getID().equals("210000"));
	}

}
