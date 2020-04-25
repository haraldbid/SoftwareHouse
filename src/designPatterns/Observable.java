package designPatterns;

public interface Observable {
	public void register(Observer o);
	public void unregiseter (Observer o);
	public void notifyObserver();
}
