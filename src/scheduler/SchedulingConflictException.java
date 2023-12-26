package scheduler;

public class SchedulingConflictException extends Exception{

	void doubleBook() {
		System.err.println("You have tried to double book a time slot");
	}

}
