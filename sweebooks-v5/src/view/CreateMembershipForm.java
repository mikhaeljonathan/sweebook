package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.MemberHandler;
import main.MembershipMainForm;
import model.Member;

public class CreateMembershipForm extends JFrame{
	private JTextField textField_Name;
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JFrame frame;
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	private JTextArea addressField;
	private JFrame frame;
	
	public CreateMembershipForm() {
		
		frame = new JFrame();
		frame.setTitle("Create Membership");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(111, 57, 242, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(29, 57, 49, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(29, 88, 49, 20);
		frame.getContentPane().add(lblGender);

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(111, 88, 242, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(29, 119, 49, 20);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(29, 181, 49, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(29, 212, 49, 20);
		frame.getContentPane().add(lblPassword);
		
		JButton btnCreate = new JButton("Create Member");
		btnCreate.setBounds(140, 292, 176, 23);
		frame.getContentPane().add(btnCreate);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(111, 181, 242, 20);
		frame.getContentPane().add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(111, 212, 242, 20);
		frame.getContentPane().add(passwordField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 119, 242, 51);
		frame.getContentPane().add(scrollPane);
		
		addressField = new JTextArea();
		scrollPane.setViewportView(addressField);
		
		frame.setVisible(true);
		
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				handleCreateMembershipInput();
				new MembershipMainForm();
				
			}
		});
		
		
	}
	
	private void handleCreateMembershipInput() {
		
		MemberHandler mh = new MemberHandler();
		HashMap<String, String> inputs = new HashMap<String, String>();
		
		// Retrieve attributes
		String name = nameField.getText();
		String gender = comboBox.getSelectedItem().toString();
		String address = addressField.getText();
		String username = usernameField.getText();
		char[] passwordTemp = passwordField.getPassword();
		String password = new String(passwordTemp);
		
		// Put all the text in input field to the HashMap
		inputs.put("name", name);
		inputs.put("gender", gender);
		inputs.put("address", address);
		inputs.put("username", username);
		inputs.put("password", password);
		// Actor who handle create membership use case always "membership"
		inputs.put("role", "Membership"); 
		
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