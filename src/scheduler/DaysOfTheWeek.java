package scheduler;

public enum DaysOfTheWeek {
	Monday(), Tuesday(), Wednesday(), Thursday(), Friday(), Saturday(), Sunday();

	private LinkedList<String[]> events = new LinkedList<String[]>();

	LinkedList<String[]> getEvents() {
		return events;
	}

	void addEvents(String[] event) throws SchedulingConflictException {
		if (events.getHead() != null) {
			Node<String[]> newNode = events.getHead();
			while (newNode != null) {
				if (newNode.getValue()[1].equals(event[1]) && newNode.getValue()[2].equals(event[2])) {
					throw new SchedulingConflictException();
				}
				newNode = newNode.getNext();
			}
			this.events.add(event);
		} else {
			this.events.add(event);
		}
	}

	void removeEvent(int position) {
		events.remove(position);
	}

	void updateTimes() {
		// update the linked list so its in order
		Node<String[]> newNode = events.getHead();
		while (newNode != null) {
			String[] time = newNode.getValue()[1].split("");
			String minsStr = time[time.length-1]+time[time.length-2];
			int mins = Integer.parseInt(minsStr);
			int hour;
			if (time[1].equals(time[time.length-2])) {
				hour = Integer.parseInt(time[0]);
			}
			else {
				hour = Integer.parseInt(time[0]+time[1]);
			}
			newNode = newNode.getNext();
		}
	}
}
