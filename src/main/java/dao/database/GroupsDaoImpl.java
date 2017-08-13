package dao.database;

import dao.GroupDao;
import models.*;
import utilits.ConnectingDataBase;
import views.ViewChangGroup;

import java.sql.*;
import java.sql.Connection;
import java.util.*;

/**
 *
 */
public class GroupsDaoImpl extends Observable implements GroupDao {
	
	private WrapperGroup wrapperGroup = new WrapperGroup();
	private List<Observer> observerList = new ArrayList<>();
	
	public GroupsDaoImpl() {
		ViewChangGroup viewChangGroup =ViewChangGroup.getViewChangGroup();
		observerList.add(viewChangGroup);
	}
	
	@Override
	public synchronized void addGroup(Entity entity) throws SQLException {
		Group group = (Group) entity;
		Connection connection = ConnectingDataBase.getConnection();
		try (PreparedStatement statement = connection.prepareStatement("SELECT add_group(?)")) {
			statement.setString(1,group.getName());
			statement.execute();
			notifyObserver();
		}finally {
			try {
				if(!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public synchronized boolean removeGroup(String name) throws SQLException {
		boolean result = false;
		Connection connection = ConnectingDataBase.getConnection();
		if (existGroup(name.trim())) {
			try (PreparedStatement statement = connection.prepareStatement("SELECT delete_group(?)")) {
				statement.setString(1, name);
				statement.execute();
				result = true;
				notifyObserver();
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
			return result;
		}
	
	
	@Override
	public synchronized boolean updateGroup(List<String> attGroup) throws SQLException {
		boolean result = false;
		Connection connection = ConnectingDataBase.getConnection();
		if (existGroup(attGroup.get(0))) {
			try (PreparedStatement statement = connection.prepareStatement("SELECT update_group(?,?)")) {
				statement.setString(2, attGroup.get(0));
				statement.setString(1, attGroup.get(1));
				statement.execute();
				result = true;
				notifyObserver();
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
		return result;
	}
	
	public void notifyObserver (){
		for (Observer observer : observerList){
			observer.update(this,getGroups());
		}
	}
	
	@Override
	public boolean existGroup(String name) throws SQLException {
		return this.wrapperGroup.existGroup(name);
	}
	
	@Override
	public Set<Group> getGroups() {
		return this.wrapperGroup.getGroups();
	}
	
	@Override
	public Set<Contact> getContactsGroup(String name) throws SQLException {
		return this.wrapperGroup.getContactsGroup(name);
	}
	
	@Override
	public Integer numberUsers() throws SQLException {
		return this.wrapperGroup.analyticInf("SELECT number_users()");
	}
	
	@Override
	public Integer numberContacts(String name) throws SQLException {
	 return this.wrapperGroup.analyticInf("SELECT number_contacts('"+name+"')");
		
	}
	
	@Override
	public Integer quantityGroupsUser(String name) throws SQLException {
		return this.wrapperGroup.analyticInf("SELECT quantity_groups_user('"+name+"')");
	}
	
	@Override
	public Integer averageNumberContactsGroups() throws SQLException {
		return this.wrapperGroup.analyticInf("SELECT average_number_contacts_groups()");
	}
	
	@Override
	public Integer averageNumberContactsUser() throws SQLException {
		return this.wrapperGroup.analyticInf("SELECT average_number_contacts_user()");
	}
	
	@Override
	public Set<User> userWithContactsMin_10() throws SQLException {
		return this.wrapperGroup.userWithContactsMin_10();
	}
	
	
	
	 class WrapperGroup {
		
		private Contact contact;
		
		public Set<Group> getGroups (){
			Set<Group> groups = new TreeSet<>();
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_groups()")) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					Group group = new Group();
					group.setId(resultSet.getInt("id"));
					group.setName(resultSet.getString("title").trim());
					groups.add(group);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(!connection.isClosed()){
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return groups;
		}
		
		public boolean existGroup (String name) throws SQLException {
			boolean result = false;
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_groups()")) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					String title = resultSet.getString("title");
					if (title.trim().equalsIgnoreCase(name)){
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
		
		public Set<Contact> getContactsGroup(String name) throws SQLException {
			Set<Contact> contacts = new TreeSet<>();
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_contacts_group(?)")) {
				statement.setString(1,name);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					contact = new Contact();
					contact.setId(resultSet.getInt("id"));
					contact.setFio(resultSet.getString("fio").trim());
					contact.setEmail(resultSet.getString("email").trim());
					contact.setPhone(resultSet.getString("phone").trim());
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
		
		public Integer analyticInf (String nameFunction) throws SQLException {
			Integer result = 0;
			Connection connection = ConnectingDataBase.getConnection();
			try (PreparedStatement statement = connection.prepareStatement(nameFunction)) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					result = resultSet.getInt(1);
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
		
		 public Set<User> userWithContactsMin_10() throws SQLException {
			 Set<User> users = new TreeSet<>();
			 Connection connection = ConnectingDataBase.getConnection();
			 try (PreparedStatement statement = connection.prepareStatement("SELECT user_with_contacts_min_10()")) {
				 ResultSet resultSet = statement.executeQuery();
				 while (resultSet.next()){
				 	 User user = new User();
					 user.setLogin(resultSet.getString(1).trim());
					 users.add(user);
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
			 return users;
		 }
		
	}
}

