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
@Repository
public class UserDaoImpl implements UserDao {
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public User getUser(String login) {
		Session ses = sessionFactory.getCurrentSession();
		Object o = ses.get(User.class, login);
		User user = (User) o;
		return user;
	}
}
