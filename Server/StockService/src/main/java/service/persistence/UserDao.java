package service.persistence;

import service.persistence.domain.User;

public interface UserDao {
	
	public User getUserByUserName(String username);
	
}
