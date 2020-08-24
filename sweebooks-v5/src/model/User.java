package model;

import javax.swing.JOptionPane;

import main.MySQLAccess;

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
		
		// Insert user into DAO
		String insertToUser = "INSERT INTO users "
				+ "VALUE('%s', '%s', '%s', '%s', SHA('%s'), '%s')";
		insertToUser = String.format(insertToUser, id, roleId, name, username, password, gender);
		
		try {
			
			MySQLAccess.stmt.execute(insertToUser);
			return this;
			
		} catch (Exception e) {
			
			// Fail to insert to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}	
	
	public User getByUsername(String username) {
		
		// Retrieve user object by user name from DAO
		String retrieveIdFromUsername = "SELECT * FROM users " + 
				"WHERE username = '%s'";
		retrieveIdFromUsername = String.format(retrieveIdFromUsername, username);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveIdFromUsername);
			
			while (MySQLAccess.rs.next()) {
				
				this.id = MySQLAccess.rs.getString("id");
				this.roleId = MySQLAccess.rs.getString("role_id");
				this.name = MySQLAccess.rs.getString("name");
				this.username = MySQLAccess.rs.getString("username");
				this.password = MySQLAccess.rs.getString("password");
				this.gender = MySQLAccess.rs.getString("gender");
				
			}
			
			return this;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}

	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}

	public String getRoleId() {
		
		return roleId;
		
	}

	public void setRoleId(String roleId) {
		
		this.roleId = roleId;
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public String getUsername() {
		
		return username;
		
	}

	public void setUsername(String username) {
		
		this.username = username;
		
	}

	public String getPassword() {
		
		return password;
		
	}

	public void setPassword(String password) {
		
		this.password = password;
		
	}

	public String getGender() {
		
		return gender;
		
	}

	public void setGender(String gender) {
		
		this.gender = gender;
		
	}
	
}
