package service.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.persistence.UserDao;
import service.persistence.domain.User;

@RestController
@RequestMapping("/users")
@Transactional
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public User getUserByUsername(@PathVariable String username) {
		return userDao.getUserByUserName(username);
	}
	
	
	
}
