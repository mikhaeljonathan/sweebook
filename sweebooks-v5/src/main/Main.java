package main;

public class Main {

	public static String user_id = null;
	
	public Main(){
		
		MySQLAccess msa = MySQLAccess.getInstance();
		new LoginForm();
		
	}
	
	public static void main(String[] args) {
		
		new Main();
		
	}

}
