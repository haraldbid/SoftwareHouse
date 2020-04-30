package app;

import java.util.ArrayList;
import java.util.List;

import designPatterns.Observer;

public class SoftwareHouse {
	
	private static SoftwareHouse softwareHouse;
	private String loggedIn;
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	private  ArrayList<Worker> listOfWorkers = new ArrayList<Worker>();


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
	public void createWorker(String ID) {
		Worker worker = new Worker(this,ID);
		
		listOfWorkers.add(worker);
		
		System.out.print(getNbWorkers());
	}
	
	public int getNbWorkers() {
		return listOfWorkers.size();
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
