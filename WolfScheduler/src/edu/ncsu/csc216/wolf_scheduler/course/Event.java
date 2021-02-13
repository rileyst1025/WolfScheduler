/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The activity class is a child class for an activity, it maintains all of activity's information plus the number of weeks before it repeats along
 * with details for the event.
 * @author rsthoma5
 *
 */
public class Event extends Activity {
	
	/** Number of weeks before the event repeats */
	private int weeklyRepeat;
	/** String with details about the event */
	private String eventDetails;
	/** Minimum number for weeklyRepeat */
	private static int minWeeklyRepeat = 1;
	/** Maximum value for weeklyRepeat */
	private static int maxWeeklyRepeat = 4;

	/**
	 * Creates an event object with the given title, meeting days and times, weeklyrepeat value, and eventDetails
	 * @param title the title to set
	 * @param meetingDays the meetingDays to set
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 * @param weeklyRepeat the weeklyRepeat to set
	 * @param eventDetails the eventDetails to set
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}
	/**
	 * Gets the value of weeklyRepeat for Event
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}
	/**
	 * Sets the value of weeklyRepeat to the given value
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if(weeklyRepeat < minWeeklyRepeat || weeklyRepeat > maxWeeklyRepeat) {
			throw new IllegalArgumentException("Invalid weekly repeat.");
		}
		this.weeklyRepeat = weeklyRepeat;
	}
	/**
	 * Gets the eventDeatails for Event
	 * @return the eventDetails for Event
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	/**
	 * Sets the eventDetails to the given String
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Gets list of Event's title and meeting string
	 * @return array of the Event's title and meeting string
	 */
	public String[] getShortDisplayArray(){
		String[] list = new String[4];
		list[0] = "";
		list[1] = "";
		list[2] = getTitle();
		list[3] = getMeetingString();
		return list;
	}
	
	/**
	 * Gets list of Event's title, meeting string, and event details
	 * @return array of the Event's title, meeting string, and event details
	 */
	public String[] getLongDisplayArray() {
		String[] list = new String[7];
		list[0] = "";
		list[1] = "";
		list[2] = getTitle();
		list[3] = "";
		list[4] = "";
		list[5] = getMeetingString();
		list[6] = getEventDetails();
		return list;
	}
	
	/**
	 * Sets the meeting time and days to Event's specifications
	 * @param meetingDays the meeting days to set
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0){
			throw new IllegalArgumentException("Invalid Meeting Days.");
		}
		int mCount = 0; int tCount = 0; int wCount = 0; int hCount = 0; int fCount = 0; int sCount = 0; int uCount = 0;
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) == 'M') {
				mCount++;
			}
			else if(meetingDays.charAt(i) == 'T') {
				tCount++;
			}
			else if(meetingDays.charAt(i) == 'W') {
				wCount++;
			}
			else if(meetingDays.charAt(i) == 'H') {
				hCount++;
			}
			else if(meetingDays.charAt(i) == 'F') {
				fCount++;
			}
			else if(meetingDays.charAt(i) == 'S') {
				sCount++;
			}
			else if(meetingDays.charAt(i) == 'U') {
				uCount++;
			}
			else {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
		}
		if(mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1 || sCount > 1 || uCount > 1) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	/**
	 * Gets String with MeetingDays, startTime, endTime, and weeklyRepeat
	 * @return meeting string with Event's days, times, and weekly repeat
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + getWeeklyRepeat() + " weeks)";
	}
	
	/**
	 * Gets comma seperated list of every parameter in Event
	 * @return comma seperated list of all parameters in Event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getWeeklyRepeat() + "," + getEventDetails();
	}
	
	/**
	 * Checks to see if passed activity is a duplicate event to Event
	 * @return true if duplicate event, false otherwise
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Event && ((Event)activity).getTitle().equals(this.getTitle());
	}
	
	
}
