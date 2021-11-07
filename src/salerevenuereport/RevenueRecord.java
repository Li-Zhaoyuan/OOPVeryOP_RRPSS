/**
 RevenueRecord - Entity of Revenue Record
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

public class RevenueRecord {
	private double netSales;
	private double discount;
	private int year;
	private int month;
	private int day;
	
	/**
	* Constructor for RevenueRecord
	*/
	public void RevenueRecord(double netSales, int year, int month, int day) {
		this.netSales = netSales;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public double getNetSales()
	{
		return netSales;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	
	public int getDay()
	{
		return day;
	}
	
	public void setNetSales(double netSales)
	{
		this.netSales = netSales;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public void setMonth(int month)
	{
		this.month = month;
	}
	
	public void setDay(int day)
	{
		this.day = day;
	}
}
