package service.persistence;

import service.persistence.domain.User;


public interface UserDao {
	
	User getUserByName(String name);
	
	void saveOrUpdateUser(User user);
	
}
