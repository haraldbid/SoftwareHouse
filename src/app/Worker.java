package app;

import java.util.ArrayList;


import designPatterns.Date;

import designPatterns.Date;

public class Worker {

	private String workerID;
	private SoftwareHouse softwareHouse;
	private ArrayList<Activity> listOfActivities;
//	private Calendar calendar = new GregorianCalendar();

	public Worker(SoftwareHouse softwareHouse, String ID) {
		this.softwareHouse = softwareHouse;

		// ID length has to be 1, 2, 3 or 4
		if (ID.length() == 0 || ID.length() > 4) {
			throw new IllegalArgumentException("ID is at minimum 1 and maximum 4 letters long.");
		}

		// ID must be composed solely of letters
		for (int i = 0; i < ID.length(); i++) {
			if (!Character.isLetter(ID.charAt(i))) {
				throw new IllegalArgumentException("ID must be composed of letters.");
			}
		}

        // ID must be available       
		for (int i = 0; i < this.softwareHouse.getNbWorkers(); i++) {
			if (this.softwareHouse.getWorker(i).getID().equals(ID)) {
				throw new IllegalArgumentException("ID is already used by another worker.");
			}
		}

		this.workerID = ID;
	}

	public String getID() {
		return workerID;
	}
	

	public int getNumActivities(Date startDate, Date endDate) {

		int count = 0;
		for (int i = 0; i < listOfActivities.size(); i++) {
			if ((listOfActivities.get(i).getStartDate().after(startDate)
					&& listOfActivities.get(i).getStartDate().before(endDate))
					|| (listOfActivities.get(i).getEndDate().after(startDate)
							&& listOfActivities.get(i).getEndDate().before(endDate))) {
				count++;
			}
		}
		return count;
	}
	
	public void addActivity(Activity activity) {
		listOfActivities.add(activity);
	}

}
