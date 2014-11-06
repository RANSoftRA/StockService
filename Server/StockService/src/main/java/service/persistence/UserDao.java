package service.persistence;

import service.persistence.domain.User;


public interface UserDao {
	
	boolean isSessionValid(String session);
	
	boolean getUserByName(String name);
	
	void saveOrUpdateUser(User user);
	
}
