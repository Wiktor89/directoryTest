package dao.database;

import dao.ContactDao;
import models.Contact;
import models.Entity;
import models.Group;
import models.User;
import utilits.ConnectingDataBase;
import views.ViewChangContact;

import java.sql.*;
import java.util.*;

/**
 *
 */
public class ContactsDaoImpl extends Observable implements ContactDao {
	
	private static volatile ContactsDaoImpl contactsDao;
	private List<Observer> observerList = new ArrayList<>();
	private User user;
	private Set<Contact> contacts;
	
	private ContactsDaoImpl() {
		ViewChangContact viewChangContact = ViewChangContact.getViewChangContact();
		observerList.add(viewChangContact);
	}
	
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
	
	@Override
	public synchronized void  addContact(Entity entity) throws SQLException {
		Contact contact = (Contact) entity;
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT add_contact(?,?,?,?)")) {
			statement.setString(1,contact.getFio());
			statement.setString(2,contact.getPhone());
			statement.setString(3,contact.getEmail());
			statement.setInt(4,this.user.getId());
			statement.execute();
			notifyObserver();
		}finally {
			try {
				if (!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized boolean updateContact(List<String> attContact) throws SQLException {
		boolean result;
		Contact oldContact = getContact(attContact.get(0));
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT update_contact(?,?,?,?,?)")) {
			statement.setInt(1,oldContact.getId());
			statement.setString(2,attContact.get(1));
			if (!(attContact.get(2).equalsIgnoreCase("nop"))){
				statement.setString(3,attContact.get(2));
			}else {
				statement.setString(3,oldContact.getPhone());
			}
			if (!(attContact.get(3).equalsIgnoreCase("noe"))){
				statement.setString(4,attContact.get(3));
			}else {
				statement.setString(4,oldContact.getEmail());
			}
			statement.setInt(5,this.user.getId());
			statement.execute();
			notifyObserver();
			result = true;
		}finally {
			try {
				if (!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public synchronized boolean removeContact(String fio) throws SQLException {
		boolean result;
		Integer id = getContact(fio).getId();
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT delete_contact(?)")) {
			statement.setInt(1,id);
			result = statement.execute();
			notifyObserver();
		}finally {
			try {
				if (!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	
	public synchronized boolean appGroupContact(List<String> attContact) throws SQLException {
		boolean result;
		Contact contact = getContact(attContact.get(0));
		Group group;
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_group(?)")) {
			statement.setString(1,attContact.get(1));
			group = ContactMapper.getGroup(statement.executeQuery());
		}
			try (PreparedStatement statement = connection.prepareStatement("SELECT app_contact_group(?,?)")) {
				statement.setInt(1,contact.getId());
				statement.setInt(2,group.getId());
				statement.execute();
				notifyObserver();
				result = true;
			}finally {
				try {
					if (!connection.isClosed()){
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return result;
	}
	
	@Override
	public synchronized boolean removeGroupContact(List<String> attr) throws SQLException {
		boolean result;
		Contact contact = getContact(attr.get(0));
		Group group;
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_group(?)")){
			statement.setString(1,attr.get(1));
			group = ContactMapper.getGroup(statement.executeQuery());
		}
			try (PreparedStatement statement = connection.prepareStatement("SELECT delete_group_contact(?,?)")) {
				statement.setInt(1,contact.getId());
				statement.setInt(2,group.getId());
				statement.execute();
				result = true;
				notifyObserver();
			}finally {
				try {
					if (!connection.isClosed()){
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return result;
	}
	
	
	@Override
	public Set<Contact> getContacts() throws SQLException {
		contacts = new TreeSet<>();
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contacts()")) {
			contacts = ContactMapper.getContacts(statement.executeQuery());
			for (Contact contact : contacts){
				try (PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM get_groups_contact(?)")) {
					statement1.setInt(1,contact.getId());
					contact.setGroup(ContactMapper.getGroups(statement1.executeQuery()));
				}
				
			}
		}finally {
			try {
				if (!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contacts;
	}
	
	@Override
	public boolean existContact(String name) throws SQLException {
		return ContactMapper.existContact(name);
	}
	
	@Override
	public Contact getContact(String fio) throws SQLException {
		
		Contact contact;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contact(?)")) {
				statement.setString(1, fio.trim());
				contact = ContactMapper.getContact(statement.executeQuery());
			PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM get_groups_contact(?)");
			statement1.setInt(1,contact.getId());
			contact.setGroup(ContactMapper.getGroups(statement1.executeQuery()));
			}
		return contact;
	}
	
	@Override
	public Contact searchName(String fio) throws SQLException {
		return getContact(fio);
	}
	
	@Override
	public User authorizationPage(List<String> attr) throws SQLException {
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_user(?,?)")) {
			statement.setString(1,attr.get(0).trim());
			statement.setString(2,attr.get(1).trim());
			this.user = ContactMapper.getUser(attr,statement.executeQuery());
		}finally {
			try {
				if (!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return this.user;
	}
	
	public void notifyObserver() throws SQLException {
		for (Observer observer : observerList){
			observer.update(this,getContacts());
		}
	}
	
	
	static class ContactMapper {
		
		
		private static Contact getContact(ResultSet resultSet) throws SQLException {
			Contact contact = new Contact();
			while (resultSet.next()) {
				contact.setId(resultSet.getInt("id"));
				contact.setFio(resultSet.getString("fio").trim());
				contact.setPhone(resultSet.getString("phone").trim());
				contact.setEmail(resultSet.getString("email").trim());
			}
			return contact;
		}
		
		private static boolean existContact(String name) throws SQLException {
			boolean result = false;
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contact(?)")) {
				statement.setString(1, name);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					if (resultSet.getString("fio").trim().equalsIgnoreCase(name)) {
						result = true;
						break;
					}
				}
			} finally {
				try {
					if (!connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		private static Set<Contact> getContacts(ResultSet resultSet) throws SQLException {
			Contact contact;
			Set<Contact> contacts = new TreeSet<>();
			while (resultSet.next()) {
				contact = new Contact();
				contact.setId(resultSet.getInt("id"));
				contact.setFio(resultSet.getString("fio").trim());
				contact.setPhone(resultSet.getString("phone").trim());
				contact.setEmail(resultSet.getString("email").trim());
				contacts.add(contact);
			}
			return contacts;
		}
		
		private static Group getGroup(ResultSet resultSet) throws SQLException {
			Group group = new Group();
			while (resultSet.next()) {
				group.setName(resultSet.getString("title"));
				group.setId(resultSet.getInt("id"));
				}
			return group;
			
		}
		
		public static User getUser(List<String> attr, ResultSet resultSet) throws SQLException {
			User user = new User();
			while (resultSet.next()) {
				String login = resultSet.getString("login").trim();
				if (login.equalsIgnoreCase(attr.get(0).trim())
						&& resultSet.getString("password").trim().equalsIgnoreCase(attr.get(1).trim())) {
					user.setAct(true);
					user.setLogin(login);
					user.setId(resultSet.getInt("id"));
				}
			}
			return user;
		}
		
		public static Set<Group> getGroups (ResultSet resultSet) throws SQLException {
			Set<Group> groups = new TreeSet<>() ;
			while (resultSet.next()){
				groups.add(new Group(resultSet.getString("title").trim()));
			}
			return groups;
		}
	}
}
