package service.data;

import java.util.List;

import service.persistence.domain.AppUser;
import service.persistence.domain.StockTransaction;

public class PortfolioResponse {
	
	private List<StockTransaction> transaction;
	
	private List<PortfolioStock> portfolioStock;
	
	private AppUser appUser;

	public PortfolioResponse(List<StockTransaction> transaction,
			List<PortfolioStock> portfolioStock, AppUser appUser) {
		super();
		this.transaction = transaction;
		this.portfolioStock = portfolioStock;
		this.appUser = appUser;
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

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}
