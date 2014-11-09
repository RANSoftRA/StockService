package service.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
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


	@RequestMapping(value = "/{username}", method = RequestMethod.POST)
	public AuthenticationResponse registerUser(
			@RequestParam(value = "un", required=true) String username,
			@RequestParam(value = "pw", required=true) String password) {
		
		return null;
	}

}
