package helper;

import javax.swing.JOptionPane;

import main.MySQLAccess;

public class SQLGetQuery {

	public static int countBooksBorrowedByUser(String userId) {
		
		// Retrieve count of borrowed books from corresponding user from DAO
		String isUserBorrowMoreThan10Books = "SELECT COUNT(members.user_id) FROM members " + 
				"INNER JOIN borrows " + 
				"ON borrows.member_id = members.user_id " + 
				"INNER JOIN borrow_items " + 
				"ON borrow_items.borrow_id = borrows.id " + 
				"WHERE members.user_id = '%s' AND borrow_items.return_timestamp = '1990-01-01 12:00:00'";
		isUserBorrowMoreThan10Books = String.format(isUserBorrowMoreThan10Books, userId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(isUserBorrowMoreThan10Books);
			
			int count = 0;
			
			while (MySQLAccess.rs.next()) {
				
				count = MySQLAccess.rs.getInt("COUNT(members.user_id)");
				
			}
			
			return count;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return -1;
			
		}
		
	}
	
	public static String getRoleFromUserId(String userId) {
		
		// Get role name from user Id
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
	
	public static String getReturnTimestampFromIdAndBookId(String id, String bookId) {
		
		// Get return time stamp of borrow item by borrow id and book id from DAO 
		String getReturnTimestamp = "SELECT return_timestamp from borrow_items " + 
				"WHERE borrow_id = '%s' AND book_id = '%s'";
		getReturnTimestamp = String.format(getReturnTimestamp, id, bookId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getReturnTimestamp);
			
			String returnTimestamp = "";
			
			while (MySQLAccess.rs.next()) {
				
				returnTimestamp = MySQLAccess.rs.getString("return_timestamp");
				
			}
			
			return returnTimestamp;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public static String getTypeFromGenreId(String genreId) {
		
		// Get genre type by genre id from DAO
		String getType = "SELECT type FROM genres " + 
				"WHERE id = '%s'";
		getType = String.format(getType, genreId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getType);
			
			String type = "";
			
			while (MySQLAccess.rs.next()) {
				
				type = MySQLAccess.rs.getString("type");
				
			}
			
			return type;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public static String getUsernameFromId(String id) {
		
		// Retrieve user name from users by userId from DAO
		String getUsername = "SELECT username FROM users " + 
				"WHERE id = '%s'";
		getUsername = String.format(getUsername, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getUsername);
			
			String username = "";
			
			while (MySQLAccess.rs.next()) {
				
				username = MySQLAccess.rs.getString("username");
				
			}
			
			return username;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public static String getBookNameFromId(String id) {
		
		// Retrieve book name by bookId from DAO
		String getBookName = "SELECT title FROM books " + 
				"WHERE id = '%s'";
		getBookName = String.format(getBookName, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getBookName);
			
			String title = "";
			
			while (MySQLAccess.rs.next()) {
				
				title = MySQLAccess.rs.getString("title");
				
			}
			
			return title;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public static String getNameFromUserId(String id) {
		
		// Retrieve name from users by userId from DAO
		String getName = "SELECT name FROM users " + 
				"WHERE id = '%s'";
		getName = String.format(getName, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getName);
			
			String name = "";
			
			while (MySQLAccess.rs.next()) {
				
				name = MySQLAccess.rs.getString("name");
				
			}
			
			return name;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public static String getRoleIdFromRoleName(String roleName) {
		
		// Retrieve roleId by roleName from DAO
		String getRoleId = "SELECT id FROM roles " + 
				"WHERE name = '%s'";
		getRoleId = String.format(getRoleId, roleName);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getRoleId);
			
			String id = "";
			
			while (MySQLAccess.rs.next()) {
				
				id = MySQLAccess.rs.getString("id");
				
			}
			
			return id;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public static String getTypeFromBookId(String bookId) {
		
		// Retrieve genre name by bookId from DAO
		String getType = "SELECT genres.type FROM genres " + 
				"INNER JOIN books " +
				"ON books.genre_id = genres.id " +
				"WHERE books.id = '%s'";
		getType = String.format(getType, bookId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getType);
			
			String type = "";
			
			while (MySQLAccess.rs.next()) {
				
				type = MySQLAccess.rs.getString("genres.type");
				
			}
			
			return type;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
}
