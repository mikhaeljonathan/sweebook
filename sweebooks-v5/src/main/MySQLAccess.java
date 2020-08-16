package main;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

// This is a singleton class
public class MySQLAccess {
	
	private static MySQLAccess instance = null;
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/sweebook";
	public static final String USER = "root";
	public static final String PASS = "";
	
	public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    
    private MySQLAccess() {
    	
    	try {
    		
            Class.forName(JDBC_DRIVER); // Register the driver
            
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS); // Connect to database
            
            stmt = (Statement) conn.createStatement(); // Create statement object
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }
    	
    }
    
    public static MySQLAccess getInstance() {
    	
    	if (instance == null) {
    		instance = new MySQLAccess();
    	}
    	
    	return instance;
    	
    }
    
    public void emptyMethodToPreventWarning() {
    	
    }

}
