package service.rest.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.data.AuthenticationResponse;
import service.persistence.UserDao;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logoutUser(@RequestParam(value = "sessionID", required = true) String sessionId) {
		
	}


	@Transactional
	@RequestMapping(value = "/{username}/login", method = RequestMethod.POST)
	public AuthenticationResponse loginUser(@PathVariable String username,
			@RequestParam(value = "pw") String password) {
		
		userDao.isSessionValid("lskadjfl");
		
		return null;
	}


	@RequestMapping(value = "/{username}", method = RequestMethod.PUT)
	public AuthenticationResponse registerOrUpdateUser(
			@RequestParam(value = "sessionID", required = false) String sessionId,
			@PathVariable String username,
			@RequestParam(value = "pw") String password) {
		
		return null;
	}

}
