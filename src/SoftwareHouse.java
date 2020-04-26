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
	
	public static int getNbWorkers() {
		
		return listOfWorkers.size();
		
	}
	
	
	//
	public static void createWorker(String ID) {
		Worker worker = new Worker(softwareHouse,ID);
		
		listOfWorkers.add(worker);
	}
	
	public Worker getWorker(int index) {
		return listOfWorkers.get(index);
	}
	
	
	
	
	
}
