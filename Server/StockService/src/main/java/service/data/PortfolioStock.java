package service.data;

public class PortfolioStock {
	
	private String symbol;
	private String name;
	private double amount; 
	private double balance;
	
	public PortfolioStock(String symbol, String name, double amount,
			double balance) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.amount = amount;
		this.balance = balance;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	} 

}
