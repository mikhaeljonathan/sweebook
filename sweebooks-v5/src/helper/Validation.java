package helper;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import controller.BorrowBookHandler;
import main.MySQLAccess;
import model.Book;

public class Validation {

	public static String dummyTimestamp = "1990-01-01 12:00:00";
	public static long twoWeeksInMillisecond = 1209600000;
	public static long oneDayInMillisecond = 86400000;
	
	public static boolean validateLogin(String username, String password) {
		
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
	
	public static boolean validateMembership(HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String name = inputs.get("name");
		String address = inputs.get("address");
		String username = inputs.get("username");
		String password = inputs.get("password");
		
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
	
	public static boolean validateEmployee(HashMap<String, String> inputs) {
		
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
		
		// Check user name exists or not in DAO
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
	
}
