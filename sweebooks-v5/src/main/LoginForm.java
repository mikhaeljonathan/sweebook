package main;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import controller.MemberHandler;
import model.Member;
import view.CreateMembershipForm;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginForm extends JFrame{
	private JTextField textField_Username;
	private JPasswordField passwordField;

	public LoginForm() {
		
		//Create UI
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		textField_Username = new JTextField();
		textField_Username.setBounds(123, 94, 248, 20);
		getContentPane().add(textField_Username);
		textField_Username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(49, 97, 64, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(49, 128, 64, 14);
		getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(119, 172, 181, 23);
		getContentPane().add(btnLogin);
		
		JButton btnCreate = new JButton("Create Membership");
		btnCreate.setBounds(119, 209, 181, 23);
		getContentPane().add(btnCreate);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 125, 248, 20);
		getContentPane().add(passwordField);
		
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CreateMembershipForm();
			}
		});
		
		setVisible(true);
		
		
//		// TODO: after the create membership button pressed, then do below
//		
//		// Show the form
//		MemberHandler mh = new MemberHandler();
//		mh.showCreateMembershipForm();
//		
//		// Handle the input field
//		handleCreateMembershipInput(mh);
		
	}
	
	private void handleCreateMembershipInput(MemberHandler mh) {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		
		// Put all the text in input field to the HashMap
		inputs.put("name", "input1");
		inputs.put("gender", "input1");
		inputs.put("address", "input1");
		inputs.put("username", "input1");
		inputs.put("password", "input1");
		// Actor who handle create membership use case always "membership"
		inputs.put("role", "membership"); 
		
		// Create new Member entity
		Member m = mh.createMembership(inputs);
		
		// Show Member entity in UI
		showMemberInGUI(m);
		
	}
	
	private void showMemberInGUI(Member m) {
		
		// TODO: tampilin m di UI
		if (m != null) {
			
		} else {
			//show error message
		}
		
	}
}
