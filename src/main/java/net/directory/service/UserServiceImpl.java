package net.directory.service;

import net.directory.dao.UserDao;
import net.directory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao dao;
	
	@Autowired(required = true)
	@Qualifier("userDao")
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	@Override
	public User getUser(String login) {
		return dao.getUser(login);
	}
}
