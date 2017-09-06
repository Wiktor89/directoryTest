package net.directory.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Модель пользователя
 */
@javax.persistence.Entity
@Table(name = "users")
public class User extends IdentifiedEntity implements Comparable<User> {
	
	@Column(name = "enable")
	private boolean enable;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Contact> listContacts;

	public User() {
	}
	
	public User(String login, int id, boolean enable) {
		super(id);
		this.login = login;
		this.enable = enable;
	}
	
	public User(String login, String password, Role role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Set<Contact> getListContacts() {
		return listContacts;
	}
	
	public void setListContacts(Set<Contact> listContacts) {
		this.listContacts = listContacts;
	}
	
	public User(int id) {
		super(id);
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
				"login=" + getLogin()+ " " + role+
				'}';
	}
	
	@Override
	public int compareTo(User o) {
		return login.compareToIgnoreCase(o.getLogin());
	}
}
