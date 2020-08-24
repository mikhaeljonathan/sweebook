package helper;

import javax.swing.JOptionPane;

public class CheckInput {

	// Name constraint
	public static boolean validateName(String name) {
		
		if (name.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Name can't be empty");
			return false;
			
		}
		
		return true;
		
	}
	
	// Address constraint
	public static boolean validateAddress(String address) {
		
		if (address.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Address can't be empty");
			return false;
			
		}
		return true;
		
	}
	
	// User name constraint
	public static boolean validateUsername(String username) {
		
		// User name can't have space
		boolean containSpace = false;
		for (int i = 0; i < username.length(); i++) {
			
			if (username.charAt(i) == ' ') containSpace = true;
			
		}
		
		if (containSpace) {
			
			JOptionPane.showMessageDialog(null, "Usernaem can't contain spaces");
			return false;
			
		}
		
		// User name must be unique
		if (username.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Username can't be empty");
			return false;
			
		}
		
		return true;
		
	}
	
	// Password constraint
	public static boolean validatePassword(String password) {
		
		/* 
		 * Criteria:
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
		
		if (salary == null || salary.isEmpty()) {
			
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
