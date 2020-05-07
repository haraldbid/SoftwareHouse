package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import designPatterns.Date;
import designPatterns.Observable;
import designPatterns.Observer;

public class SoftwareHouse implements Observable {

	private static SoftwareHouse softwareHouse;
	private Worker loggedIn;
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	private List<Project> listOfProjects = new ArrayList<Project>();

	private ArrayList<Worker> listOfWorkers = new ArrayList<Worker>();

	public SoftwareHouse() {

	}

	public void createProject(Date startDate, Date endDate) {

		Project project = new Project(this, startDate, endDate,generateProjectID(startDate));
		listOfProjects.add(project);
	}

	public String generateProjectID(Date startDate) {

		String year = Integer.toString(startDate.getYear());

		String runningCount = "";

		for (int i = 0; i < 4 - Integer.toString(listOfProjects.size()).length(); i++) {

			runningCount += "0";
		}
		runningCount += listOfProjects.size();

		return year + runningCount;

	}
	
	

// TODO: check valid ID, or create example user with the given ID.Perhaps LoggeIn should be reference to worker

	public void logIn(String ID) {

		for (int i = 0; i < listOfWorkers.size(); i++) {

			if (listOfWorkers.get(i).getID().equals(ID)) {
				loggedIn = listOfWorkers.get(i);
				notifyObserver();
				System.out.println("Worker " + ID + " has logged in succesfully.");
			}
		}

		if (!loggedIn()) {
			System.out.println("Login failed.");
		}

	}
	
	public void logOut() {
		
		if (loggedIn()) {
			loggedIn = null;
			notifyObserver();
			System.out.println("Logout successful.");
		} else {
			System.out.println("No worker is logged in.");
		}
		
	}

	public void getAllWorkersActivities(Date startDate, Date endDate) {

		quickSort(listOfWorkers, 0, listOfWorkers.size() - 1, startDate, endDate);

		for (int i = 0; i < listOfWorkers.size(); i++) {
			System.out.println(listOfWorkers.get(i).getID() + " : " + listOfWorkers.get(i).getNumActivities(startDate, endDate));
		}
	}

	public void quickSort(ArrayList<Worker> arr, int low, int high, Date startDate, Date endDate) {


		if (arr == null || arr.size() == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr.get(middle).getNumActivities(startDate, endDate);

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr.get(i).getNumActivities(startDate, endDate) < pivot) {
				i++;
			}

			while (arr.get(j).getNumActivities(startDate, endDate) > pivot) {
				j--;
			}

			if (i <= j) {
				Worker temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j, startDate, endDate);

		if (high > i)
			quickSort(arr, i, high, startDate, endDate);

	}

	public boolean loggedIn() {
		if (this.loggedIn == null) {
			return false;
		}
		return true;
	}
	
	public Worker getWorkerLoggedIn() {
		return loggedIn;
	}
	
	

	@Override
	public void register(Observer o) {
		observers.add(o);
	}

	public void createWorker(String ID) {
		Worker worker = new Worker(this, ID);

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
	
	public Worker getWorker(String ID) {
		int i = 0;
		while (!listOfWorkers.get(i).getID().equals(ID)) {
			i++;
		}
		return listOfWorkers.get(i);
	}
	
	public Project getProject(String projectID) {
		int i = 0;
		
		while (!listOfProjects.get(i).getID().equals(projectID)) {
			i++;
		}
		
		return listOfProjects.get(i);
		
	}

	public List<Project> getListOfProjects() {
		return listOfProjects;

	}
	


	@Override
	public void unregister(Observer o) {
		observers.remove(o);

	}

}
