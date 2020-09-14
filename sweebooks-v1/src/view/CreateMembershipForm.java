package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MemberHandler;
import controller.UserHandler;
import main.LoginForm;
import main.Main;
import main.MembershipMainForm;

public class CreateMembershipForm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nameField;
	private JTextField usernameField;
	private JComboBox<String> genreComboBox;
	private JTextArea addressField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	
	public CreateMembershipForm() {
		
		// Create UI
		setTitle("Create Membership Form");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		KeyAdapter keyAdapter = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					handleCreateMembershipInput();
					
				}
				
			}
			
		};
		
		// Title Label
		JLabel titleLbl = new JLabel("Create Membership Form", JLabel.CENTER);
		titleLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		// CreateMembership Panel
		JLabel nameLbl = new JLabel("Name");
		nameField = new JTextField();
		nameField.addKeyListener(keyAdapter);
		
		JLabel genderLbl = new JLabel("Gender");
		genreComboBox = new JComboBox<>(new String[] {
			"Male", "Female"
		});		
		
		JLabel addressLbl = new JLabel("Address");
		addressField = new JTextArea();
		JScrollPane addressSp = new JScrollPane(addressField);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameField = new JTextField();
		usernameField.addKeyListener(keyAdapter);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.addKeyListener(keyAdapter);
		
		JLabel confirmPasswordLbl = new JLabel("Confirm password");
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.addKeyListener(keyAdapter);
		
		JPanel createMembershipPanel = new JPanel(new GridLayout(6, 2, 10, 10));
		createMembershipPanel.add(nameLbl);
		createMembershipPanel.add(nameField);
		createMembershipPanel.add(genderLbl);
		createMembershipPanel.add(genreComboBox);
		createMembershipPanel.add(addressLbl);
		createMembershipPanel.add(addressSp);
		createMembershipPanel.add(usernameLbl);
		createMembershipPanel.add(usernameField);
		createMembershipPanel.add(passwordLbl);
		createMembershipPanel.add(passwordField);
		createMembershipPanel.add(confirmPasswordLbl);
		createMembershipPanel.add(confirmPasswordField);
		
		JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		
		//CreateMembership Button
		JButton createMembershipBtn = new JButton("Create Membership");
		createMembershipBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				handleCreateMembershipInput();
				
			}
			
		});
		
		JButton backToLoginBtn = new JButton("Back to Login Form");
		backToLoginBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				new LoginForm();
				
			}
			
		});
		
		buttonPanel.add(createMembershipBtn);
		buttonPanel.add(backToLoginBtn);
		
		//Main Panel
		JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
		mainPanel.add(titleLbl, BorderLayout.NORTH);
		mainPanel.add(createMembershipPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		add(mainPanel);
		setVisible(true);
		
	}
	
	private void handleCreateMembershipInput() {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		
		// Retrieve attributes
		String name = nameField.getText();
		String gender = genreComboBox.getSelectedItem().toString();
		String address = addressField.getText();
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		String confirmPassword = new String(confirmPasswordField.getPassword());
		
		// Put all the text in input field to the HashMap
		inputs.put("name", name);
		inputs.put("gender", gender);
		inputs.put("address", address);
		inputs.put("username", username);
		inputs.put("password", password);
		inputs.put("confirmPassword", confirmPassword);
		// Actor who handle create membership use case always "membership"
		inputs.put("role", "Membership"); 
		
		if (new MemberHandler().createMembership(inputs) != null) {
			
			JOptionPane.showMessageDialog(null, "Membership is sucessfully created");
			Main.user_id = new UserHandler().getByUsername(username).getId();
			
			dispose();
			new MembershipMainForm();
			
		}
		
	}
	
}