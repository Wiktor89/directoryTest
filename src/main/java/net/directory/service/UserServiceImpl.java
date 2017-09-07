package net.directory.service;

import net.directory.dao.ContactDao;
import net.directory.dao.UserDao;
import net.directory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private ContactDao dao;
	private UserDao userDao;
	
	@Autowired(required = true)
	@Qualifier("contactDao")
	public void setDao(ContactDao dao) {
		this.dao = dao;
	}
	
	@Autowired(required = true)
	@Qualifier("userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User getUser(String login) {
		try {
			return this.dao.authorizationPage(login);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<User> getUser() {
		return userDao.getUser();
	}
	
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	@Override
	public void removeUser(int id) {
		userDao.removeUser(id);
	}
	
	@Override
	public void updateUser(User user, int id) {
		userDao.updateUser(user,id);
	}
	
}
