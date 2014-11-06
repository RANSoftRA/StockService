package service.persistence.hibernate;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import service.persistence.UserDao;
import service.persistence.domain.User;
import service.persistence.domain.UserSession;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean isSessionValid(String session) {
		Criteria crit = getCurrentSession().createCriteria(UserSession.class);
		crit.add(Restrictions.eq("uuid", session));
		
		if(crit.list().size() != 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean getUserByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveOrUpdateUser(User user) {
		getCurrentSession().saveOrUpdate(user);
	}

}
