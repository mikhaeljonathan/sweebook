package view;

import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.EmployeeHandler;
import controller.RoleHandler;
import helper.SQLGetQuery;
import main.Main;
import model.Employee;
import model.Role;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

// This is a singleton class
public class ManageEmployeeForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ManageEmployeeForm instance = null;
	
	private EmployeeHandler eh;
	private JPanel listEmployeePanel;
	private JTextField nameTextField;
	private JTextField usernameTextField;
	private JTextField salaryTextField;

	private ManageEmployeeForm() {
		
		// Create UI
		setLayout(new GridLayout(1, 2, 0, 0));
		setResizable(false);
		setTitle("Manage Employee Form");
		setSize(800, 400);
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		eh = new EmployeeHandler();
		
		// Retrieve all employee
		List<Employee> le = eh.getAll();
		
		// List Employee (left)
		listEmployeePanel = new JPanel(new GridLayout(le.size() + 1, 0, 0, 10));
		JScrollPane listEmployeeScrollPane = new JScrollPane(listEmployeePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(listEmployeeScrollPane);
		
		// For each employee
		listEmployeePanel.add(new JLabel("Employees:"));
		int counter = 1;
		for (Employee employee : le) {
			
			listEmployeePanel.add(employeeListInfo(employee, counter));
			counter++;
			
		}
		
		// Right panel (list role and add new employee)
		JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		add(rightPanel);
		
		// Retrieve all role
		List<Role> lr = new RoleHandler().getAll();
		
		// List role
		JPanel listRolePanel = new JPanel(new GridLayout(lr.size() + 1, 0, 0, 10));
		JScrollPane listRoleScrollPane = new JScrollPane(listRolePanel);
		rightPanel.add(listRoleScrollPane);
		
		listRolePanel.add(new JLabel("Role List:"));
		
		// For each role
		for (Role role : lr) {
			
			JLabel roleLabel = new JLabel(role.getName());
			listRolePanel.add(roleLabel);
			
		}
		
		// Add employee panel
		JPanel addEmployeePanel = new JPanel(new GridLayout(6,2,0,10));
		rightPanel.add(addEmployeePanel);
		
		JLabel nameLabel = new JLabel("Name");
		addEmployeePanel.add(nameLabel);
		
		nameTextField = new JTextField();
		addEmployeePanel.add(nameTextField);
		
		JLabel usernameLabel = new JLabel("Username");
		addEmployeePanel.add(usernameLabel);
		
		usernameTextField = new JTextField();
		addEmployeePanel.add(usernameTextField);
		
		JLabel genderLabel = new JLabel("Gender");
		addEmployeePanel.add(genderLabel);
		String[] genders = {"Male", "Female"};
		JComboBox<String> genderComboBox = new JComboBox<String>(genders);
		addEmployeePanel.add(genderComboBox);
		
		JLabel salaryLabel = new JLabel("Salary");
		addEmployeePanel.add(salaryLabel);
		
		salaryTextField = new JTextField();
		addEmployeePanel.add(salaryTextField);
		
		JLabel roleLabel = new JLabel("Role");
		addEmployeePanel.add(roleLabel);
		String[] roles = {"Purchasing", "Admin", "Human Capital"};
		JComboBox<String> roleComboBox = new JComboBox<String>(roles);
		addEmployeePanel.add(roleComboBox);
		
		JButton addButton = new JButton("Add New Employee");
		addEmployeePanel.add(addButton);
		
		addButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				HashMap<String, String> inputs = new HashMap<String, String>();
				
				inputs.put("name", nameTextField.getText());
				inputs.put("username", usernameTextField.getText());
				inputs.put("gender", (String) genderComboBox.getSelectedItem());
				inputs.put("roleId", SQLGetQuery.getRoleIdFromRoleName((String) roleComboBox.getSelectedItem()));
				inputs.put("salary", salaryTextField.getText());
				
				if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Manager")) {
					
					if (eh.createWithActiveStatus(inputs) != null) {
						
						JOptionPane.showMessageDialog(null, "Employee successfully created");
						refreshEmployeeList();
						refreshInputPanel();
						
					}
					
				} else {
					
					if (eh.createWithPendingStatus(inputs) != null) {
						
						JOptionPane.showMessageDialog(null, "Employee successfully created\n"
								+ "It needs an approval from manager to be an active employee");
						refreshEmployeeList();
						refreshInputPanel();
						
					}
					
				}
				
			}
		
		});
	}
	
	public static ManageEmployeeForm getInstance() {
		
		if (instance == null) {
			instance = new ManageEmployeeForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		instance = null;
		
	}
	
	private JPanel employeeListInfo(Employee employee, int counter) {
		
		String id = employee.getId();
		String role = SQLGetQuery.getRoleFromUserId(employee.getId());
		String status = employee.getStatus();
		
		JPanel employeeInfo = new JPanel(new GridLayout(3, 2, 0, 0));
		
		JLabel idLabel = new JLabel(counter + ".");
		employeeInfo.add(idLabel);
		
		JLabel nameLabel = new JLabel(SQLGetQuery.getNameFromUserId(id));
		employeeInfo.add(nameLabel);
		
		JLabel roleLabel = new JLabel(role);
		employeeInfo.add(roleLabel);
		
		JButton fireButton = new JButton("Fire");
		employeeInfo.add(fireButton);
		
		JLabel statusLabel = new JLabel(status);
		if (status.equals("Active")) {
			
			statusLabel.setForeground(Color.GREEN);
			
		} else if (status.equals("Pending")) {
			
			statusLabel.setForeground(Color.RED);
			
		} else {
			
			statusLabel.setForeground(Color.GRAY);

		}
		employeeInfo.add(statusLabel);
		
		JButton acceptButton = new JButton("Accept");
		employeeInfo.add(acceptButton);
		
		fireButton.setVisible(false);
		acceptButton.setVisible(false);
		
		if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Manager")) {
			
			if (status.equals("Active")) fireButton.setVisible(true);
			if (status.equals("Pending")) acceptButton.setVisible(true);
			
		}
		
		fireButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to fire this employee?");
				
	            if(result == JOptionPane.YES_OPTION){
	            	
	            	if (eh.firedEmployee(employee.getId()) != null) {
	            		
	        			JOptionPane.showMessageDialog(null, "Employee is successfully fired");
	        			refreshEmployeeList();
	        			
	        		}
	            	
	            } 
	            
			}
			
		});
		
		acceptButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to accept this employee?");
				
	            if (result == JOptionPane.YES_OPTION){
	            	
	            	if (eh.acceptEmployee(employee.getId()) != null) {
	            		
	        			JOptionPane.showMessageDialog(null, "Employee is successfully accepted\n"
	        					+ "Now the employee's status is \"active\"");
	        			refreshEmployeeList();
	        			
	        		}
	            	
	            }
	            
			}
			
		});
		
		return employeeInfo;
		
	}
	
	private void refreshEmployeeList() {
		
		listEmployeePanel.setVisible(false);
		listEmployeePanel.removeAll();
		
		// Retrieve all employee
		List<Employee> le = eh.getAll();
		
		listEmployeePanel.setLayout(new GridLayout(le.size() + 1, 0, 0, 10));
		
		// For each employee
		listEmployeePanel.add(new JLabel("Employees:"));
		int counter = 1;
		for (Employee employee : le) {
			
			listEmployeePanel.add(employeeListInfo(employee, counter));
			counter++;
			
		}
		
		listEmployeePanel.setVisible(true);
		
	}
	
	private void refreshInputPanel() {
		
		nameTextField.setText("");
		usernameTextField.setText("");
		salaryTextField.setText("");
		
	}

}
