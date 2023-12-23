package scheduler;

public class SchedulingConflictException extends Exception{

	void doubleBook() {
		System.out.println("You have tried to double book a time slot");
	}

}
