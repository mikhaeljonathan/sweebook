package main;

import javax.swing.JFrame;

import controller.EmployeeHandler;

public class HumanCapitalMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public HumanCapitalMainForm() {
	
		new EmployeeHandler().showManageEmployeeForm();
		
	}
	
}
