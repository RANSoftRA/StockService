package service.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import service.persistence.TransactionDao;
import service.persistence.domain.Transaction;

@Repository
public class TransactionDaoImpl extends BaseDao implements TransactionDao {

	@Autowired
	public TransactionDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void saveOrUpdateTransaction(Transaction t) {
		getCurrentSession().saveOrUpdate(t);		
	}

}
