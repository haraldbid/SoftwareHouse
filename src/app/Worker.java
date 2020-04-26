package app;

public class Worker {

	private String workerID;
	private SoftwareHouse softwareHouse;

	public Worker(SoftwareHouse softwareHouse,String ID) {
		this.softwareHouse = softwareHouse;
		
		// ID length has to be 1, 2, 3 or 4
		if (ID.length() == 0 || ID.length() > 4) {
			throw new IllegalArgumentException("ID is at minimum 1 and maximum 4 letters long");
		}

		// ID must be composed solely of letters
		for (int i = 0; i < ID.length(); i++) {
			if (!Character.isLetter(ID.charAt(i))) {
				throw new IllegalArgumentException("ID must be composed of letters");
			}
		}

		this.workerID = ID;
	}

	public String getID() {
		return "Worker ID is : " + workerID;
	}

}
