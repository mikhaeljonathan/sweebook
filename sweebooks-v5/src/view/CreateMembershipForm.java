package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MemberHandler;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class CreateMembershipForm extends JFrame{
	private JTextField textField_Name;
	private JTextField textField_Username;
	private JTextField textField_Password;
	
	public CreateMembershipForm() {
		
		//TODO : create UI
		setTitle("Create Membership");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(111, 57, 242, 20);
		getContentPane().add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(29, 57, 49, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(29, 88, 49, 20);
		getContentPane().add(lblGender);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		comboBox.setBounds(111, 88, 242, 20);
		getContentPane().add(comboBox);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(29, 119, 49, 20);
		getContentPane().add(lblAddress);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(29, 181, 49, 20);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(29, 212, 49, 20);
		getContentPane().add(lblPassword);
		
		JButton btnCreate = new JButton("Create Member");
		btnCreate.setBounds(140, 292, 176, 23);
		getContentPane().add(btnCreate);
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		textField_Username.setBounds(111, 181, 242, 20);
		getContentPane().add(textField_Username);
		
		textField_Password = new JTextField();
		textField_Password.setColumns(10);
		textField_Password.setBounds(111, 212, 242, 20);
		getContentPane().add(textField_Password);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 119, 242, 51);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		setVisible(true);
	}
}