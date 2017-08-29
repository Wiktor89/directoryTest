package net.directory.models;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "role")
public class Role extends IdentifiedEntity {
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<User> userList;
	
	public Role(String title) {
		this.title = title;
	}
	
	public Role(int id) {
		super(id);
	}
	
	public Role() {
	}
	
	public Set<User> getUserList() {
		return userList;
	}
	
	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Role{" +
				"title='" + title + '\'' +
				'}';
	}
}
