package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.MySQLAccess;

public class Employee {

	private String id;
	private int salary;
	private String status;
	
	public Employee() {
		
	}
	
	public Employee(String id, int salary, String status) {
		
		this.id = id;
		this.salary = salary;
		this.status = status;
		
	}

	public List<Employee> all(){
		
		List<Employee> le = new ArrayList<Employee>();
		
		// Retrieve all role data from DAO
		String findAllEmployee = "SELECT * FROM employees";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findAllEmployee);
			
			while (MySQLAccess.rs.next()) {
				
				// Add Employee object into List<Employee>
				le.add(new Employee(MySQLAccess.rs.getString("user_id"), 
						MySQLAccess.rs.getInt("salary"),
						MySQLAccess.rs.getString("status")));
				
			}
			
			return le;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public Employee find(String id) {
		return new Employee();
	}
	
	public Employee insert() {
		
		// Insert employee into DAO
		String insertToEmployee = "INSERT INTO employees "
				+ "VALUE('%s', %d, '%s')";
		insertToEmployee = String.format(insertToEmployee, id, salary, status);
		
		try {
			
			MySQLAccess.stmt.execute(insertToEmployee);
			return this;
			
		} catch (Exception e) {
			
			// Fail to insert to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public Employee update() {
		return new Employee();
	}

}
