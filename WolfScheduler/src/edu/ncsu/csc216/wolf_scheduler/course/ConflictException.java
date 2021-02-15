/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The ConflictException class is an exception that is thrown when there is a scheduling conflict between activities
 * @author rsthoma5
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a Conflict Exception with the message passed to it
	 * @param message the message to display when Conflict Exception is called
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Creates a Conflict Exception with the default message "Schedule conflict"
	 */
	public ConflictException() {
		super("Schedule conflict");
	}
}
