package menuitem;

/**
MenuItem - parent class that defines all the shared features needed for a menu item.
@author Li Zhaoyuan
@version 1.0
@since 2021-10-20
*/
public abstract class MenuItem {
	/**
	* Protected String variable that stores the name of this object
	*/
	protected String name;
	
	/**
	* Protected String variable that stores the description of this object
	*/
	protected String description;
	
	/**
	* Protected double variable that stores the price of this object
	*/
	protected double price;
	
	/**
	* Accessor function to get the name (String) of the object
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* Accessor function to get the description (String) of the object
	*/
	public String getDescription()
	{
		return description;
	}
	
	/**
	* Accessor function to get the price (double) of the object
	*/
	public double getPrice()
	{
		return price;
	}
	
	/**
	* Mutator function to set the name of the object
	* @param name - name of object, String
	*/
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	* Mutator function to set the description of the object
	* @param description - description of object, String
	*/
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	* Mutator function to set the price of the object
	* @param price - price of object, double price
	*/
	public void setPrice(double price)
	{
		this.price = price;
	}
}
