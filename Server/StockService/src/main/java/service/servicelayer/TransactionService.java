package service.servicelayer;

import service.data.TransactionResponse;

public interface TransactionService {
	TransactionResponse addTransaction(String symbol, int amount, boolean isSell);
	
	TransactionResponse getTransactions();
}
