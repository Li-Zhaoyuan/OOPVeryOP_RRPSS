package main;

public class Staff {
    private String name;
	private String gender;
	private String jobTitle;
	private int employeeID;

	public String toString(){
		return " Name: " + this.name + " Gender: " + this.gender + " Job title: " + this.jobTitle + "Employee ID: " + this.employeeID;
	}

	public Staff(String name, String gender, String jobTitle, int employeeID) {
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.employeeID = employeeID;
	}

	public String getName(){ 
		return name; 
	}

	public String getGender(){ 
		return gender; 
	}
	public String getJobTitle(){ 
		return jobTitle; 
	}

	public int getEmployeeID(){ 
		return employeeID; 
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}
}

