package Helper;

import java.util.HashMap;

import main.Main;
import main.MySQLAccess;
import model.Book;
import model.CartStorage;

public class Validation {

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
		if (!CheckInput.validateName(name)) return false;
		if (!CheckInput.validateGender(gender)) return false;
		if (!CheckInput.validateAddress(address)) return false;
		if (!CheckInput.validateUsername(username)) return false;
		if (!CheckInput.validatePassword(password)) return false;
		
		return true;
		
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
	
}
