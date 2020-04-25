import java.util.ArrayList;

import designPatterns.Observable;
import designPatterns.Observer;

public class SoftwareHouse implements Observable {
	
	private String loggedIn;
	private ArrayList<Observer> observers; 
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public void logIn(String ID) {
		loggedIn = ID;
		notifyObserver();
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
		
		for (Observer o : observers) {
			o.update(loggedIn);
		}
		
	}
	
	
	
	
	
}
