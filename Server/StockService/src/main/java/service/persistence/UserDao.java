package service.persistence;


public interface UserDao {
	
	boolean isSessionValid(String session);
	
	boolean getUserByName(String name);
	
}
