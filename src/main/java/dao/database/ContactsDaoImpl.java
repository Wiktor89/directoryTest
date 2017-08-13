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
	
	private User user;
	private WrapperContact wrapperContact = new WrapperContact();
	private List<Observer> observerList = new ArrayList<>();
	
	public ContactsDaoImpl() {
		ViewChangContact viewChangContact = ViewChangContact.getViewChangContact();
		observerList.add(viewChangContact);
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
		Contact contact = wrapperContact.getContact(attContact.get(0));
		Group group = wrapperContact.getGroup(attContact.get(1));
		Connection connection = ConnectingDataBase.getConnection();
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
		Contact contact = wrapperContact.getContact(attr.get(0));
		Group group = wrapperContact.getGroup(attr.get(1));
		Connection connection = ConnectingDataBase.getConnection();
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
		return this.wrapperContact.getContacts();
	}
	
	@Override
	public boolean existContact(String name) throws SQLException {
		return wrapperContact.existContact(name);
	}
	
	@Override
	public Contact getContact(String fio) throws SQLException {
		return this.wrapperContact.getContact(fio);
	}
	
	@Override
	public Contact searchName(String fio) throws SQLException {
		return wrapperContact.getContact(fio);
	}
	
	@Override
	public User authorizationPage(List<String> attr) throws SQLException {
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_user(?,?)")) {
			statement.setString(1,attr.get(0).trim());
			statement.setString(2,attr.get(1).trim());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				String login = resultSet.getString("login").trim();
				if (login.equalsIgnoreCase(attr.get(0).trim())
						&& resultSet.getString("password").trim().equalsIgnoreCase(attr.get(1).trim())){
					this.user = new User();
					this.user.setAct(true);
					this.user.setLogin(login);
					this.user.setId(resultSet.getInt("id"));
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
		return this.user;
	}
	
	public void notifyObserver() throws SQLException {
		for (Observer observer : observerList){
			observer.update(this,getContacts());
		}
	}
	
	
	class WrapperContact {
		
		private Group group;
		private Contact contact;
		
		private Contact getContact(String fio) throws SQLException {
			Connection connection = ConnectingDataBase.getConnection();
			if (existContact(fio)) {
				try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contact(?)")) {
					statement.setString(1, fio.trim());
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						contact = new Contact();
						contact.setId(resultSet.getInt("id"));
						contact.setFio(resultSet.getString("fio").trim());
						contact.setPhone(resultSet.getString("phone").trim());
						contact.setEmail(resultSet.getString("email").trim());
						CallableStatement statement1 = connection.prepareCall("SELECT * FROM get_groups_contact(?)");
						statement1.setString(1, contact.getFio().trim());
						ResultSet resultSet1 = statement1.executeQuery();
						Set<Group> groups = new TreeSet<>();
						while (resultSet1.next()) {
							groups.add(new Group(resultSet1.getString("title").trim()));
						}
						contact.setGroup(groups);
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
			}
			return contact;
		}
		
		private boolean existContact(String name) throws SQLException {
			boolean result = false;
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contact(?)")) {
				statement.setString(1,name);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					if (resultSet.getString("fio").trim().equalsIgnoreCase(name)){
						result = true;
						break;
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
			return result;
		}
		
		private Set<Contact> getContacts() throws SQLException {
			Set<Contact> contacts = new TreeSet<>();
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contacts()")) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					Contact contact = new Contact();
					contact.setId(resultSet.getInt("id"));
					contact.setFio(resultSet.getString("fio").trim());
					contact.setPhone(resultSet.getString("phone").trim());
					contact.setEmail(resultSet.getString("email").trim());
					CallableStatement statement1 = connection.prepareCall("SELECT * FROM get_groups_contact(?)");
					statement1.setString(1,contact.getFio());
					ResultSet resultSet1 = statement1.executeQuery();
					Set<Group> groups = new TreeSet<>();
						while (resultSet1.next()){
							groups.add(new Group(resultSet1.getString("title").trim()));
						}
					contact.setGroup(groups);
					contacts.add(contact);
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
		
		private Group getGroup(String name) throws SQLException {
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_group(?)")) {
				statement.setString(1,name);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					group = new Group();
					group.setName(resultSet.getString("title"));
					group.setId(resultSet.getInt("id"));
				}
			}
			return group;
			
		}
	
	}
}
