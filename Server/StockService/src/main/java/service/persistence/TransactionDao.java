package service.persistence;

import java.util.List;

import service.persistence.domain.AppUser;
import service.persistence.domain.StockTransaction;


public interface TransactionDao {
	
	void saveOrUpdateTransaction(StockTransaction t);		

	List<StockTransaction> getTransactions(AppUser user);
	
}
