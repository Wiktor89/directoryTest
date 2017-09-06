package net.directory.dao;

import net.directory.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User getUser(String login) {
		Session ses = sessionFactory.getCurrentSession();
		User user = (User) ses.get(User.class, login);
		if (user.getLogin().equals(login)){
			logger.info("user "+user.getLogin());
			return user;
		}
		return null;
	}
}
