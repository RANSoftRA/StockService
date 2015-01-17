package service.persistence;

import service.persistence.domain.AppUser;


public interface AppUserDao {
	
	AppUser getUserByName(String name);
	
	void saveOrUpdateUser(AppUser user);
	
}
