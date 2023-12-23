package scheduler;

public enum DaysOfTheWeek{
	Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
	private LinkedList<String[]> events;
	
	LinkedList<String[]> getEvents() {
		return events;
	}
	void addEvents(String[] event) throws SchedulingConflictException {
		this.events.add(event);
	}
	void updateTimes() {
		//update the linked list so its in order
	}
}
