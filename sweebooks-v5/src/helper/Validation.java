package helper;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import controller.BorrowBookHandler;
import controller.UserHandler;
import main.MySQLAccess;
import model.Book;

public class Validation {

	public static String dummyTimestamp = "1990-01-01 12:00:00";
	public static long twoWeeksInMillisecond = 1209600000;
	public static long oneDayInMillisecond = 86400000;
	
	public static boolean validateLogin(String username, String password) {
		
		return (validateUsernameAndPassword(username, password) && validateEmployee(username));
		
	}
	
	public static boolean validateUsernameAndPassword(String username, String password) {
		
		// Check if user name and password match in DAO
		String validateLogin = "SELECT username FROM users " + 
				"WHERE username = '%s' AND password = SHA('%s')";
		validateLogin = String.format(validateLogin, username, password);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(validateLogin);
			
			String temp = "";
			
			while (MySQLAccess.rs.next()) {
				
				temp = MySQLAccess.rs.getString("username");
				
			}
			
			if (temp.isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "Username and password don't match");
				return false;
				
			} else {
				
				return true;
				
			}
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public static boolean validateEmployee(String username) {
		
		String id = new UserHandler().getByUsername(username).getId();
		
		// Check if employee is eligible from DAO
		String retrieveUserId = "SELECT status FROM employees "
				+ "WHERE user_id = '%s'";
		retrieveUserId = String.format(retrieveUserId, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveUserId);
			
			String status = "";
			while(MySQLAccess.rs.next()) {
				
				status = MySQLAccess.rs.getString("status");
				
			}
			
			if (status.equals("Pending")) {
				
				JOptionPane.showMessageDialog(null, "You haven't have permission to access");
				return false;
				
			}
			
			if (status.equals("Fired")) {
				
				JOptionPane.showMessageDialog(null, "You have been fired!");
				return false;
				
			}
			
			return true;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public static boolean validateMembership(HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String name = inputs.get("name");
		String address = inputs.get("address");
		String username = inputs.get("username");
		String password = inputs.get("password");
		String confirmPassword = inputs.get("confirmPassword");
		
		if (!password.equals(confirmPassword)) {
			
			JOptionPane.showMessageDialog(null, "Password doesn't match");
			return false;
			
		}
		
		// Check the constraint
		return (CheckInput.validateName(name) &&
				CheckInput.validateAddress(address) &&
				CheckInput.validateUsername(username) &&
				Validation.isUsernameExists(username) &&
				CheckInput.validatePassword(password));
		
	}
	
	public static boolean isGenreTypeExist(String type) {
		
		// Retrieve any genre type from DAO
		String retrieveType = "SELECT type FROM genres "
				+ "WHERE type = '%s'";
		retrieveType = String.format(retrieveType, type);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveType);
			
			String typeRetrieved = "";
			while(MySQLAccess.rs.next()) {
				
				typeRetrieved = MySQLAccess.rs.getString("type");
				
			}
			
			if (typeRetrieved.isEmpty()) {
				
				return false;
				
			} else {
				
				return true;
				
			}
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public static boolean validateEmployeeInput(HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String name = inputs.get("name");
		String username = inputs.get("username");
		String salary = inputs.get("salary");
		
		// Check the constraint
		return (CheckInput.validateName(name) &&
				CheckInput.validateUsername(username) &&
				Validation.isUsernameExists(username) &&
				CheckInput.validateSalary(salary));
		
	}
	
	public static boolean isBookIsInTheCart(Book book) {
		
		List<Book> lb = new BorrowBookHandler().getCart();
		
		for (Book b : lb) {
			
			if (b.getId().equals(book.getId())) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public static boolean isUsernameExists(String username) {
		
		// Check if user name exists or not in DAO
		String retrieveUsername = "SELECT username FROM users "
				+ "WHERE username = '%s'";
		retrieveUsername = String.format(retrieveUsername, username);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveUsername);
			
			String usernameRetrieved = "";
			while(MySQLAccess.rs.next()) {
				
				usernameRetrieved = MySQLAccess.rs.getString("username");
				
			}
			
			if (usernameRetrieved.isEmpty()) {
				
				return true;
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Username already exists");
				return false;
				
			}
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public static boolean isBookInUnreturnedBorrowItem(String bookId) {
		
		// Check if the book is in borrow items and hasn't returned in DAO
		String retrieveBook = "SELECT books.id FROM books " + 
				"INNER JOIN borrow_items " + 
				"ON borrow_items.book_id = books.id " + 
				"WHERE borrow_items.return_timestamp = '1990-01-01 12:00:00' AND books.id = '%s'";
		retrieveBook = String.format(retrieveBook, bookId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveBook);
			
			String id = "";
			while(MySQLAccess.rs.next()) {
				
				id = MySQLAccess.rs.getString("books.id");
				
			}
			
			if (id.isEmpty()) {
				
				return false;
				
			} 
			
			return true;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
}
