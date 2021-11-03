/**
 Staff - Staff of restaurant.
 Each staff has a name, gender, job title and employee ID.
 @author Chua Yi Jie
 @version 1.0
 @since 2021-10-23
*/

package main;

public class Staff {

	/**
	* Name of staff
	*/
    private String name;

	/**
	* Gender of staff
	*/
	private String gender;

	/**
	* Job title of staff
	*/
	private String jobTitle;

	/**
	* Unique staff id identifying a unique staff instance
	*/
	private int employeeID;

	/**
	* @return Staff's name, gender, job title and ID in a string
	*/
	public String toString(){
		return "Name: " + this.name + "\nGender: " + this.gender + "\nJob title: " + this.jobTitle + "\nEmployee ID: " + this.employeeID;
	}

	/**
	* Create a new Staff object
	* @param name - name of staff
	* @param gender - gender of staff
	* @param jobTitle - job title of staff
	* @param employeeID - ID of staff
	*/
	public Staff(String name, String gender, String jobTitle, int employeeID) {
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.employeeID = employeeID;
	}

	/**
	* get the name of the staff
	* @return name of staff
	*/
	public String getName(){ 
		return name; 
	}

	/**
	* Get the gender of the staff
	* @return gender of staff
	*/
	public String getGender(){ 
		return gender; 
	}

	/**
	* Get the job title of the staff
	* @return job title of staff
	*/
	public String getJobTitle(){ 
		return jobTitle; 
	}

	/**
	* Get the ID of the staff
	* @return ID of staff
	*/
	public int getEmployeeID(){ 
		return employeeID; 
	}

	/**
	* set new name for staff
	* @param name is the new name for staff
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	* set new gender for staff
	* @param gender is the new gender for staff
	*/
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	/**
	* set new job title for staff
	* @param jobTitle is the new job title for staff
	*/
	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	/**
	* set new staff ID for staff
	* @param employeeID is the new staff ID for staff
	*/
	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}
}

