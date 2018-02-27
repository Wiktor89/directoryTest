package net.directory.service;

import net.directory.dao.ContactDao;
import net.directory.dao.UserDao;
import net.directory.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger (UserDetailsServiceImpl.class);

    @Autowired
    private ContactDao dao;

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String login) {
        try {
            LOGGER.info ("*** --- getUsers  --- ***");
            return this.dao.authorizationPage (login);
        } catch (SQLException e) {
            LOGGER.error ("*** --- getUsers  --- ***", e.toString ());
        }
        return null;
    }

    @Override
    public List<User> getUser() {
        return userDao.getUser ();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser (user);
    }

    @Override
    public void removeUser(int id) {
        userDao.removeUser (id);
    }

    @Override
    public void updateUser(User user, int id) {
        userDao.updateUser (user, id);
    }

}
