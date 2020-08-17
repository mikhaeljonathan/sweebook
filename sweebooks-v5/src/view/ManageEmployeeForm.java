package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controller.EmployeeHandler;
import controller.RoleHandler;
import helper.SQLGetQuery;
import main.Main;
import model.Employee;
import model.Role;

public class ManageEmployeeForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ManageEmployeeForm() {
		
		EmployeeHandler eh = new EmployeeHandler();
		
		List<Role> lr = new ArrayList<Role>();
		lr = new RoleHandler().getAll();
		// TODO: show lr here
		
		List<Employee> le = new ArrayList<Employee>();
		le = eh.getAll();
		// TODO: show le here
		
		
		// nanti ada textfield name, username, gender, salary, role(ourchasing, admin, human capital)
		// TODO: kalau tombol add new employee diteken execute this:
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("name", "namanya di sini nanti");
		inputs.put("username", "username nya di sini nanti");
		inputs.put("gender", "male/female");
		inputs.put("roleId", "rolenya (id) di sini nanti");
		inputs.put("salary", "berapa saja tapi integer");
		
		if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Manager")) {
			
			if (eh.createWithActiveStatus(inputs) != null) {
				
				JOptionPane.showMessageDialog(null, "Employee successfully created");
				
			}
			
		} else {
			
			if (eh.createWithPendingStatus(inputs) != null) {
				
				JOptionPane.showMessageDialog(null, "Employee successfully created\n"
						+ "It needs an approval from manager to be an active employee");
				
			}
			
		}
		
	}

}

