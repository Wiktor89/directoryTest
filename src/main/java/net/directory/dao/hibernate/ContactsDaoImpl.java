package net.directory.dao.hibernate;

import net.directory.dao.ContactDao;
import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;
import net.directory.utilits.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ContactsDaoImpl implements ContactDao {
	
	private static final Logger LOGGER = Logger.getLogger(ContactsDaoImpl.class);
	private static volatile ContactsDaoImpl contactsDao;
	private User user;
	
	public static ContactsDaoImpl getContactsDaoImpl(){
		if (contactsDao == null){
			synchronized (ContactsDaoImpl.class){
				if (contactsDao == null){
					contactsDao = new ContactsDaoImpl();
				}
			}
		}
		return contactsDao;
	}
	
	private ContactsDaoImpl() {
	}
	
	@Override
	public void addContact(Entity entity) {
		Contact contact = (Contact) entity;
		Session ses  = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction transaction = ses.beginTransaction();
		User user = (User) ses.get(User.class, this.user.getId());
		contact.setUser(user);
		LOGGER.info("User " +user);
		LOGGER.debug("Contact was successfully added " +contact);
		ses.save(contact);
		transaction.commit();
	}
	
	@Override
	public boolean updateContact(List<String> attContact) {
		Session ses  = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = ses.beginTransaction();
		Contact contact = (Contact) ses.get(Contact.class,Integer.valueOf(attContact.get(0)));
		contact.setFio(attContact.get(1));
		LOGGER.debug("update contact "+contact);
		LOGGER.info("update contact "+contact);
		if (!(attContact.get(2).equalsIgnoreCase("nop")))
				contact.setPhone(attContact.get(2));
		if (!(attContact.get(3).equalsIgnoreCase("noe")))
				contact.setEmail(attContact.get(3));
		ses.update(contact);
		transaction.commit();
		ses.close();
		return true;
	}
	
	@Override
	public boolean removeContact(Integer id) {
		boolean result = false;
		Session ses  = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = ses.beginTransaction();
		Contact contact = (Contact) ses.load(Contact.class, new Integer(id));
		LOGGER.info("remove contact id"+ contact);
		LOGGER.debug("remove contact id"+ contact);
		if (contact != null){
			ses.delete(contact);
			result = true;
		}
		transaction.commit();
		ses.close();
		return result;
	}
	
	@Override
	public boolean appGroupContact(List<String> attContact) {
		boolean result = false;
		Session ses  = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = ses.beginTransaction();
		Contact contact = (Contact) ses.get(Contact.class, getContact(attContact.get(0)).getId());
		Criteria criteria = ses.createCriteria(Group.class);
		Group group = (Group) criteria.add(Restrictions.eq("name", attContact.get(1))).uniqueResult();
		if (group != null && group.getName().trim().equalsIgnoreCase(attContact.get(1))) {
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
		ses.close();
		transaction.commit();
		return result;
	}
	
	@Override
	public boolean removeGroupContact(List<String> attr) {
		boolean result = false;
		Session ses  = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction transaction = ses.beginTransaction();
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
		transaction.commit();
		return result;
	}
	
	@Override
	public Set<Contact> getContacts() {
		Session ses  = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = ses.beginTransaction();
		User user = (User) ses.get(User.class, this.user.getId());
		Set<Contact> listContacts = new TreeSet<>();
		user.getListContacts().size();
		listContacts.addAll(user.getListContacts());
		LOGGER.info("Get contacts "+listContacts);
		LOGGER.debug("Get contacts "+listContacts);
		transaction.commit();
		ses.close();
		return listContacts;
	}
	
	@Override
	public boolean existContact(String name) {
		boolean result = false;
		Session ses  = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = ses.beginTransaction();
		Criteria criteria = ses.createCriteria(Contact.class);
		Contact contact = (Contact) criteria.add(Restrictions.eq("fio", name)).uniqueResult();
		if (contact != null){
			result = true;
			LOGGER.info("exist contact "+contact);
			LOGGER.debug("exist contact "+contact);
		}
		transaction.commit();
		ses.close();
		return  result;
	}
	
	@Override
	public Contact getContact(String fio) {
		Session ses  = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction transaction = ses.beginTransaction();
		Criteria criteria = ses.createCriteria(Contact.class);
		Contact contact = (Contact) criteria.add(Restrictions.eq("fio", fio)).uniqueResult();
		if (contact != null){
			return contact;
		}
		LOGGER.info("Get contact "+contact);
		LOGGER.debug("Get contact "+contact);
		transaction.commit();
		return  null;
	}
	
	@Override
	public Contact searchName(String fio) throws SQLException {
		return getContact(fio);
	}
	
	@Override
	public User authorizationPage(List<String> attr) {
		Session ses = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction transaction = ses.beginTransaction();
		user = (User) ses.createQuery("from User where login =:login AND password =:password AND enable = false")
				.setString("login", attr.get(0)).setParameter("password", attr.get(1)).uniqueResult();
		if (user != null && !user.getEnable()){
			user.setEnable(true);
			ses.update(user);
			LOGGER.debug("authorization user "+ this.user);
			LOGGER.info("authorization user "+ this.user);
			transaction.commit();
		}
		ses.close();
		return user;
	}
	
	@Override
	public Contact getContact(Integer id){
		Session ses  = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction transaction = ses.beginTransaction();
		Contact contact = (Contact) ses.get(Contact.class, id);
		if (contact != null){
			LOGGER.debug("get contact "+ contact);
			LOGGER.info("get contact "+ contact);
			return contact;
		}
		transaction.commit();
		return  null;
	}
}
