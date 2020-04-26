package app;

import java.util.ArrayList;
import java.util.List;

public class SoftwareHouse {
	
	private static ArrayList<Worker> listOfWorkers = new ArrayList<Worker>(0);
	private static SoftwareHouse softwareHouse;
	
	

	public static void main(String[] args) {
		
		createWorker("AB");
		createWorker("AB");
		System.out.println(getNbWorkers());
		
	}
	

	private String loggedIn;
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	private List<Worker> listOfWorkers;


	public SoftwareHouse() {
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
	
	
	//
	public static void createWorker(String ID) {
		Worker worker = new Worker(softwareHouse,ID);
		
		listOfWorkers.add(worker);
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
