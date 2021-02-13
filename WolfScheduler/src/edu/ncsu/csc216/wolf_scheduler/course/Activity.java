package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The activity class is an abstract class representing an activity, is parent for both Course and Event, and maintains information for the title, 
 * meeting days, and meeting times for an activity
 * @author rsthoma5
 *
 */
public abstract class Activity {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** Course's max hour value */
	private static int upperHour = 23;
	/** Course's max minute value */
	private static int upperMinute = 59;

	/**
	 * Returns list of Activity's name, section, title, and meeting string
	 * @return array of Actitity's name, section, title, and meeting string
	 */
	public abstract String[] getShortDisplayArray();
	/**
	 * Returns list of Activity's name, section, title, credits, instructor id, and meeting string
	 * @return array of Activity's name, section, title, credits, instructor id, and meeting string
	 */
	public abstract String[] getLongDisplayArray();
	/**
	 * Checks if given activity is a duplicate of the classes Activity
	 * @param activity the activity to compare to
	 * @return true if duplicate, false if not
	 */
	public abstract boolean isDuplicate(Activity activity); 
	/**
	 * Constructs an Activity object with the given tile, meeting days, start time, and end time
	 * @param title the title to set
	 * @param meetingDays the meeting dasy to set
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Gets the Activity's title
	 * @return title of Activity
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Gets the Activity's meeting days
	 * @return the Activity's meeting days
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Gets the Activity's start time
	 * @return the Activity's start time
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Activity's end time
	 * @return the Activity's end time
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meeting days and time
	 * @param meetingDays the meeting days to set
	 * @param startTime the starting time to set
	 * @param endTime the ending time to set
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;
			if (startHour > upperHour || startMin > upperMinute || startTime < 0) {
				throw new IllegalArgumentException("Invalid start time.");
			}
			if (endHour > upperHour || endMin > upperMinute || endTime < 0) {
				throw new IllegalArgumentException("Invalid end time.");
			}
			if (startTime > endTime) {
				throw new IllegalArgumentException("End time cannot be before start time.");
			}
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}

	/**
	 * Gets the given time in standard time
	 * @param time the time that is being converted to standard time
	 * @return the time in standard time
	 */
	private String getTimeString(int time) {
		int minutes = time % 100;
		if(minutes >= 10) {
			if (time < 100) {
				return "12:" + minutes + "AM";
			}
			else if(time < 1200) {
				int hours = time / 100;
				return hours + ":" + minutes + "AM";
			}
			else if (time < 1300) {
				return "12:" + minutes + "PM";
			}
			else {
				int hours = time / 100 - 12;
				return hours + ":" + minutes + "PM";
			}
		}
		else {
			if (time < 100) {
				return "12:0" + minutes + "AM";
			}
			else if(time < 1200) {
				int hours = time / 100;
				return hours + ":0" + minutes + "AM";
			}
			else if (time < 1300) {
				return "12:0" + minutes + "PM";
			}
			else {
				int hours = time / 100 - 12;
				return hours + ":0" + minutes + "PM";
			}
		}
	}

	/**
	 * Gets the Activity's meeting time
	 * @return the Activity's meeting time
	 */
	public String getMeetingString() {
		if("A".equals(this.meetingDays)) {
			return "Arranged";
		}
		else {
			return this.meetingDays + " " + getTimeString(this.startTime) + "-" + getTimeString(this.endTime);
		}
	}

	/**
	 * Generates a hash code for the Activity 
	 * @return the hash code for the Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Checks to see if Activity is equal to a given object
	 * @param obj the object to compare the Activity to
	 * @return true if the objects are equal, false if they are not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}