package service.data;

import java.util.List;

import service.persistence.domain.StockTransaction;

public class TransactionResponse {
	
	private List<StockTransaction> transaction;
	
	private List<PortfolioStock> portfolioStock;

	public TransactionResponse(List<StockTransaction> transaction,
			List<PortfolioStock> portfolioStock) {
		super();
		this.transaction = transaction;
		this.portfolioStock = portfolioStock;
	}

	public List<StockTransaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<StockTransaction> transaction) {
		this.transaction = transaction;
	}

	public List<PortfolioStock> getPortfolioStock() {
		return portfolioStock;
	}

	public void setPortfolioStock(List<PortfolioStock> portfolioStock) {
		this.portfolioStock = portfolioStock;
	}
	
	

}
