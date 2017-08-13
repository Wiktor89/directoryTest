package models;

/**
 * Модель пользователя
 */
public class User extends IdentifiedEntity implements Comparable<User> {
	
	private boolean act;
	private String login;
	private String password;
	
	public User() {
	}
	
	public boolean getAct() {
		return act;
	}
	
	public void setAct(boolean act) {
		this.act = act;
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
				"login='" + getLogin()+
				'}';
	}
	
	@Override
	public int compareTo(User o) {
		return login.compareToIgnoreCase(o.getLogin());
	}
}
