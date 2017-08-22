package models;

import java.util.Set;
import java.util.TreeSet;

/**
 * Модель пользователя
 */
public class User extends IdentifiedEntity implements Comparable<User> {
	
	private boolean enable;
	private String login;
	private String password;
	private Set<Role> role = new TreeSet<>();
	
	public User() {
	}
	
	public User(String login, int id, boolean enable) {
		super(id);
		this.login = login;
		this.enable = enable;
	}
	
	public User(int id) {
		super(id);
	}
	
	public Set<Role> getRole() {
		return role;
	}
	
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	public boolean getEnable() {
		return enable;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"login=" + getLogin()+ " role " + role+
				'}';
	}
	
	@Override
	public int compareTo(User o) {
		return login.compareToIgnoreCase(o.getLogin());
	}
}
