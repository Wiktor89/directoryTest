package net.directory.dao;

import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
@Repository("groupDao")
@Transactional
public class GroupsDaoImp implements GroupDao {

    private static final Logger LOGGER = Logger.getLogger(ContactsDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired(required = true)
    @Qualifier(value = "hibernate")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addGroup(Entity entity) {
        Group group = (Group) entity;
        String name = group.getName();
        Session ses = sessionFactory.getCurrentSession();
        Criteria criteria = ses.createCriteria(Group.class);
        Group group1 = (Group) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        if (group1 == null) {
            ses.saveOrUpdate(group);
        }
        LOGGER.info("Add " + group);
        LOGGER.debug("Add " + group);
    }

    @Override
    public boolean removeGroup(Integer id) {
        boolean result = false;
        Session ses = sessionFactory.getCurrentSession();
        Group group = (Group) ses.get(Group.class, id);
        if (group != null) {
            ses.delete(group);
            result = true;
        }
        LOGGER.info("Remove " + group);
        LOGGER.debug("Remove " + group);
        return result;
    }

    @Override
    public boolean updateGroup(Integer id, String name) {
        boolean result = false;
        Session ses = sessionFactory.getCurrentSession();
        Group group = (Group) ses.get(Group.class, new Integer(id));
        Criteria criteria = ses.createCriteria(Group.class);
        Group group1 = (Group) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        if (group1 == null) {
            group.setName(name);
            ses.update(group);
            result = true;
        }
        LOGGER.info("Update " + group);
        LOGGER.debug("Update " + group);
        return result;
    }

    @Override
    public boolean existGroup(Integer id) {
        boolean result = false;
        Session ses = sessionFactory.getCurrentSession();
        Group group = (Group) ses.get(Group.class, id);
        if (group != null) {
            result = true;
        }
        LOGGER.info("exist group " + group);
        LOGGER.debug("exist group " + group);
        return result;
    }

    @Override
    public Set<Group> getGroups() {
        Session ses = sessionFactory.getCurrentSession();
        List list = ses.createQuery("from Group").list();
        Set<Group> groups = new TreeSet<>();
        groups.addAll(list);
        LOGGER.info("get groups " + groups);
        LOGGER.debug("get groups " + groups);
        return groups;
    }

    @Override
    public Set<Contact> getContactsGroup(String name) {
        Set<Contact> contact = new TreeSet<>();
        Session ses = sessionFactory.getCurrentSession();
        Criteria criteria = ses.createCriteria(Group.class);
        Group group = (Group) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        if (group != null) {
            contact.addAll(group.getContact());

        }
        LOGGER.info("get contacts group = " + group + " contacts = " + contact);
        LOGGER.debug("get contacts group = " + group + " contacts = " + contact);
        return contact;
    }

    @Override
    public Integer numberUsers() {
        Session ses = sessionFactory.getCurrentSession();
        int size = ses.createQuery("from User").list().size();
        LOGGER.info("number users * " + size);
        LOGGER.debug("number users * " + size);
        return size;
    }

    @Override
    public Integer numberContacts(String name) {
        int count = 0;
        Session ses = sessionFactory.getCurrentSession();
        Criteria criteria = ses.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", name)).uniqueResult();
        if (user != null) {
            count = user.getListContacts().size();
            LOGGER.info("Number contacts *" + user.getListContacts().size());
            LOGGER.debug("Number contacts *" + user.getListContacts().size());
        }
        return count;
    }

    @Override
    public Integer quantityGroupsUser(String name) {
        int countGroups = 0;
        Session ses = sessionFactory.getCurrentSession();
        Criteria criteria = ses.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", name)).uniqueResult();
        if (user != null) {
            for (Contact contact : user.getListContacts()) {
                if (!contact.getGroups().isEmpty() && contact.getGroups().size() > 0) {
                    countGroups += contact.getGroups().size();
                }
            }
            LOGGER.info("Quantity groups user " + user);
            LOGGER.debug("Quantity groups user " + user);
        }
        return countGroups;
    }

    @Override
    public Integer averageNumberContactsGroups() {
        Session ses = sessionFactory.getCurrentSession();
        List groups = ses.createQuery("from Group ").list();
        List contacts = ses.createQuery("from Contact ").list();
        int i = contacts.size() / groups.size();
        LOGGER.info("Average number contacts groups " + i);
        LOGGER.debug("Average number contacts groups " + i);
        return i;
    }

    @Override
    public Integer averageNumberContactsUser() {
        Session ses = sessionFactory.getCurrentSession();
        List users = ses.createQuery("from User ").list();
        List contacts = ses.createQuery("from Contact ").list();
        int i = contacts.size() / users.size();
        LOGGER.info("Average number contacts user " + i);
        LOGGER.debug("Average number contacts user " + i);
        return i;
    }

    @Override
    public Set<User> userWithContactsMin_10() {
        Session ses = sessionFactory.getCurrentSession();
        List list = ses.createQuery("from User ").list();
        Set<User> users = new TreeSet<>();
        for (Object o : list) {
            User user = (User) o;
            if (user.getListContacts().size() < 10) {
                users.add(user);
                LOGGER.info("User contacts < 10 " + user);
                LOGGER.debug("User contacts < 10 " + user);
            }
        }
        return users;
    }
}
