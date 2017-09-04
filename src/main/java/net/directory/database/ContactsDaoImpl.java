package net.directory.database;

import net.directory.dao.ContactDao;
import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;
import net.directory.utilits.ConnectingDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class ContactsDaoImpl extends Observable implements ContactDao {
	
	private static volatile ContactsDaoImpl contactsDao;
	private User user;
	private Set<Contact> contacts;
	
	private ContactsDaoImpl() {
//		ViewChangContact viewChangContact = ViewChangContact.getViewChangContact();
//		observerList.add(viewChangContact);
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
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT add_contact(?,?,?,?)")) {
			statement.setString(1,contact.getFio());
			statement.setString(2,contact.getPhone());
			statement.setString(3,contact.getEmail());
			statement.setInt(4,this.user.getId());
			statement.execute();
		}
	}
	
	@Override
	public synchronized boolean updateContact(List<String> attContact) throws SQLException {
		boolean result;
		Contact oldContact = getContact(Integer.parseInt(attContact.get(0)));
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT update_contact(?,?,?,?,?)")) {
			statement.setInt(1, Integer.parseInt(attContact.get(0)));
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
	public synchronized boolean removeContact(Integer id) throws SQLException {
		boolean result;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT delete_contact(?)")) {
			statement.setInt(1,id);
			result = statement.execute();
		}
		return result;
	}
	
	@Override
	
	public synchronized boolean appGroupContact(List<String> attContact) throws SQLException {
		boolean result;
		Contact contact = getContact(attContact.get(0));
		Group group;
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_group_name(?)")) {
			statement.setString(1,attContact.get(1));
			group = ContactMapper.getGroup(statement.executeQuery());
		}
			try (PreparedStatement statement = connection.prepareStatement("SELECT app_contact_group(?,?)")) {
				statement.setInt(1,contact.getId());
				statement.setInt(2,group.getId());
				statement.execute();
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
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_group_name(?)")){
			statement.setString(1,attr.get(1));
			group = ContactMapper.getGroup(statement.executeQuery());
		}
			try (PreparedStatement statement = connection.prepareStatement("SELECT delete_group_contact(?,?)")) {
				statement.setInt(1,contact.getId());
				statement.setInt(2,group.getId());
				statement.execute();
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
	public Set<Contact> getContacts() throws SQLException {
		contacts = new TreeSet<>();
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contacts()")) {
			for (Contact contact : ContactMapper.getContacts(statement.executeQuery())){
				if (contact.getUser().getId() == user.getId()){
					try (PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM get_groups_contact(?)")) {
						statement1.setInt(1,contact.getId());
						contact.setGroup(ContactMapper.getGroups(statement1.executeQuery()));
						contacts.add(contact);
					}
				}
			}
		}
		return contacts;
	}
	
	@Override
	public boolean existContact(String name) throws SQLException {
		boolean result;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contact(?)")) {
			statement.setString(1, name);
			result = ContactMapper.existContact(statement.executeQuery(),name);
		}
		return result;
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
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_user(?,?)")) {
			statement.setString(1, attr.get(0));
			statement.setString(2, attr.get(1));
			user = ContactMapper.getUser(statement.executeQuery());
			if (user != null && user.getEnable()) {
				PreparedStatement statement1 = connection.prepareStatement("SELECT FROM update_users(?,?)");
				statement1.setInt(1, user.getId());
				statement1.setBoolean(2, user.getEnable());
				statement1.execute();
			}
			
		}
		return user;
	}
	
	@Override
	public Contact getContact(Integer id) throws SQLException {
		Contact contact;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contact(?)")) {
			statement.setInt(1, id);
			contact = ContactMapper.getContact(statement.executeQuery());
			
		}
		return contact;
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
		
		private static boolean existContact(ResultSet resultSet,String name) throws SQLException {
			boolean result = false;
				while (resultSet.next()) {
					if (resultSet.getString("fio").trim().equalsIgnoreCase(name)) {
						result = true;
						break;
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
				contact.setUser(new User(resultSet.getInt("user_id")));
				contacts.add(contact);
			}
			return contacts;
		}
		
		private static Group getGroup(ResultSet resultSet) throws SQLException {
			Group group = new Group();
			while (resultSet.next()) {
				group.setName(resultSet.getString("title").trim());
				group.setId(resultSet.getInt("id"));
				}
			return group;
			
		}
		
		public static User getUser(ResultSet resultSet) throws SQLException {
			while (resultSet.next()) {
				boolean enable = resultSet.getBoolean("enable");
				if (!enable){
					String login = resultSet.getString("login").trim();
					return new User(login,resultSet.getInt("id"),!enable);
				}
			}
			return null;
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
