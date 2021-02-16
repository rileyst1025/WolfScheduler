/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The ActivityTest class is a class that tests the check conflict method for Activity
 * @author rsthoma5
 *
 */
public class ActivityTest {

	/**
	 * Tests to see if ConflictException is not thrown when there isn't a schedule conflict
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	        a2.checkConflict(a1);
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}

	/**
	 * Tests to see if ConflictException is thrown when there is a schedule conflcit
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}
	
	/**
	 * Tests to see if ConflictException is thrown with end time the same as start time and vice versa
	 */
	@Test
	public void testCheckConflictWithBackToBackTimes() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 2:45PM-3:30PM", a2.getMeetingString());
	    }
	    a2.setMeetingDaysAndTime("M", 1230, 1330);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 12:30PM-1:30PM", a2.getMeetingString());
	    }
	}
}
