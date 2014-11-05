package service.rest.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.persistence.UserDao;

@Component
@Aspect
public class ServiceAspect {

	
	@Autowired
	private UserDao userDao;
	
	@Before("execution(* service.rest.controller..*(..)) && args(sessionId,..)")
	public void checkSession(String sessionId) throws Exception {
		
		// Check if Session Exists!
		
	}
	
	
}
