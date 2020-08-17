package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.MySQLAccess;

public class Genre {

	private String id;
	private String type;
	
	public Genre() {
		
	}
	
	public Genre(String id, String type) {
		
		this.id = id;
		this.type = type;
		
	}
	
	public List<Genre> all(){
		
		List<Genre> lg = new ArrayList<Genre>();
		
		// Retrieve all book data from DAO
		String findAllGenre = "SELECT * FROM genres";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findAllGenre);
			
			while (MySQLAccess.rs.next()) {
				
				// Add Genre object into List<Genre>
				lg.add(new Genre(MySQLAccess.rs.getString("id"), 
						MySQLAccess.rs.getString("type")));
				
			}
			
			return lg;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
			
	}
	
	public Genre insert() {
		return new Genre();
	}
	
	public Genre getByType(String type) {
		return new Genre();
	}
	
	// Getter and setter
	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}

	public String getType() {
		
		return type;
		
	}

	public void setType(String type) {
		
		this.type = type;
		
	}
	
}
