package net.directory.controller;

import net.directory.models.Role;
import net.directory.models.User;
import net.directory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class MainController {
	
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/testRest",method = RequestMethod.GET)
	public User login(@RequestParam(value = "name") String login) {
		User user = new User();
		user.setPassword("asdasdasdasd");
		Role role = new Role("admin");
		user.setPassword("asdasdasdasd");
		user.setRole(role);
		user.setLogin(login);
		return user;
	}
}
