package service.data;

import java.util.List;

import service.persistence.domain.Transaction;

public class TransactionResponse {
	
	private List<Transaction> transaction;
	
	private List<PortfolioStock> portfolioStock;

	public TransactionResponse(List<Transaction> transaction,
			List<PortfolioStock> portfolioStock) {
		super();
		this.transaction = transaction;
		this.portfolioStock = portfolioStock;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public List<PortfolioStock> getPortfolioStock() {
		return portfolioStock;
	}

	public void setPortfolioStock(List<PortfolioStock> portfolioStock) {
		this.portfolioStock = portfolioStock;
	}
	
	

}
