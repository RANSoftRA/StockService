package service.rest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.data.TransactionResponse;
import service.data.service.YQLService;
import service.persistence.TransactionDao;
import service.persistence.UserDao;
import service.persistence.domain.Transaction;
import service.persistence.domain.Transaction.TransactionType;
import service.persistence.domain.User;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private YQLService yql;
	
	@RequestMapping(value = "/finance/transactions", method = RequestMethod.POST)
	@Transactional
	public TransactionResponse addTransaction(
			@RequestParam(value = "stocksymbol" , required=true) String symbol,
			@RequestParam(value = "amount", required = true) int amount,
			@RequestParam(value = "type", required = true) boolean isSell) {
		
		TransactionType transType = isSell ? TransactionType.SELL : TransactionType.BUY;
		
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userDao.getUserByName(principal);
		
		transactionDao.saveOrUpdateTransaction(new Transaction(symbol, amount,
				new Date(), yql.getCurrentPrice(symbol),
				transType, user));
		
		return new TransactionResponse(null, null);
	}
	
	@Transactional
	@RequestMapping(value = "/users/{username}/transactions", method = RequestMethod.GET)
	public TransactionResponse getUserTransactions() {
		return null;
	}
}
