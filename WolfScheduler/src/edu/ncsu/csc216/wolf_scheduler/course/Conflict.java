/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The conflict interface checks for scheduling conflict between two activities
 * @author rsthoma5
 *
 */
public interface Conflict {

	/**
	 * Checks to see if two activities conflict with each other
	 * @param possibleConflictingActivity the activity to check conflict with
	 * @throws ConflictException when there is a scheudle conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
