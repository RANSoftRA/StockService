package service.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.servicelayer.AppUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(method = RequestMethod.POST)
	public void registerUser(
			@RequestParam(value = "un", required = true) String username,
			@RequestParam(value = "pw", required = true) String password) {
		
		appUserService.registerUser(username, password);
	}

}
