import java.lang.*;

public abstract class Employee {

	private String employeeID;

	public Employee(String ID) {

		// ID length has to be 1, 2, 3 or 4
		if (ID.length() > 4 || ID.length() == 0) {
			throw new IllegalArgumentException("ID is at minimum 1  maximum 4 letters long");
		}

		// ID must solely be composed of letters
		for (int i = 0; i < ID.length(); i++) {
			if (!Character.isLetter(ID.charAt(i))) {
				throw new IllegalArgumentException("ID must be composed of letters");
			}
		}

		this.employeeID = ID;
	}

	public String getID() {
		return employeeID;
	}

}
