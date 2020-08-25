package controller;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import helper.Validation;
import model.Employee;
import model.User;
import view.ManageEmployeeForm;

public class EmployeeHandler {

	public EmployeeHandler() {
		
	}
	
	public JInternalFrame showManageEmployeeForm() {
		
		return ManageEmployeeForm.getInstance();
		
	}
	
	public void unshowManageEmployeeForm() {
		
		ManageEmployeeForm.getInstance().destroy();
		
	}
	
	public List<Employee> getAll(){
		
		return new Employee().all();
		
	}
	
	public Employee findById(String id) {
		
		return new Employee().find(id);
		
	}
	
	public Employee insert(HashMap<String, String> inputs) {
		
		String id = inputs.get("id");
		int salary = Integer.parseInt(inputs.get("salary"));
		String status = inputs.get("status");
		
		return new Employee(id, salary, status).insert();
		
	}
	
	public Employee update(HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String userId = inputs.get("userId");
		int salary = Integer.parseInt(inputs.get("salary"));
		String status = inputs.get("Status");
		
		return new Employee(userId, salary, status).update();
		
	}
	
	public Employee createWithPendingStatus(HashMap<String, String> inputs) {
		
		inputs.put("status", "Pending");
		
		if (Validation.validateEmployeeInput(inputs)) {
			
			String password = generatePassword(inputs.get("username"));
			inputs.put("password", password);
			inputs.put("id", UUID.randomUUID().toString());
			
			if (createUserEntity(inputs) == null) {
				
				return null;
				
			}
			
			JOptionPane.showMessageDialog(null, "Current employee's password is: " + password + "\nPlease remember it!");
			
			return createEmployeeEntity(inputs);
			
			
		} else {
			
			return null;
			
		}
		
	}
	
	public Employee createWithActiveStatus(HashMap<String, String> inputs) {
		
		inputs.put("status", "Active");
		
		if (Validation.validateEmployeeInput(inputs)) {
			
			String password = generatePassword(inputs.get("username"));
			inputs.put("password", password);
			inputs.put("id", UUID.randomUUID().toString());
			
			if (createUserEntity(inputs) == null) {
				
				return null;
				
			}
			
			JOptionPane.showMessageDialog(null, "Current employee's password is: " + password + "\nPlease remember it!");
			
			return createEmployeeEntity(inputs);
			
			
		} else {
			
			return null;
			
		}
		
	}
	
	public Employee firedEmployee(String id) {
		
		Employee e = new Employee().find(id);
		if (e.getStatus().equals("Fired")) {
			
			JOptionPane.showMessageDialog(null, "Employee is already fired");
			return null;
			
		} else {
			
			e.setStatus("Fired");
			return e.update();
			
		}
		
	}
	
	public Employee acceptEmployee(String id) {
		
		Employee e = new Employee().find(id);
		if (e.getStatus().equals("Active")) {
			
			JOptionPane.showMessageDialog(null, "Employee is already accepted");
			return null;
			
		} else {
			
			e.setStatus("Active");
			return e.update();
			
		}
		
	}
	
	private String generatePassword(String username) {
		
		String temp = username;
		temp = temp + username.length();
		
		String ret = "";
		for (int i = temp.length() - 1; i >= 0; i--) {
			
			ret += temp.charAt(i);
			
		}
		
		ret = "Sw3b00k" + ret;
		
		return ret;
		
	}
	
	private User createUserEntity(HashMap<String, String> inputs) {
		
		return new UserHandler().insert(inputs);
		
	}
	
	private Employee createEmployeeEntity(HashMap<String, String> inputs) {
		
		return new EmployeeHandler().insert(inputs);
		
	}
	
}
