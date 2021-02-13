/**Source package for WolfScheduler.
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The course class is a child class of activity, maintaining all of its information plus the name, section, number of credits, and the id of the 
 * instructor of the course.
 * @author rsthoma5
 */
public class Course extends Activity {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's min length */
	static int minNameLength = 5;
	/** Course's max length */
	static int maxNameLength = 8;
	/** Course's min amount of letters */
	static int minLetterCount = 1;
	/** Course's max amount of letters */
	static int maxLetterCount = 4;
	/** Course's correct amount of digits */
	static int digitCount = 3;
	/** Course's correct number of digits for its section */
	static int sectionLength = 3;
	/** Course's min amount of credit hours */
	static int minCredits = 1;
	/** Course's max amount of credit hours */
	static int maxCredits = 5;
	/**
	 * Gets the Course's name
	 * @return the Course's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Course's name
	 * @param name the name to set
	 */
	private void setName(String name) {
		if (name == null){
			throw new IllegalArgumentException("Name cannot be null.");
		}
		else if(name.length() < minNameLength || 
				name.length() > maxNameLength) {
			throw new IllegalArgumentException("Name's length should be between 5 and 8, inclusive.");
		}
		int lettercount = 0;
		int digitcount = 0;
		boolean spacecheck = false;
		for(int i = 0; i < name.length(); i++) {
			if (spacecheck){
				if (Character.isDigit(name.charAt(i))) {
					digitcount++;
				}
				else {
					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
				}
			}
			else {
				if (Character.isLetter(name.charAt(i))) {
					lettercount++;
				}
				else if (name.charAt(i) == ' ') {
					spacecheck = true;
				}
				else {
					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
				}
			}
		}
		if (lettercount < minLetterCount || lettercount > maxLetterCount) {
			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}
		if (digitcount != digitCount) {
			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}
		this.name = name;
	}
	/**
	 * Gets the Course's section
	 * @return the Course's section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Set's the Course's section
	 * @param section the section to set
	 */
	public void setSection(String section) {
		if(section == null) {
			throw new IllegalArgumentException("Invalid section.");
		}
		if(section.length() != sectionLength) {
			throw new IllegalArgumentException("Invalid section.");
		}
		boolean notDigitCheck = false;
		for (int i = 0; i < section.length(); i++) {
			if(!Character.isDigit(section.charAt(i))) {
				notDigitCheck = true;
			}
		}
		if(notDigitCheck) {
			throw new IllegalArgumentException("Invalid section.");
		}
		this.section = section;
	}
	/**
	 * Gets the number of the Course's credits
	 * @return the Course's credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the number for the Course's credits
	 * @param credits the number of credits to set
	 */
	public void setCredits(int credits) {
		if(credits < minCredits || credits > maxCredits) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		this.credits = credits;
	}
	/**
	 * Gets the Course's instructor id
	 * @return the Course's instructor id
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the Course's instructor id
	 * @param instructorId the instructor id to set
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for
	 * courses that are arranged
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for course as series of chars
	 * @param startTime start time for Course as an int
	 * @param endTime end time for Course as an int
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
	    this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}
	
	/**
	 * Sets meeting days and times to the specifications of Course
	 * @param meetingDays the meetingDays to set
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0){
			throw new IllegalArgumentException("Invalid Meeting Days.");
		}
		if ("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
		else {
			int mCount = 0; int tCount = 0; int wCount = 0; int hCount = 0; int fCount = 0;
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
				else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			if(mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}
	/**
	 * Gets a comma separated value String of all Course fields.
	 * @return comma separated value string for all Course fields
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	/**
	 * Generates a hashcode for the Course
	 * @return the hashcode for the Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	/**
	 * Checks to see if Course if equal to a given object
	 * @return true if Course and object are equal, false if they are not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Gets list of Course's name, section, title, and meeting string
	 * @return array of Course's name, section, title, and meeting string
	 */
	public String[] getShortDisplayArray(){
		String[] list = new String[4];
		list[0] = getName();
		list[1] = getSection();
		list[2] = getTitle();
		list[3] = getMeetingString();
		return list;
	}
	
	/**
	 * Gets list of Course's name, section, title, credits, instructor id and meeting string
	 * @return array of Course's name, section, title, credits, instructor id, and meeting string
	 */
	public String[] getLongDisplayArray() {
		String[] list = new String[7];
		list[0] = getName();
		list[1] = getSection();
		list[2] = getTitle();
		list[3] = Integer.toString(getCredits());
		list[4] = getInstructorId();
		list[5] = getMeetingString();
		list[6] = "";
		return list;
	}
	
	/**
	 * Checks to see if passed activity is a duplicate course to Course
	 * @return true if duplicate course, false otherwise
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && ((Course)activity).getName().equals(this.getName());
	}
}