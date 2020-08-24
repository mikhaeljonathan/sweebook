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
		
		// Retrieve all employee by user id from DAO
		String findEmployeeById = "SELECT * FROM employees "
				+ "WHERE user_id = '%s'";
		findEmployeeById = String.format(findEmployeeById, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findEmployeeById);
			
			while (MySQLAccess.rs.next()) {
				
				this.id = MySQLAccess.rs.getString("user_id");
				this.salary = Integer.parseInt(MySQLAccess.rs.getString("salary"));
				this.status = MySQLAccess.rs.getString("status");
				
			}
			
			return this;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
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
		
		// Update employee in the DAO
		String updateEmployee = "UPDATE employees " + 
				"SET user_id = '%s', salary = %d, status = '%s' " + 
				"WHERE user_id = '%s'";
		updateEmployee = String.format(updateEmployee, id, salary, status, id);
		
		try {
			
			MySQLAccess.stmt.execute(updateEmployee);
			return this;
			
		} catch (Exception e) {
			
			// Fail to update to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}

	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}

	public int getSalary() {
		
		return salary;
		
	}

	public void setSalary(int salary) {
		
		this.salary = salary;
		
	}

	public String getStatus() {
		
		return status;
		
	}

	public void setStatus(String status) {
		
		this.status = status;
		
	}
	
}
