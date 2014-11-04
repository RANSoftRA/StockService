package service.persistence;

import service.persistence.domain.Transaction;


public interface TransactionDao {
	
	public void saveOrUpdateTransaction(Transaction t);		

}
