package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Helper.SQLGetQuery;
import Helper.Validation;
import view.CreateMembershipForm;

public class LoginForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextField usernameField;
	private JPasswordField passwordField;

	public LoginForm() {
		
		// Create UI
		setTitle("Welcome to Sweebook Library");
		setSize(500, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Title
		JLabel title = new JLabel("Welcome to Sweebook Library", JLabel.CENTER);
		title.setPreferredSize(new Dimension(100, 50));
		title.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		// Login Panel
		JLabel usernameLbl = new JLabel("Username");
		JTextField usernameField = new JTextField();
		JLabel passwordLbl = new JLabel("Password");
		JPasswordField passwordField = new JPasswordField();
		
		JPanel fieldPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		fieldPanel.add(usernameLbl);
		fieldPanel.add(usernameField);
		fieldPanel.add(passwordLbl);
		fieldPanel.add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		
		JPanel loginPanel = new JPanel(new BorderLayout(5, 5));
		loginPanel.add(fieldPanel, BorderLayout.NORTH);
		loginPanel.add(loginBtn, BorderLayout.SOUTH);
		
		// Create Membership
		JLabel orLbl = new JLabel("--- or ---", JLabel.CENTER);
		JButton createMembershipBtn = new JButton("Create Membership");
		
		JPanel createMembershipPanel = new JPanel(new BorderLayout(5, 5));
		createMembershipPanel.add(orLbl, BorderLayout.NORTH);
		createMembershipPanel.add(createMembershipBtn, BorderLayout.SOUTH);
		
		// Main Panel
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(loginPanel, BorderLayout.CENTER);
		mainPanel.add(createMembershipPanel, BorderLayout.SOUTH);
		mainPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		add(mainPanel);
		setVisible(true);
		
		// Back end
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Retrieve user name and password from text field
				String username = usernameField.getText();
				char[] passwordTemp = passwordField.getPassword();
				String password = new String(passwordTemp);
				
				// Validate is user name and password match
				if (Validation.validateLogin(username, password)) {
					
					dispose();
					
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
		createMembershipBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new CreateMembershipForm();
				
			}
			
		});
		
	}
}
