package service.servicelayer.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.data.Stock;
import service.data.PortfolioResponse;
import service.misc.TransactionError;
import service.persistence.TransactionDao;
import service.persistence.domain.AppUser;
import service.persistence.domain.StockTransaction;
import service.persistence.domain.StockTransaction.TransactionType;
import service.servicelayer.AppUserService;
import service.servicelayer.TransactionService;
import service.servicelayer.YQLService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private YQLService yql;
	
	@Override
	@Transactional(readOnly=false)
	public PortfolioResponse addTransaction(String symbol, int amount,
			boolean isSell) throws TransactionError {
		TransactionType transType = isSell ? TransactionType.SELL
				: TransactionType.BUY;

		AppUser user = appUserService.getAuthenticatedUser();
		
		Stock stock = yql.getStock(symbol);
		
		double currentPrice = stock.getLastTradedPriceOnly();
		
		double price = currentPrice * amount;
		
		// When user wants to buy
		if(transType == TransactionType.BUY) {
			// User cannot afford Stocks!
			if(user.getBalance() < price) {
				throw new TransactionError(TransactionError.Type.NOT_ENOUGH_MONEY);
			}
			
			// Not enough stocks available on market
			if(stock.getVolume() < amount) {
				throw new TransactionError(TransactionError.Type.NOT_ENOUGH_STOCKS);
			}
			
			user.setBalance(user.getBalance() - price);
		}
		
		// When user wants to sell
		if(transType == TransactionType.SELL) {
			user.setBalance(user.getBalance() + price);
		}
		
		
		
		transactionDao.saveOrUpdateTransaction(new StockTransaction(symbol, amount,
				new Date(), currentPrice, transType, user));

		return appUserService.getAuthenticatedUserTransactions();
	}

}
