/**
 RevenueRecord - Entity of Revenue Record
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

public class RevenueRecord {
	private String name;
	private int quantity;
	private int year;
	private int month;
	private int day;
	
	public void RevenueRecord(String name, int quantity, int year, int month, int day) {
		this.name = name;
		this.quantity = quantity;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getQuantity()
	{
		return quantity;
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
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
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
