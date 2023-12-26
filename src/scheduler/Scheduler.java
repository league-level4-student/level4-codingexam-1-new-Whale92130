package scheduler;

import java.util.Scanner;

/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */
public class Scheduler {
	public static void main(String[] args) {
		Schedule runrun = new Schedule();
		runrun.schedule();
	}
}

class Schedule {
	Scanner scan = new Scanner(System.in);
	DaysOfTheWeek monday = DaysOfTheWeek.values()[0];
	DaysOfTheWeek tuesday = DaysOfTheWeek.values()[1];
	DaysOfTheWeek wednesday = DaysOfTheWeek.values()[2];
	DaysOfTheWeek thursday = DaysOfTheWeek.values()[3];
	DaysOfTheWeek friday = DaysOfTheWeek.values()[4];
	DaysOfTheWeek saturday = DaysOfTheWeek.values()[5];
	DaysOfTheWeek sunday = DaysOfTheWeek.values()[6];

	void schedule() {
		System.out.println("Welcome the the scheduler");
		choseDay();
	}

	void choseDay() {
		System.out.println("Enter a day(Mon, Tue, Wed, Thu, Fri, Sat, Sun)");
		String chosenDay = scan.next();
		switch (chosenDay) {
		case "Mon":
			editDay("Monday", monday);
			break;
		case "Tue":
			editDay("Tuesday", tuesday);
			break;
		case "Wed":
			editDay("Wednesday", wednesday);
			break;
		case "Thu":
			editDay("Thursday", thursday);
			break;
		case "Fri":
			editDay("Friday", friday);
			break;
		case "Sat":
			editDay("Saturday", saturday);
			break;
		case "Sun":
			editDay("Sunday", sunday);
			break;
		}
	}

	void editDay(String day, DaysOfTheWeek dayList) {
		System.out.println("Day: " + day + "\n Add: 1 \n Remove: 2 \n View: 3");
		int choice = scan.nextInt();
		switch (choice) {
		case 1:
			System.out
					.println("Enter the event you want to add and the time of the event(ex. Dinner_with_Sam,10:30pm)");
			String event = scan.next();
			String[] events = convertEvent(event, day);
			try {
				dayList.addEvents(events);
				System.out.println("Event added!");
				choseDay();
			} catch (SchedulingConflictException e) {
				e.doubleBook();
				choseDay();
			}
			break;
		case 2:
			System.out.println();
			System.out.println("Enter the number of the item you wish to remove");
			printList(dayList);
			String remove = scan.next();
			dayList.removeEvent(Integer.parseInt(remove));
			choseDay();
			break;
		case 3:
			System.out.println();
			printList(dayList);
			choseDay();
			break;
		}
	}

	// works
	String[] convertEvent(String eventStr, String day) {
		System.out.println(eventStr);
		eventStr += ",day,orgTime";
		String[] event = eventStr.split(",");
		event[3] = event[1];
		int timeHour = 0;
		int timeMin = 0;
		event[0] = event[0].trim().toLowerCase();
		event[1] = event[1].trim();
		if (event[1].contains("am")) {
			event[1] = event[1].replaceAll("am", "");
			timeHour = Integer.parseInt(event[1].split(":")[0]);
			timeMin = Integer.parseInt(event[1].split(":")[1]);
			if (timeHour == 12) {
				timeHour = 0;
			}
		} else if (event[1].contains("pm")) {
			event[1] = event[1].replaceAll("pm", "");
			timeHour = Integer.parseInt(event[1].split(":")[0]);
			timeMin = Integer.parseInt(event[1].split(":")[1]);
			if (timeHour == 12) {

			} else {
				timeHour += 12;
			}
		}
		if (timeHour > 24 || timeHour < 0 || timeMin >= 60 || timeMin < 0) {
			System.out.println("Invalid Time");
		} else {
			event[1] = timeHour + "";
			if (timeMin <= 9) {
				event[1] += "0";
			}
			event[1] += timeMin + "";
			event[2] = day;
		}
		return event;
	}

	void printList(DaysOfTheWeek day) {
		Node<String[]> newNode = day.getEvents().getHead();
		int count = 0;
		for (int i = 0; i < day.getEvents().size(); i++) {
			System.out.println(count + ": " + newNode.getValue()[0] + " " + newNode.getValue()[3] + " "
					+ newNode.getValue()[2] + " ");
			newNode = newNode.getNext();
			count++;
		}
	}

}
