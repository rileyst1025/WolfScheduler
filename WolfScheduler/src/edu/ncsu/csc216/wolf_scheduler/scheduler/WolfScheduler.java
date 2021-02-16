/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;

/**
 * The WolfScheduler class contains a course catalog, a schedule, and methods to edit said schedule with courses from the course catalog as well as
 * with events that are initialized in the class by the user
 * @author rsthoma5
 *
 */
public class WolfScheduler {
	
	/** List of all available courses in WoldScheduler's catalog */
	ArrayList<Course> catalog = new ArrayList<Course>();
	/** List of all Activities in user's schedule in WolfScheduler */
	ArrayList<Activity> schedule = new ArrayList<Activity>();
	/** Title of user's schedule in WolfScheduler */
	String title;

	/**
	 * Creates WolfSchedule with empty schedule, schedule name "My Schedule", and a course
	 * catalog from input file
	 * @param file the file to get catalog from
	 */
	public WolfScheduler(String file) {
		schedule = new ArrayList<Activity>();
		title = "My Schedule";
		try {
			catalog = CourseRecordIO.readCourseRecords(file);
		} catch (Exception e) {
			throw new IllegalArgumentException ("Cannot find file.");
		}
	}

	/**
	 * Gets list of courses in course catalog showing name, section, and title.
	 * @return 2D array of courses in catalog with name, section, and title
	 */
	public String[][] getCourseCatalog() {
		if(this.catalog.size() == 0) {
			return new String[0][0];
		}
		else {
			String[][] rcatalog = new String[this.catalog.size()][4];
			for(int i = 0; i < this.catalog.size(); i++) {
				rcatalog[i][0] = this.catalog.get(i).getName();
				rcatalog[i][1] = this.catalog.get(i).getSection();
				rcatalog[i][2] = this.catalog.get(i).getTitle();
				rcatalog[i][3] = this.catalog.get(i).getMeetingString();
			}
			return rcatalog;
		}
	}

	/**
	 * Gets list of activites in user schedule showing short display array
	 * @return 2D array of activites in schedule with short display array
	 */
	public String[][] getScheduledActivities() {
		if(this.schedule.size() == 0) {
			return new String[0][0];
		}
		else {
			String[][] rcatalog = new String[this.schedule.size()][];
			for(int i = 0; i < this.schedule.size(); i++) {
				rcatalog[i] = this.schedule.get(i).getShortDisplayArray();
			}
			return rcatalog;
		}
	}

	/**
	 * Gets list of activites in user schedule showing long display array.
	 * @return 2D array of activites in schedule with long display array
	 */
	public String[][] getFullScheduledActivities() {
		if(this.schedule.size() == 0) {
			return new String[0][0];
		}
		else {
			String[][] rcatalog = new String[this.schedule.size()][];
			for(int i = 0; i < this.schedule.size(); i++) {
				rcatalog[i] = this.schedule.get(i).getLongDisplayArray();
			}
			return rcatalog;
		}
	}

	/**
	 * Gets title of user's schedule
	 * @return the title of schedule
	 */
	public String getScheduleTitle() {
		return this.title;
	}

	/**
	 * Exports user's schedule to the given file
	 * @param filename the name of file to export schedule
	 */
	public void exportSchedule(String filename) {
		try {
			ActivityRecordIO.writeActivityRecords(filename, this.schedule);
		}
		catch(Exception e) { 
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * Returns true if course is able to be added from the course catalog and doesn't share name with a course already in schedule
	 * @param name the name of course to be added
	 * @param section the section of course to be added
	 * @return true if course can be added, false if it cannot
	 */
	public boolean addCourseToSchedule(String name, String section){
		Course addcourse = getCourseFromCatalog(name, section);
		if(addcourse == null) {
			return false;
		}
		try {
			for(int i = 0; i < schedule.size(); i++) {
				addcourse.checkConflict(schedule.get(i));
				if(addcourse.isDuplicate(schedule.get(i))) {
					throw new IllegalArgumentException("You are already enrolled in " + addcourse.getName());
				}
			}
			if("A".equals(addcourse.getMeetingDays())) {
				schedule.add(addcourse);
				return true;
			}
			schedule.add(addcourse);
			return true;
		} catch(ConflictException e) {
			throw new IllegalArgumentException("The course could not be added due to conflict.");
		}
	}
	
	/**
	 * Returns true if event can be added to schedule and adds event to schedule
	 * @param title the title of event to add
	 * @param meetingDays the meeting days of event to add
	 * @param startTime the start time of event to add
	 * @param endTime the end time of event to add
	 * @param weeklyRepeat the weekly repeat of event to add
	 * @param eventDetails the details of event to add
	 * @return true if event can be added
	 * @throws ConflictException if there is a schedule conflict with the event being added
	 */
	public boolean addEventToSchedule(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		if(title == null || "".equals(title) || startTime > endTime) {
			throw new IllegalArgumentException("The event is Invalid");
		}
		try {
			if(meetingDays == null || "".equals(meetingDays)) {
				throw new IllegalArgumentException("The event must occur on at least one day.");
			}
			Event addevent = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
			for(int i = 0; i < schedule.size(); i++) {
				addevent.checkConflict(schedule.get(i));
				if(addevent.isDuplicate(schedule.get(i))) {
					throw new IllegalArgumentException("You have already created an event called " + addevent.getTitle());
				}
			}
			schedule.add(addevent);
			return true;
		} catch(ConflictException e) {
			throw new IllegalArgumentException("The event could not be added due to conflict.");
		}
	}

	/**
	 * Returns true if activity at given index can be removed
	 * @param idx index of activity to remove
	 * @return true if activity can be removed, false if it cannot
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	/**
	 * Reset user's schedule to empty arraylist
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Activity>();
	}

	/**
	 * Sets the user's schedule title to the given title
	 * @param title the title to set
	 */
	public void setScheduleTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Gets a course from the catalog based on given name and section
	 * @param name the name to search for in catalog
	 * @param section the section to search for in catalog
	 * @return the course with the given name and section, null if course cannot be found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < this.catalog.size(); i++) {
			if(this.catalog.get(i).getName().equals(name) && 
					this.catalog.get(i).getSection().equals(section)){
				return this.catalog.get(i);
			}
		}
		return null;
	}

}
