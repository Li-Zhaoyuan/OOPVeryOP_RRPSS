package salerevenuereport;

/**
IndividualSaleRecord - Entity of Individual Sale Record
@author Heng Zheng Ping
@version 1.0
@since 2021-11-05
*/
public class IndividualSaleRecord {
	/**
	* String variable that stores the name of menu item in the individual sale record
	*/
	private String name;
	
	/**
	* integer variable that stores the quantity of menu item in the individual sale record
	*/
	private int quantity;
	
	/**
	* integer variable that stores the year of the individual sale record
	*/
	private int year;
	
	/**
	* integer variable that stores the month of the individual sale record
	*/
	private int month;
	
	/**
	* integer variable that stores the day of the individual sale record
	*/
	private int day;
	
	/**
	* Constructor for IndividualSaleRecord
	*/
	public IndividualSaleRecord() {

	}
	
	/**
	* Accessor of the menu item's name for each record
	* @return name
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* Accessor of the menu item's quantity for each record
	* @return quantity
	*/
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	* Accessor of the year for each record
	* @return year
	*/
	public int getYear()
	{
		return year;
	}
	
	/**
	* Accessor of the month for each record
	* @return month
	*/
	public int getMonth()
	{
		return month;
	}
	
	/**
	* Accessor of the day for each record
	* @return day
	*/
	public int getDay()
	{
		return day;
	}
	
	/**
	* Mutator of the menu item's name for each record
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	* Mutator of the menu item's quantity for each record
	*/
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	/**
	* Mutator of the year for each record
	*/
	public void setYear(int year)
	{
		this.year = year;
	}
	
	/**
	* Mutator of the month for each record
	*/
	public void setMonth(int month)
	{
		this.month = month;
	}
	
	/**
	* Mutator of the day for each record
	*/
	public void setDay(int day)
	{
		this.day = day;
	}
}
