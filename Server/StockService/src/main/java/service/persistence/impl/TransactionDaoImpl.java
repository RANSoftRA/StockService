package service.persistence.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import service.persistence.TransactionDao;
import service.persistence.domain.AppUser;
import service.persistence.domain.StockTransaction;

@Repository
public class TransactionDaoImpl extends BaseDao implements TransactionDao {

	@Autowired
	public TransactionDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void saveOrUpdateTransaction(StockTransaction t) {
		getCurrentSession().saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockTransaction> getTransactions(AppUser user) {

		return getCurrentSession().createCriteria(StockTransaction.class)
				.add(Restrictions.eq("appUser", user)).list();
	}

}
