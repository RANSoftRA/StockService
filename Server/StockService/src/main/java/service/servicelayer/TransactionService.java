package service.servicelayer;

import service.data.PortfolioResponse;
import service.misc.TransactionError;

public interface TransactionService {
	PortfolioResponse addTransaction(String symbol, int amount, boolean isSell) throws TransactionError;
}
