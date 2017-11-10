package net.directory.dao;

import net.directory.models.*;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
@Repository("contactDao")
public class ContactsDaoImpl implements ContactDao {
	
	private static final Logger LOGGER = Logger.getLogger(ContactsDaoImpl.class);
	
	@Autowired(required = true)
	@Qualifier(value = "hibernate")
	private SessionFactory sessionFactory;
	private User user;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addContact(Entity entity) {
		Contact contact = (Contact) entity;
		Session ses  = sessionFactory.getCurrentSession();
		User user = (User) ses.get(User.class, this.user.getId());
		contact.setUser(user);
		ses.save(contact);
		LOGGER.info("User " +user);
		LOGGER.debug("Contact was successfully added " +contact);
	}
	
	@Override
	public boolean updateContact(List<String> attContact) {
		Session ses  = sessionFactory.getCurrentSession();
		Contact contact = (Contact) ses.get(Contact.class,Integer.valueOf(attContact.get(0)));
		contact.setFio(attContact.get(1));
		if (!(attContact.get(2).equalsIgnoreCase("nop"))) {
			contact.setPhone(attContact.get(2));
		}
		if (!(attContact.get(3).equalsIgnoreCase("noe"))) {
			contact.setEmail(attContact.get(3));
		}
		LOGGER.debug("update contact "+contact);
		LOGGER.info("update contact "+contact);
		ses.update(contact);
		return true;
	}
	
	@Override
	public boolean removeContact(Integer id) {
		boolean result = false;
		Session ses  = sessionFactory.getCurrentSession();
		Contact contact = (Contact) ses.get(Contact.class, new Integer(id));
		if (contact != null){
			ses.delete(contact);
			result = true;
		}
		LOGGER.info("remove contact id"+ contact);
		LOGGER.debug("remove contact id"+ contact);
		return result;
	}
	
	@Override
	public boolean appGroupContact(List<String> attContact) {
		boolean result = false;
		Session ses  = sessionFactory.getCurrentSession();
		Contact contact = (Contact) ses.get(Contact.class, getContact(attContact.get(0)).getId());
		Criteria criteria = ses.createCriteria(Group.class);
		Group group = (Group) criteria.add(Restrictions.eq("name", attContact.get(1))).uniqueResult();
		if (group != null && group.getName().trim().equalsIgnoreCase(attContact.get(1)) && contact != null) {
			if (contact.getGroups() != null){
				contact.getGroups().add(group);
			}else {
				Set<Group> groups = new TreeSet<>();
				groups.add(group);
				contact.setGroup(groups);
			}
			result = true;
			ses.update(contact);
		}
		LOGGER.info("app group contact "+contact);
		LOGGER.debug("app group contact "+contact);
		return result;
	}
	
	@Override
	public boolean removeGroupContact(List<String> attr) {
		boolean result = false;
		Session ses  = sessionFactory.getCurrentSession();
		Criteria criteria = ses.createCriteria(Contact.class);
		Contact contact = (Contact) criteria.add(Restrictions.eq("fio", attr.get(0))).uniqueResult();
		Criteria criteria1 = ses.createCriteria(Group.class);
		Group group = (Group) criteria1.add(Restrictions.eq("name", attr.get(1))).uniqueResult();
		if (group != null ){
			contact.getGroups().remove(group);
			result = true;
			ses.update(contact);
			LOGGER.info("remove group contact "+contact);
			LOGGER.debug("Get contacts "+contact);
		}
		return result;
	}
	
	@Override
	public Set<Contact> getContacts() {
		Session ses  = sessionFactory.getCurrentSession();
		User user = (User) ses.get(User.class, this.user.getId());
		Set<Contact> listContacts = user.getListContacts();
		user.getListContacts().size();
		LOGGER.info("Get contacts "+listContacts);
		LOGGER.debug("Get contacts "+listContacts);
		return listContacts;
	}
	
	@Override
	public boolean existContact(String name) {
		boolean result = false;
		Session ses  = sessionFactory.getCurrentSession();
		Criteria criteria = ses.createCriteria(Contact.class);
		Contact contact = (Contact) criteria.add(Restrictions.eq("fio", name)).uniqueResult();
		if (contact != null){
			result = true;
			LOGGER.info("exist contact "+contact);
			LOGGER.debug("exist contact "+contact);
		}
		return  result;
	}
	
	@Override
	public Contact getContact(String fio) {
		Session ses  = sessionFactory.getCurrentSession();
		Criteria criteria = ses.createCriteria(Contact.class);
		Contact contact = (Contact) criteria.add(Restrictions.eq("fio", fio)).uniqueResult();
		if (contact != null){
			return contact;
		}
		LOGGER.info("Get contact "+contact);
		LOGGER.debug("Get contact "+contact);
		return  null;
	}
	
	@Override
	public Contact searchName(String fio) throws SQLException {
		return getContact(fio);
	}
	
	@Override
	public User authorizationPage(String login) {
		Session ses  = sessionFactory.getCurrentSession();
		Criteria criteria = ses.createCriteria(User.class);
		user = (User) criteria.add(Restrictions.eq("login",login)).uniqueResult();
		if (user != null && !user.getEnable()){
			user.setEnable(true);
			ses.update(user);
		}
		LOGGER.info("authorization user "+ this.user);
		LOGGER.debug("authorization user "+ this.user);
		return user;
	}
	
	@Override
	public Contact getContact(Integer id){
		Session ses  = sessionFactory.getCurrentSession();
		Contact contact = (Contact) ses.get(Contact.class, id);
		if (contact != null){
			return contact;
		}
		LOGGER.debug("get contact "+ contact);
		LOGGER.info("get contact "+ contact);
		return  null;
	}
}
