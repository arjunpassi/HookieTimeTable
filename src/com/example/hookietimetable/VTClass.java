package com.example.hookietimetable;

/**
 * Instantiation of this class will represent a class
 * at Virgina Tech
 * 
 * @author Arjun Passi
 *
 */
public class VTClass {
	
	/** CRN of the class */
	private String CRN;
	
	/** Name of the course */
	private String course;
	
	/** Title of the course */
	private String title;
	
	/** Type of the course */
	private String type;
	
	/** Number of Credit hours of the class */
	private String creditHours;
	
	/** Capacity of the class */
	private String capacity;
	
	/** Name of the instructor of the class*/
	private String instructor;
	
	/** Days this class is taught*/
	private String days;
	
	/** Additional Days this class is taught*/
	private String additionalDay;
	
	/** Begin time of this class */
	private String beginTime;
	
	/** Additional Begin time of this class*/
	private String additionalBeginTime;
	
	/** End time of this class*/
	private String endTime;
	
	/** Additional End time of this class*/
	private String additionalEndTime;
	
	/** Location of where this class is taught */
	private String location;
	
	/** Additional location of the class*/
	private String additionalLocation;
	
	/**
	 * Constructs the VTClass object with additional times
	 * @param crn
	 * @param course
	 * @param title
	 * @param type
	 * @param cHrs
	 * @param size
	 * @param instructor
	 * @param days
	 * @param additionalDay
	 * @param beginTime
	 * @param additionalBeginTime
	 * @param endTime
	 * @param additionalEndTime
	 * @param location
	 * @param additionalLocation
	 */
	VTClass(String crn, String course, String title, String type, 
			String cHrs, String size, String instructor, String days, 
			String additionalDay, String beginTime, String additionalBeginTime,
			String endTime, String additionalEndTime, String location, String additionalLocation){
	
		this.CRN = crn;
		this.course = course;
		this.title = title;
		this.type = type;
		this.creditHours = cHrs;
		this.capacity = size;
		this.instructor = instructor;
		this.days = days;
		this.additionalDay = additionalDay;
		this.beginTime = beginTime;
		this.additionalBeginTime = additionalBeginTime;
		this.endTime = endTime;
		this.additionalEndTime = additionalEndTime;
		this.location = location;
		this.additionalLocation = additionalLocation;
	}
	
	/**
	 * Constructs a VTClass object with no additional times
	 * @param crn
	 * @param course
	 * @param title
	 * @param type
	 * @param cHrs
	 * @param size
	 * @param instructor
	 * @param days
	 * @param beginTime
	 * @param endTime
	 * @param location
	 */
	VTClass(String crn, String course, String title, String type, 
			String cHrs, String size, String instructor, String days, String beginTime,
			String endTime, String location){
	
		this.CRN = crn;
		this.course = course;
		this.title = title;
		this.type = type;
		this.creditHours = cHrs;
		this.capacity = size;
		this.instructor = instructor;
		this.days = days;
		this.additionalDay = "";
		this.beginTime = beginTime;
		this.additionalBeginTime = "";
		this.endTime = endTime;
		this.additionalEndTime = "";
		this.location = location;
		this.additionalLocation = "";
	}
	
	/** Returns the CRN of the */
	public String getCRN() {
		return CRN;
	}

	/**
	 * Sets the CRN of the class
	 * @param cRN
	 */
	public void setCRN(String cRN) {
		CRN = cRN;
	}

	/** @return course name */
	public String getCourse() {
		return course;
	}

	/**
	 * Sets the course name
	 * @param course
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/** @return title of the course */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the course
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** @return type of the course */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the class
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** @return credit hours of the course */
	public String getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the credit hours of the course
	 * @param creditHours
	 */
	public void setCreditHours(String creditHours) {
		this.creditHours = creditHours;
	}

	/** @return capacity of the class*/
	public String getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity of the class
	 * @param capacity
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	/** @return instructor name */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * Sets the instructor name of this class
	 * @param instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	/** @return days this class is taught */
	public String getDays() {
		return days;
	}

	/**
	 * Sets the days this class is taught
	 * @param days
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/** @return additional days this class is taught */
	public String getAdditionalDay() {
		return additionalDay;
	}

	/**
	 * Sets the additional days this class is taught
	 * @param additionalDay
	 */
	public void setAdditionalDay(String additionalDay) {
		this.additionalDay = additionalDay;
	}

	/** @return begin time of this class */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * Sets the begin time of this class
	 * @param beginTime
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/** @return additional begin time */
	public String getAdditionalBeginTime() {
		return additionalBeginTime;
	}

	/** @return Sets additional begin time of this class*/
	public void setAdditionalBeginTime(String additionalBeginTime) {
		this.additionalBeginTime = additionalBeginTime;
	}

	/** @return end time of the class */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time of the class
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/** @return additional time of the class */
	public String getAdditionalEndTime() {
		return additionalEndTime;
	}

	/**
	 * Sets additional end time of the class
	 * @param additionalEndTime
	 */
	public void setAdditionalEndTime(String additionalEndTime) {
		this.additionalEndTime = additionalEndTime;
	}

	/** @return location of the class*/
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the class
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/** @return additional location of the class*/
	public String getAdditionalLocation() {
		return additionalLocation;
	}

	/**
	 * Sets the additional location of the class
	 * @param additionalLocation
	 */
	public void setAdditionalLocation(String additionalLocation) {
		this.additionalLocation = additionalLocation;
	}

	public String toString(){
		StringBuilder build = new StringBuilder();
		
		build.append("CRN: " + CRN + '\n');
		build.append("Course: " + course + '\n');
		build.append("Title: " + title + '\n');
		build.append("Type: " + type + '\n');
		build.append("Cr Hrs: " + creditHours + '\n');
		build.append("Capacity: " + capacity + '\n');
		build.append("Instructor: " + instructor + '\n');
		build.append("Days: " + days + '\n');
		
		if(!additionalDay.equals(""))
			build.append("Additional Days: " + additionalDay + '\n');
		
		build.append("Begin Time: " + beginTime + '\n');
		
		if(!additionalBeginTime.equals(""))
			build.append("Begin Time on " + additionalDay + " " + additionalBeginTime + '\n');
		
		build.append("End Time: " + endTime + '\n');
		
		if(!additionalEndTime.equals(""))
			build.append("End Time on " + additionalDay + " " + additionalEndTime + '\n');
			
		build.append("Location: " + location + '\n');
		
		if(!additionalLocation.equals(""))
			build.append("Loaction on " + additionalDay + " " + additionalLocation + '\n');
		
		return build.toString();
	}

}
