package Helper;

import main.Main;
import main.MySQLAccess;

public class SQLGetQuery {

	public static String getRoleFromUsername(String username) {
		
		String retrieveRoleFromUsername = "SELECT roles.name FROM roles "
				+ "INNER JOIN users "
				+ "ON users.role_id = roles.id "
				+ "WHERE users.username = '%s'";
		retrieveRoleFromUsername = String.format(retrieveRoleFromUsername, username);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveRoleFromUsername);
			
			String role = "";
			
			while (MySQLAccess.rs.next()) {
				
				role = MySQLAccess.rs.getString("roles.name");
				
			}
			
			return role;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	public static String getIdFromUsername(String username) {
		
		String retrieveIdFromUsername = "SELECT id FROM users " + 
				"WHERE username = '%s'";
		retrieveIdFromUsername = String.format(retrieveIdFromUsername, username);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveIdFromUsername);
			
			String id = "";
			
			while (MySQLAccess.rs.next()) {
				
				id = MySQLAccess.rs.getString("id");
				
			}
			
			return id;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	public static int countBooksBorrowedByUser() {
		
		String isUserBorrowMoreThan10Books = "SELECT COUNT(members.user_id) FROM members " + 
				"INNER JOIN borrows " + 
				"ON borrows.member_id = members.user_id " + 
				"INNER JOIN borrow_items " + 
				"ON borrow_items.borrow_id = borrows.id " + 
				"WHERE members.user_id = '%s'";
		isUserBorrowMoreThan10Books = String.format(isUserBorrowMoreThan10Books, Main.user_id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(isUserBorrowMoreThan10Books);
			
			int count = 0;
			
			while (MySQLAccess.rs.next()) {
				
				count = MySQLAccess.rs.getInt("COUNT(members.user_id)");
				
			}
			
			return count;
			
		} catch (Exception e) {
			
			return -1;
			
		}
		
	}
	
	public static String getRoleFromUserId(String userId) {
		
		String getRole = "SELECT roles.name FROM roles " + 
				"INNER JOIN users " + 
				"ON users.role_id = roles.id " + 
				"WHERE users.id = '%s'";
		getRole = String.format(getRole, userId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getRole);
			
			String role = "";
			
			while (MySQLAccess.rs.next()) {
				
				role = MySQLAccess.rs.getString("roles.name");
				
			}
			
			return role;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
}
