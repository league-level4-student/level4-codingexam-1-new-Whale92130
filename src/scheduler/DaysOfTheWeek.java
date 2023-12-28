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
		System.out.println("Event Removed!");
	}

	void updateTimes() {
	    boolean sorted = false;
	    
	    while (!sorted) {
	        sorted = true;
	        Node<String[]> node = events.getHead();
	        
	        while (node != null && node.getNext() != null) {
	            if (getTime(node) > getTime(node.getNext())) {
	                swapNodes(node, node.getNext());
	                sorted = false;
	            }
	            node = node.getNext();
	        }
	    }
	}
	//works
	private void swapNodes(Node<String[]> node1, Node<String[]> node2) {
	    Node<String[]> tempPrev = node1.getPrev();
	    Node<String[]> tempNext = node2.getNext();

	    if (tempPrev != null) {
	        tempPrev.setNext(node2);
	    } else {
	        events.setHead(node2);
	    }

	    if (tempNext != null) {
	        tempNext.setPrev(node1);
	    }

	    node1.setPrev(node2);
	    node1.setNext(tempNext);
	    node2.setPrev(tempPrev);
	    node2.setNext(node1);
	}

	// works
	private int getTime(Node<String[]> event) {
		int time = Integer.parseInt(event.getValue()[1]);
		// System.out.println("Time: " + time);
		return time;
	}
}
