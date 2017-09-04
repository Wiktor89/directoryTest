package net.directory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class LoginController {
	
	
	@RequestMapping(value = "/selection123", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
}
