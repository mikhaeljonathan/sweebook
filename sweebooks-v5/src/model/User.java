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
				+ "VALUE('%s', '%s', '%s', '%s', '%s', '%s')";
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
	
	public String getByUsername(String username) {
		return "tes";
	}
	
}
