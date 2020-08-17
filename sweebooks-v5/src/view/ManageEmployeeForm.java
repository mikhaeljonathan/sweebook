package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import controller.EmployeeHandler;
import controller.RoleHandler;
import model.Employee;
import model.Role;

public class ManageEmployeeForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ManageEmployeeForm() {
		
		List<Role> lr = new ArrayList<Role>();
		lr = new RoleHandler().getAll();
		// TODO: show lr here
		
		List<Employee> le = new ArrayList<Employee>();
		le = new EmployeeHandler().getAll();
		// TODO: show le here
		
	}

}
