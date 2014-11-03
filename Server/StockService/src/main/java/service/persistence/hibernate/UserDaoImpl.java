package service.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import service.persistence.UserDao;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
