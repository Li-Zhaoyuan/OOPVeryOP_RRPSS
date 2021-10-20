/**
 description of this class
 @author Zhaoyuan
 @version 1.0
 @since 2021-10-20
*/
package menuitem;

public class MenuItem {
	protected String name;
	protected String description;
	protected double price;
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
}
