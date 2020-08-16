package main;

import view.ViewBookForm;

public class Main {

	// Store user name every time
	public static String user_id = null;
	
	public Main(){
		
		// Connect to MySQL
		MySQLAccess msa = MySQLAccess.getInstance();
		msa.emptyMethodToPreventWarning();
		
		// Go to the LoginForm
		new MembershipMainForm();
		
	}
	
	public static void main(String[] args) {
		
		new Main();
		
	}

}
