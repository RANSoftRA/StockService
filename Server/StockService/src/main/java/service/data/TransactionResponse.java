package service.data;

import service.persistence.domain.Transaction;

public class TransactionResponse {
	
	private Transaction transaction;
	private PortfolioStock portfolioStock;
	
	public TransactionResponse(Transaction transaction,
			PortfolioStock portfolioStock) {
		super();
		this.transaction = transaction;
		this.portfolioStock = portfolioStock;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public PortfolioStock getPortfolioStock() {
		return portfolioStock;
	}

	public void setPortfolioStock(PortfolioStock portfolioStock) {
		this.portfolioStock = portfolioStock;
	}
	

}
