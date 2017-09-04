package net.directory.dao;

import net.directory.models.User;

/**
 *
 */
public interface UserDao {
	User getUser(String login);

}
