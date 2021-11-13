package salerevenuereport;

/**
RevenueRecord - Entity of Revenue Record
@author Heng Zheng Ping
@version 1.0
@since 2021-11-03
*/
public class RevenueRecord {
	/**
	* double variable that stores the netSales of order in the revenue record
	*/
	private double netSales;
	
	/**
	* integer variable that stores the year of the revenue record
	*/
	private int year;
	
	/**
	* integer variable that stores the month of the revenue record
	*/
	private int month;
	
	/**
	* integer variable that stores the day of the revenue record
	*/
	private int day;
	
	/**
	* Constructor for RevenueRecord
	*/
	public RevenueRecord() {
		
	}
	
	/**
	* Accessor of the order's net sales
	* @return netSales
	*/
	public double getNetSales()
	{
		return netSales;
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
	* Mutator of the order's netSales
	* @param netSales the net sales made to be set
	*/
	public void setNetSales(double netSales)
	{
		this.netSales = netSales;
	}
	
	/**
	* Mutator of the year for each record
	* @param year the year for the record
	*/
	public void setYear(int year)
	{
		this.year = year;
	}
	
	/**
	* Mutator of the month for each record
	* @param month the month of the record
	*/
	public void setMonth(int month)
	{
		this.month = month;
	}
	
	/**
	* Mutator of the day for each record
	* @param day the day of the record
	*/
	public void setDay(int day)
	{
		this.day = day;
	}
}
