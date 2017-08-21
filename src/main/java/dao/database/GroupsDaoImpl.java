package dao.database;

import dao.GroupDao;
import models.Contact;
import models.Entity;
import models.Group;
import models.User;
import utilits.ConnectingDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class GroupsDaoImpl extends Observable implements GroupDao {
	
	private static volatile GroupsDaoImpl groupsDao;
	private List<Observer> observerList = new ArrayList<>();
	
	private GroupsDaoImpl() {
//		ViewChangGroup viewChangGroup = ViewChangGroup.getViewChangGroup();
//		observerList.add(viewChangGroup);
	}
	
	public static GroupsDaoImpl getGroupsDaoImpl(){
		if (groupsDao == null){
			synchronized (GroupsDaoImpl.class){
				if (groupsDao == null){
					groupsDao = new GroupsDaoImpl();
				}
			}
		}
		return groupsDao;
	}
	
	@Override
	public synchronized void addGroup(Entity entity) throws SQLException {
		Group group = (Group) entity;
		try (Connection connection = ConnectingDataBase.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT add_group(?)")) {
			statement.setString(1,group.getName());
			statement.execute();
		}
		
	}
	
	@Override
	public synchronized boolean removeGroup(Integer id) throws SQLException {
		boolean result = false;
		if (existGroup(id)) {
			try (Connection connection = ConnectingDataBase.getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT delete_group(?)")) {
				statement.setInt(1, id);
				statement.execute();
				result = true;
			}
		}
			return result;
		}
	
	@Override
	public synchronized boolean updateGroup(Integer id, String name) throws SQLException {
		boolean result = false;
		if (existGroup(id)) {
			try (Connection connection = ConnectingDataBase.getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT update_group(?,?)")) {
				statement.setInt(2, id);
				statement.setString(1, name);
				statement.execute();
				result = true;
			}
		}
		return result;
	}
	
	public void notifyObserver (){
		for (Observer observer : observerList){
			observer.update(this,getGroups());
		}
	}
	
	@Override
	public boolean existGroup(Integer id) throws SQLException {
		boolean result;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_group(?)")) {
			statement.setInt(1, id);
			result = GroupMapper.existGroup(statement.executeQuery());
		}
		return result;
	}
	
	@Override
	public Set<Group> getGroups() {
		Set<Group> groups = null;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_groups()")) {
			groups = GroupMapper.getGroups(statement.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	@Override
	public Set<Contact> getContactsGroup(String name) throws SQLException {
		Set<Contact> contacts;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contacts_group(?)")) {
			statement.setString(1,name);
			contacts = GroupMapper.getContactsGroup(statement.executeQuery());
		}
		return contacts;
	}
	
	@Override
	public Integer numberUsers() throws SQLException {
		Integer numberUsers;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT number_users()")) {
			numberUsers = GroupMapper.analyticInf(statement.executeQuery());
		}
		return numberUsers;
	}
	
	@Override
	public Integer numberContacts(String name) throws SQLException {
		Integer numberUsers;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT number_contacts(?)")) {
			 statement.setString(1,name);
			 numberUsers = GroupMapper.analyticInf(statement.executeQuery());
		}
		return numberUsers;
		
	}
	
	@Override
	public Integer quantityGroupsUser(String name) throws SQLException {
		Integer numberUsers;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT quantity_groups_user(?)")) {
			 statement.setString(1,name);
			 numberUsers = GroupMapper.analyticInf(statement.executeQuery());
		}
		return numberUsers;
	}
	
	@Override
	public Integer averageNumberContactsGroups() throws SQLException {
		Integer numberUsers;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT average_number_contacts_groups()")) {
			 numberUsers = GroupMapper.analyticInf(statement.executeQuery());
		}
		return numberUsers;
	}
	
	@Override
	public Integer averageNumberContactsUser() throws SQLException {
		Integer numberUsers;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT average_number_contacts_user()")) {
			 numberUsers = GroupMapper.analyticInf(statement.executeQuery());
		}
		return numberUsers;
	}
	
	@Override
	public Set<User> userWithContactsMin_10() throws SQLException {
		Set<User> users;
		try (Connection connection = ConnectingDataBase.getConnection();
		     PreparedStatement statement = connection.prepareStatement("SELECT user_with_contacts_min_10()")) {
			users = GroupMapper.userWithContactsMin_10(statement.executeQuery());
		}
		return users;
	}
	
	 static class GroupMapper {
		
		
		private static Set<Group> getGroups (ResultSet resultSet) throws SQLException {
			Set<Group> groups = new TreeSet<>();
				while (resultSet.next()){
					Group group = new Group();
					group.setId(resultSet.getInt("id"));
					group.setName(resultSet.getString("title").trim());
					groups.add(group);
				}
			
			return groups;
		}
		
		private static boolean existGroup (ResultSet resultSet) throws SQLException {
			boolean result = false;
				while (resultSet.next()){
						result = true;
				}
			return result;
		}
		
		private static Set<Contact> getContactsGroup(ResultSet resultSet) throws SQLException {
				Set<Contact> contacts = new TreeSet<>();
				while (resultSet.next()){
					Contact contact = new Contact();
					contact.setId(resultSet.getInt("id"));
					contact.setFio(resultSet.getString("fio").trim());
					contact.setEmail(resultSet.getString("email").trim());
					contact.setPhone(resultSet.getString("phone").trim());
					if (contact != null && contact.getFio().trim().length() > 0){
						contacts.add(contact);
					}
				}
			return contacts;
		}
		
		private static Integer analyticInf (ResultSet resultSet) throws SQLException {
			Integer result = 0;
				while (resultSet.next()){
					result = resultSet.getInt(1);
				}
			return result;
		}
		
		 private static Set<User> userWithContactsMin_10(ResultSet resultSet) throws SQLException {
			Set<User> users = new TreeSet<>();
				 while (resultSet.next()){
				 	 User user = new User();
					 user.setLogin(resultSet.getString(1).trim());
					 users.add(user);
				 }
			 return users;
		 }
	}
}

