package model;

public class User {

	private String id;
	private String roleId;
	private String name;
	private String username;
	private String password;
	private String gender;
	
	public User() {
		
	}
	
	public User(String id, String roleId, String name, String username, String password, String gender) {
		
		this.id = id;
		this.roleId = roleId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.gender = gender;
		
	}
	
	public User insert() {
		
		// TODO
		// insert to database
		// if success return user, else return null
		return this;
		
	}	
	
	public String getByUsername(String username) {
		return "tes";
	}
	
}
