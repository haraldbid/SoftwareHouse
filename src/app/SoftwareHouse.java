package app;

import java.util.ArrayList;
import java.util.List;
import designPatterns.Observable;
import designPatterns.Observer;

public class SoftwareHouse implements Observable {
	
	private String loggedIn;
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	private List<Worker> listOfWorkers;
	private List<Project> listOfProjects = new ArrayList<Project>();


	public SoftwareHouse() {
	}
	
	public void createProject() {
		Project project = new Project(this);
		listOfProjects.add(project);
	}
	
	public void logIn(String ID) {
		loggedIn = ID;
		notifyObserver();
	}
	public boolean loggedIn() {
		if(this.loggedIn == null) {
			return false;
		}
		return true;
	}

	@Override
	public void register(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void unregiseter(Observer o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObserver() {
		
		if (!observers.isEmpty()) {
			for (Observer o : observers) {
				o.update(loggedIn);
			}
		}
		
	}
		

	public Worker getWorker(int index) {
		return listOfWorkers.get(index);

	}
	
	
	
	
	
}
