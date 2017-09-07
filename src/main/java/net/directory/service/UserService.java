package net.directory.service;

import net.directory.models.User;

import java.util.List;

/**
 *
 */
public interface UserService {
	
	User getUser(String login);
	
	List<User> getUser();
	
	void addUser(User user);
	
	void removeUser(int id);
	
	void updateUser(User user, int id);
}
