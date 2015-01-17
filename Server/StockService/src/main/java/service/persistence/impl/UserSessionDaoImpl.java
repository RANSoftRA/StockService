package service.persistence.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import service.persistence.UserSessionDao;

@Repository
public class UserSessionDaoImpl extends BaseDao implements UserSessionDao {

	@Autowired
	public UserSessionDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
