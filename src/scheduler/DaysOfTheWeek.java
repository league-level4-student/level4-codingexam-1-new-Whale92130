package scheduler;

public enum DaysOfTheWeek {
	Monday, Tuesday, Wednesday, Thursday, Friday, Satruday, Sunday;
	private LinkedList<String[][]> events;
	
	LinkedList<String[][]> getEvents() {
		return events;
	}
	void addEvents(String[][] event) {
		events.add(event);
	}
	void updateTimes() {
		//update the linked list so its in order
	}
}
