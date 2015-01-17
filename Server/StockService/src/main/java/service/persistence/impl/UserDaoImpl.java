package service.persistence.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import service.persistence.AppUserDao;
import service.persistence.domain.AppUser;

@Repository
public class UserDaoImpl extends BaseDao implements AppUserDao {

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public AppUser getUserByName(String name) {
		return (AppUser) getCurrentSession().createCriteria(AppUser.class)
				.add(Restrictions.eq("username", name)).uniqueResult();
	}

	@Override
	public void saveOrUpdateUser(AppUser user) {
		getCurrentSession().saveOrUpdate(user);
	}

}
