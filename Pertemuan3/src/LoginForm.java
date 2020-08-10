import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame{
	
	public LoginForm() {
		setTitle("Login Form");
		setSize(400, 225);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Label, Username, Password -> JLabel
		// Input username -> JTextField
		// Input password -> JPasswordField
		// Button Login -> JButton
		
		JLabel title = new JLabel("Login Form", JLabel.CENTER);
		title.setPreferredSize(new Dimension(400, 50));
		title.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		JTextField txtUsername = new JTextField();
		JPasswordField txtPassword = new JPasswordField();
		JButton button = new JButton("Login");
		
		
		JPanel pnlForm = new JPanel(new GridLayout(2, 2, 5, 5));
		pnlForm.add(lblUsername);
		pnlForm.add(txtUsername);
		pnlForm.add(lblPassword);
		pnlForm.add(txtPassword);
		
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(pnlForm, BorderLayout.CENTER);
		mainPanel.add(button, BorderLayout.SOUTH);
		mainPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		
		add(mainPanel);
		
		setVisible(true);
	}
	
	
}
