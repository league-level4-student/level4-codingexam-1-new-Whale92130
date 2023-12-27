package scheduler;

public enum DaysOfTheWeek {
	Monday(), Tuesday(), Wednesday(), Thursday(), Friday(), Saturday(), Sunday();

	private LinkedList<String[]> events = new LinkedList<String[]>();

	LinkedList<String[]> getEvents() {
		updateTimes();
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
		Node<String[]> node = events.getHead();
		while (node != null && node.getNext() != null) {
			System.out.println(getTime(node));
			System.out.println(getTime(node.getNext()));
			if (getTime(node) > getTime(node.getNext())) {
				System.out.println("swap");
				Node<String[]> temp = node;
				node = node.getNext();
				node.setPrev(temp);
				events.print();
			}

		}
	}

	// works
	private int getTime(Node<String[]> event) {
		int time = Integer.parseInt(event.getValue()[1]);
		// System.out.println("Time: " + time);
		return time;
	}
}
