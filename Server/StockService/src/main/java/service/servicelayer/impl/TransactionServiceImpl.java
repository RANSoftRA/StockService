package service.servicelayer.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.data.TransactionResponse;
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
	public TransactionResponse addTransaction(String symbol, int amount,
			boolean isSell) {
		TransactionType transType = isSell ? TransactionType.SELL
				: TransactionType.BUY;

		AppUser user = appUserService.getAuthenticatedUser();

		transactionDao.saveOrUpdateTransaction(new StockTransaction(symbol, amount,
				new Date(), yql.getCurrentPrice(symbol), transType, user));

		return appUserService.getAuthenticatedUserTransactions();
	}

	@Override
	@Transactional(readOnly=true)
	public TransactionResponse getTransactions() {
		return appUserService.getAuthenticatedUserTransactions();
	}

}
