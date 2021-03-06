package net.directory.dao;

import net.directory.models.Role;
import net.directory.models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    static final Logger logger = LoggerFactory.getLogger (UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public java.util.List<User> getUser() {
        Session ses = sessionFactory.getCurrentSession ();
        List list = ses.createQuery ("from User ").list ();
        List<User> users = new ArrayList<> ();
        users.addAll (list);
        logger.info ("get Users " + users);
        logger.debug ("get Users " + users);
        return users;
    }

    @Override
    public void addUser(User user) {
        Session ses = sessionFactory.getCurrentSession ();
        Criteria criteriaRole = ses.createCriteria (Role.class);
        Role role = (Role) criteriaRole.add (Restrictions.eq ("title", "ROLE_USER")).uniqueResult ();
        Criteria criteriaUser = ses.createCriteria (User.class);
        if (criteriaUser.add (Restrictions.eq ("login", user.getLogin ())).uniqueResult () == null) {
            user.setRole (role);
            ses.save (user);
        }
        logger.info ("get Users " + user);
        logger.debug ("get Users " + user);
    }

    @Override
    public void removeUser(int id) {
        Session ses = sessionFactory.getCurrentSession ();
        User user = (User) ses.get (User.class, new Integer (id));
        if (user != null) {
            ses.delete (user);
            logger.info ("delete user " + user);
            logger.debug ("delete user " + user);
        }
    }

    @Override
    public void updateUser(User user, int id) {
        Session ses = sessionFactory.getCurrentSession ();
        User userUpd = (User) ses.get (User.class, new Integer (id));
        if (userUpd != null) {
            user.setId (userUpd.getId ());
            ses.update (user);
            logger.info ("update user " + user);
            logger.debug ("update user " + user);
        }
    }
}
