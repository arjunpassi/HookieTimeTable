package com.example.hookietimetable;

/**
 * This class represents a professor at Virginia Tech
 * @author Arjun Passi
 *
 */
public class Professor {

	/** Name of the professor */
	private String name;
	
	/** Department of the professor*/
	private String department;
	
	/** Quality of the professor*/
	private String quality;
	
	/** Ratings of the professor*/
	private String ratings;
	
	/** Easiness of the professor */
	private String easiness;
	
	/**
	 * Constructs the professor object
	 * @param name
	 * @param department
	 * @param ratings
	 * @param quality
	 * @param easiness
	 */
	Professor(String name, String department, String ratings,
			String quality, String easiness){
		this.name = name;
		this.department = department;
		this.quality = quality;
		this.ratings = ratings;
		this.easiness = easiness;
	}
	
	/**
	 * @return the name of the professor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the professor
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name of the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets the department the name
	 * @param department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the quality of the professor
	 */
	public String getQuality() {
		return quality;
	}

	/**
	 * Sets the quality of the professor
	 * @param quality
	 */
	public void setQuality(String quality) {
		this.quality = quality;
	}

	/**
	 * @return ratings of the professor
	 */
	public String getRatings() {
		return ratings;
	}

	/**
	 * Sets the ratings of the professor
	 * @param ratings
	 */
	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	/**
	 * @return easiness of the professor.
	 */
	public String getEasiness() {
		return easiness;
	}

	/**
	 * Sets the easiness of the professor
	 * @param easiness
	 */
	public void setEasiness(String easiness) {
		this.easiness = easiness;
	}
	
	public String toString(){
		StringBuilder build = new StringBuilder();
		build.append("Name: " + name + '\n');
		build.append("Department: " + department + '\n');
		build.append("Quality: " + quality + '\n');
		build.append("Rating: " + ratings + '\n');
		build.append("Easiness: " + easiness + '\n');
		
		return build.toString();
	}
}
