package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MemberHandler;
import controller.UserHandler;
import helper.SQLGetQuery;
import helper.Validation;

public class LoginForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public LoginForm() {
		
		// Create UI
		setTitle("Sweebook Library");
		setSize(500, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		KeyAdapter keyAdapter = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					handleLogin();
					
				}
				
			}
			
		};
		
		// Title
		JLabel title = new JLabel("Welcome to Sweebook Library", JLabel.CENTER);
		title.setPreferredSize(new Dimension(100, 50));
		title.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		// Login Panel
		JLabel usernameLbl = new JLabel("Username");
		usernameField = new JTextField();
		usernameField.addKeyListener(keyAdapter);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.addKeyListener(keyAdapter);
		
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
		loginBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				handleLogin();
				
			}
			
		});
		
		// For Create Membership Button
		createMembershipBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				new MemberHandler().showCreateMembershipForm();
				
			}
			
		});
		
	}
	
	private void handleLogin() {
		
		// Retrieve user name and password from text field
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		
		// Validate is user name and password match
		if (Validation.validateLogin(username, password)) {
			
			dispose();
			
			Main.user_id = new UserHandler().getByUsername(username).getId();
			String role = SQLGetQuery.getRoleFromUserId(Main.user_id);
			
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
			
		}
		
	}
	
}
