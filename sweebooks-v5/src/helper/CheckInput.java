package helper;

import javax.swing.JOptionPane;

//import jdk.nashorn.internal.scripts.JO;
import main.MySQLAccess;

public class CheckInput {

	// Name constraint
	public static boolean validateName(String name) {
		
		if (name.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Name can't be empty");
			return false;
			
		}
		
		return true;
		
	}
	
	// Gender constraint
	public static boolean validateGender(String gender) {
		
		if (gender == "Male") return true;
		if (gender == "Female") return true;
		return false;
		
	}
	
	// Address constraint
	public static boolean validateAddress(String address) {
		
		if (address.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "address can't be empty");
			return false;
			
		}
		return true;
		
	}
	
	// User name constraint
	public static boolean validateUsername(String username) {
		
		if (username.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Username can't be empty");
			return false;
			
		}
		
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
			
			return false;
			
		}
		
	}
	
	// Password constraint
	public static boolean validatePassword(String password) {
		
		/* Criteria:
		 * - minimum length 8
		 * - contains alphanumeric
		 * - contains upper and lower case
		 */
		
		if (password.length() < 8) {
			
			JOptionPane.showMessageDialog(null, "Password have to contain at least 8 characters");
			return false;
			
		}
		
		boolean numeric = false;
		boolean alphabet = false;
		boolean upperCase = false;
		boolean lowerCase = false;
		
		for (int i = 0; i < password.length(); i++) {
			
			if (Character.isDigit(password.charAt(i))) numeric = true;
			if (Character.isLetter(password.charAt(i))) alphabet = true;
			if (Character.isUpperCase(password.charAt(i))) upperCase = true;
			if (Character.isLowerCase(password.charAt(i))) lowerCase = true;
			
		}
		
		if (!numeric) {
			
			JOptionPane.showMessageDialog(null, "Password has to contain at least 1 number");
			return false;
			
		}

		if (!alphabet) {
			
			JOptionPane.showMessageDialog(null, "Password has to contain at least 1 letter");
			return false;
			
		}
		
		if (!upperCase) {
			
			JOptionPane.showMessageDialog(null, "Password has to contain at least 1 upper case letter");
			return false;
			
		}

		if (!lowerCase) {
	
			JOptionPane.showMessageDialog(null, "Password has to contain at least 1 lower case letter");
			return false;
			
		}
		
		return true;
		
	}
	
	public static boolean validateSalary(String salary) {
		
		if (salary.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Salary can't be empty");
			return false;
			
		}
		
		try {
			
			if (Integer.parseInt(salary) > 1000) {
				
				return true;
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Salary must be above 1000");
				return false;
				
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Salary must be numeric");
			return false;
			
		}
		
	}
	
}
