package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.MySQLAccess;

public class Role {

	private String id;
	private String name;
	
	public Role() {
		
	}
	
	public Role(String id, String name) {
		
		this.id = id;
		this.name = name;
		
	}

	public List<Role> all(){
		
		List<Role> lr = new ArrayList<Role>();
		
		// Retrieve all role data from DAO
		String findAllRole = "SELECT * FROM roles";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findAllRole);
			
			while (MySQLAccess.rs.next()) {
				
				// Add Role object into List<Role>
				lr.add(new Role(MySQLAccess.rs.getString("id"), 
						MySQLAccess.rs.getString("name")));
				
			}
			
			return lr;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}

	public Role getByName(String name) {
		
		// Get role entity by its name from DAO
		String getRoleObjectByName = "SELECT id FROM roles " + 
				"WHERE name = '%s'";
		getRoleObjectByName = String.format(getRoleObjectByName, name);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getRoleObjectByName);
			
			while(MySQLAccess.rs.next()) {
				
				this.id = MySQLAccess.rs.getString("id");
				this.name = name;
				
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

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}
	
}

