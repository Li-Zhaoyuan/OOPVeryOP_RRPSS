/**
 DiscountRecord - Entity of Discount Record
 @author Heng Zheng Ping
 @version 1.0
 @since 2021-11-03
*/

package salerevenuereport;

public class DiscountRecord {
	private double discount;
	private int year;
	private int month;
	private int day;
	
	/**
	* Constructor for DiscountRecord
	*/
	public void DiscountRecord(double discount, int year, int month, int day) {
		this.discount = discount;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public double getDiscount()
	{
		return discount;
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
	
	public void setDiscount(double discount)
	{
		this.discount = discount;
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
