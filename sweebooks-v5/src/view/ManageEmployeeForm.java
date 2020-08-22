package view;

import java.util.ArrayList;
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

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ManageEmployeeForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ManageEmployeeForm() {
		
		setResizable(false);
		setTitle("Manage Employee Form");
		setSize(800, 400); //ukuran gui
		setLocation(160, 10); //start location frame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //biar kalo di close langsung mati
		setVisible(true);
		setClosable(true);
		setLayout(new GridLayout(1, 2, 0, 0)); //layout 1 row, 2 kolom
		
		EmployeeHandler eh = new EmployeeHandler();
		
	//--------- PANEL KIRI -------------------------------
	// Bagian List Employee
		List<Employee> le = new ArrayList<Employee>();
		le = eh.getAll();
		// TODO: show le here
		JPanel listEmployeePanel = new JPanel();
		listEmployeePanel.setLayout(new GridLayout(10,0,0,10)); //row sebanyak jumlah employee
		JScrollPane listEmployeeScrollPane = new JScrollPane(listEmployeePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(listEmployeeScrollPane);
		for (Employee employee : le) {
			String employeeRole = SQLGetQuery.getRoleFromUserId(employee.getId());
//			System.out.println(employeeRole);
			listEmployeePanel.add(employeeListInfo(employeeRole, employee.getId(),employee.getId(),employee.getStatus()));
			//harusnya employeeListInfo(JPanel, Role, ID, NAMA, Status ) , Role buat nentuin button fire & accept visible atau invisible
		}
	//----------------------------------------------------
		
	//-------- PANEL KANAN --------------------------------
		JPanel kananPanel = new JPanel();
		kananPanel.setLayout(new GridLayout(2,1,0,0)); // panel yang paling besar di kanan, row 2 karna ada 2 bagian
		add(kananPanel);
		
	// Bagian List Role
		List<Role> lr = new ArrayList<Role>();
		lr = new RoleHandler().getAll();
		// TODO: show lr here
		JPanel listRolePanel = new JPanel(); // panel buat list role
		listRolePanel.setLayout(new GridLayout(10,0,0,10)); //row sebanyak role, 1 column, 0 hgap, 10 vertical gap (jarak kotak ke kotak lain)
		JScrollPane listRoleScrollPane = new JScrollPane(listRolePanel);
		kananPanel.add(listRoleScrollPane);
		
		for (Role role : lr) {
			JLabel roleLabel = new JLabel(role.getId()); // label yg isi text nya role.getId
			listRolePanel.add(roleLabel);
		}
		
	// nanti ada textfield name, username, gender, salary, role(ourchasing, admin, human capital)
		JPanel addEmployeePanel = new JPanel();
		addEmployeePanel.setLayout(new GridLayout(6,2,0,10)); //7 row, 2 kolom
		kananPanel.add(addEmployeePanel);
		
		JLabel nameLabel = new JLabel("Name");
		addEmployeePanel.add(nameLabel);
		JTextField nameTextField = new JTextField();
		addEmployeePanel.add(nameTextField);
		
		JLabel usernameLabel = new JLabel("Username");
		addEmployeePanel.add(usernameLabel);
		JTextField usernameTextField = new JTextField();
		addEmployeePanel.add(usernameTextField);
		
		JLabel genderLabel = new JLabel("Gender");
		addEmployeePanel.add(genderLabel);
		String[] genders = {"Male", "Female"};
		JComboBox<String> genderComboBox = new JComboBox<String>(genders);
		addEmployeePanel.add(genderComboBox);
		
		JLabel salaryLabel = new JLabel("Salary");
		addEmployeePanel.add(salaryLabel);
		JTextField salaryTextField = new JTextField();
		addEmployeePanel.add(salaryTextField);
		
		JLabel roleLabel = new JLabel("Role");
		addEmployeePanel.add(roleLabel);
		String[] roles = {"Purchasing", "Admin", "Human Capital"};
		JComboBox<String> roleComboBox = new JComboBox<String>(roles);
		addEmployeePanel.add(roleComboBox);
		
		JButton addButton = new JButton("Add New Employee");
		addEmployeePanel.add(addButton);
		
	// TODO: kalau tombol add new employee diteken execute this:
		addButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				HashMap<String, String> inputs = new HashMap<String, String>();
				inputs.put("name", nameTextField.getText());
				inputs.put("username", usernameTextField.getText());
				inputs.put("gender", (String)genderComboBox.getSelectedItem());
				inputs.put("roleId", salaryTextField.getText());
				inputs.put("salary", (String)roleComboBox.getSelectedItem());
				
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
		
		});
	}
	
	private JPanel employeeListInfo(String role, String id, String name, String status) {
		JPanel employeeInfo = new JPanel();
		employeeInfo.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel idLabel = new JLabel(id);
		employeeInfo.add(idLabel);
		
		JButton fireButton = new JButton("Fire");
		employeeInfo.add(fireButton);
		
		JLabel nameLabel = new JLabel(name);
		employeeInfo.add(nameLabel);
		
		JButton acceptButton = new JButton("Accept");
		employeeInfo.add(acceptButton);
		
		JLabel statusLabel = new JLabel(status);
		employeeInfo.add(statusLabel);
		
		if (role == "Manager") {
			fireButton.setVisible(true);
			acceptButton.setVisible(true);
		} else {
			fireButton.setVisible(false);
			acceptButton.setVisible(false);
		}
		
		EmployeeHandler eh = new EmployeeHandler();
		Employee em = new Employee();
		// kalau role nya skrg human capital tombol fire dan accept ga muncul
		
		// TODO: kalau salah satu employee diteken dan tekan fire employee akan execute this:
		// keluar confirmation dialog dulu ya apakah setuju
		fireButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null,"Are you sure ?");
	            if(result == JOptionPane.YES_OPTION){
	            	if (eh.firedEmployee(em.getId()) != null) {
	        			JOptionPane.showMessageDialog(null, "Employee is successfully fired");
	        		} else {
	        			JOptionPane.showMessageDialog(null, "Employee is not fired");
	        		}
	            } else if (result == JOptionPane.NO_OPTION){
	            	JOptionPane.showMessageDialog(null, "Employee is not fired");
	            }
			}
			
		});
		
		// TODO: kalau salah satu employee diteken dan tekan accept employee request akan exeucte this:
		// keluar confirmation dialog dul uya apakah setuju
		acceptButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null,"Are you sure ?");
				
	            if(result == JOptionPane.YES_OPTION){
	            	if (eh.acceptEmployee(em.getId()) != null) {
	        			JOptionPane.showMessageDialog(null, "Employee is successfully accepted\n"
	        					+ "Now the employee's status is \"active\"");
	        		} else {
	        			JOptionPane.showMessageDialog(null, "Employee is not Accepted");
	        		}
	            } else if (result == JOptionPane.NO_OPTION){
	            	JOptionPane.showMessageDialog(null, "Employee is not Accepted");
	            }
			}
			
		});
		
		return employeeInfo;
	}

}

