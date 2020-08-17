package helper;

import java.util.HashMap;

import javax.swing.JOptionPane;

import main.Main;
import main.MySQLAccess;
import model.Book;
import model.CartStorage;

public class Validation {

	public static String dummyTimestamp = "1990-01-01 12:00:00";
	public static long twoWeeksInMillisecond = 1209600000;
	
	public static boolean validateLogin(String username, String password) {
		
		String validateLogin = "SELECT username FROm users " + 
				"WHERE username = '%s' AND password = '%s'";
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
		
			return false;
			
		}
		
	}
	
	public static boolean validateMembership(HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String name = inputs.get("name");
		String gender = inputs.get("gender");
		String address = inputs.get("address");
		String username = inputs.get("username");
		String password = inputs.get("password");
		
		// Check the constraint
		
		return (CheckInput.validateName(name) &&
				CheckInput.validateGender(gender) &&
				CheckInput.validateAddress(address) &&
				CheckInput.validateUsername(username) &&
				CheckInput.validatePassword(password));
		
	}
	
	public static boolean isUserEverBorrowThisBook(Book book) {
		
		String isThereAnyData = "SELECT books.title FROM books" + 
				"INNER JOIN borrow_items " + 
				"ON borrow_items.book_id = books.id " + 
				"INNER JOIN borrows " + 
				"ON borrows.id = borrow_items.borrow_id " + 
				"INNER JOIN members " + 
				"ON members.user_id = borrows.member_id " + 
				"WHERE members.user_id = '%s' AND books.id = '%s'";
		isThereAnyData = String.format(isThereAnyData, Main.user_id, book.getId());
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(isThereAnyData);
			
			String title = "";
			
			while (MySQLAccess.rs.next()) {
				
				title = MySQLAccess.rs.getString("books.title");
				
			}
			
			if (title.isEmpty()) {
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		} catch (Exception e) {
			
			return false;
			
		}
		
	}
	
	public static boolean isUserCanBorrow() {
		
		CartStorage cs = CartStorage.getInstance();
		int cartSize = cs.getCart().size();
		
		if (cartSize + SQLGetQuery.countBooksBorrowedByUser() <= 10) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	public static boolean isGenreTypeExist(String type) {
		
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
			
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public static boolean validateIsbn(String isbn) {
		
		if (isbn.length() < 10 || isbn.length() > 13) {
			
			JOptionPane.showMessageDialog(null, "ISBN must be between 10 and 13 characters long");
			return false;
			
		}
		
		boolean isAllNum = true;
		for (int i = 0; i < isbn.length(); i++) {
			
			if (!Character.isDigit(isbn.charAt(i))) isAllNum = false;
			
		}
		
		if (!isAllNum) {
			
			JOptionPane.showMessageDialog(null, "ISBN must be numeric");
			return false;
			
		}
		
		return true;
		
	}
	
	public static boolean validateBookInput(String name, String quantity) {
		
		if (name.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Book name can't be empty");
			return false;
			
		}
		
		try {
			
			int ret = Integer.parseInt(quantity);
			
			if (ret > 0) {
				
				return true;
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Book quantity must above zero");
				return false;
				
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Quantity must be numeric");
			return false;
			
		}
		
	}
}
