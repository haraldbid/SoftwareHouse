package designPatterns;

import app.Worker;

public interface Observable {
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver();
	public Worker getWorkerLoggedIn();
	
}
