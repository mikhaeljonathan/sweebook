package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Helper.SQLGetQuery;
import Helper.Validation;
import view.CreateMembershipForm;

public class LoginForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JFrame frame;

	public LoginForm() {
		
		// Create UI
		frame = new JFrame();
		frame.setTitle("Login or Create Member");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(123, 94, 248, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(49, 97, 64, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(49, 128, 64, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(119, 172, 181, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCreate = new JButton("Create Membership");
		btnCreate.setBounds(119, 209, 181, 23);
		frame.getContentPane().add(btnCreate);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 125, 248, 20);
		frame.getContentPane().add(passwordField);
		
		frame.setVisible(true);
		
		// For Login Button
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Retrieve user name and password from text field
				String username = usernameField.getText();
				char[] passwordTemp = passwordField.getPassword();
				String password = new String(passwordTemp);
				
				// Validate is user name and password match
				if (Validation.validateLogin(username, password)) {
					
					frame.dispose();
					
					String role = SQLGetQuery.getRoleFromUsername(username);
					Main.user_id = SQLGetQuery.getIdFromUsername(username);
					
					if (role.equals("Administrator")) {
						
						new AdministratorMainForm();
						
					} else if (role.equals("Human Capital")) {
						
						new HumanCapitalMainForm();
						
					} else if (role.equals("Manager")) {
						
						new ManagerMainForm();
						
					} else if (role.equals("Membership")) {
						
						new MembershipMainForm();
						
					} else { // Purchasing
						
						new PurchasingMainForm();
						
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Username and password don't match");
					
				}
				
			}
		});
		
		// For Create Membership Button
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new CreateMembershipForm();
				
			}
			
		});
		
	}
}
