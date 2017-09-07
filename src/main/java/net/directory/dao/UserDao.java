package net.directory.dao;

import net.directory.models.User;

import java.util.List;

/**
 *
 */
public interface UserDao {
	
	List<User> getUser();
	
	void addUser(User user);
	
	void removeUser(int id);
	
	void updateUser(User user, int id);

}
