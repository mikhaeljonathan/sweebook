package main;


public class Main {

	// Store user name every time
	public static String user_id = null;
	
	public Main(){
		
		// Connect to MySQL
		MySQLAccess msa = MySQLAccess.getInstance();
		msa.emptyMethodToPreventWarning();
		
		// Go to the LoginForm
//		new LoginForm();
		new LoginForm();
		
	}
	
	public static void main(String[] args) {
		
		new Main();
		
	}

}
