package net.directory.service;

import net.directory.dao.ContactDao;
import net.directory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private ContactDao dao;
	
	@Autowired(required = true)
	@Qualifier("contactDao")
	public void setDao(ContactDao dao) {
		this.dao = dao;
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

}
