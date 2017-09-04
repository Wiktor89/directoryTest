package net.directory.service;

import net.directory.models.User;

/**
 *
 */
public interface UserService {
	
	User getUser(String login);

}
