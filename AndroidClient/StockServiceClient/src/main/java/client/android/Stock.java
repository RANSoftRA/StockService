package main.java.client.android;

public class Stock {

	private String id;
	private double price;
	
	public Stock(String id, double price) {
		
		this.id = id;
		this.price = price;
		
	}
	
	public String getId()
	{
		return this.id;
		
	}
	
	public double getPrice()
	{
		return this.price;
		
	}
	
}
