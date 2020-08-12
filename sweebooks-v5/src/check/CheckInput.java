package check;

public class CheckInput {

	// Name constraint
	public static boolean validateName(String name) {
		
		if (name.isEmpty()) return false;
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
		
		if (address.isEmpty()) return false;
		return true;
		
	}
	
	// User name constraint
	public static boolean validateUsername(String username) {
		
		if (username.isEmpty()) return false;
		// TODO: if (existInDB(username)) return false;
		return true;
		
	}
	
	// Password constraint
	public static boolean validatePassword(String password) {
		
		/* Criteria:
		 * - minimum length 8
		 * - contains alphanumeric
		 * - contains upper and lower case
		 */
		
		if (password.length() < 8) return false;
		
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
		
		return (numeric && alphabet && upperCase && lowerCase);
		
	}
	
}
