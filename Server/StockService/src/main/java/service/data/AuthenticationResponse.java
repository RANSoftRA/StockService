package service.data;

import java.util.List;

import service.persistence.domain.Transaction;

public class AuthenticationResponse {
	
	private String sessionKey;
	
	private List<Transaction> transactions;
	
	private List<PortfolioStock> portfolioStocks;

	public AuthenticationResponse(String sessionKey,
			List<Transaction> transactions, List<PortfolioStock> portfolioStocks) {
		super();
		this.sessionKey = sessionKey;
		this.transactions = transactions;
		this.portfolioStocks = portfolioStocks;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<PortfolioStock> getPortfolioStocks() {
		return portfolioStocks;
	}

	public void setPortfolioStocks(List<PortfolioStock> portfolioStocks) {
		this.portfolioStocks = portfolioStocks;
	}
}
