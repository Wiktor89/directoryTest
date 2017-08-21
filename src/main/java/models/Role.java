package models;

/**
 *
 */
public class Role extends IdentifiedEntity {
	
	private String title;
	private String description;
	
	public Role(String title) {
		this.title = title;
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
}
