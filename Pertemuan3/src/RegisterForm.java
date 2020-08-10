import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class RegisterForm extends JFrame{
	
	public RegisterForm() {
//		Label
//		Text Field
//		Password Field
//		Text Area
//		Radio Button (and grouping)
//		Check Box
//		Combo Box
//		List
//		Spinner
//		Button
		
		setTitle("Register Form");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel lblTitle, lblName, lblPassword, lblAddress, lblGender, lblCampus, lblHobby, lblAge;
		JTextField txtName;
		JPasswordField txtPassword;
		JTextArea txtAddress;
		JRadioButton rbMale, rbFemale;
		JComboBox<String> cbCampus;
		JList<String> lHobby;
		JSpinner spAge;
		JCheckBox cbAgree;
		JButton btnRegister;
		
		lblTitle = new JLabel("Register Form", JLabel.CENTER);
		lblName = new JLabel("Name");
		lblPassword = new JLabel("Password");
		lblAddress = new JLabel("Address");
		lblGender = new JLabel("Gender");
		lblCampus = new JLabel("Campus");
		lblHobby = new JLabel("Hobby");
		lblAge = new JLabel("Age");
		
		txtName = new JTextField();
		txtPassword = new JPasswordField();
		
		txtAddress = new JTextArea();
		JScrollPane spAddress = new JScrollPane(txtAddress);
		
		ButtonGroup group = new ButtonGroup();
		rbMale = new JRadioButton("Male");
		rbFemale = new JRadioButton("Female");
		group.add(rbMale);
		group.add(rbFemale);
		
		cbCampus = new JComboBox<>(new String[] {
			"Binus Kemanggisan", "BLI"
		});
		lHobby = new JList<>(new String[] {
			"Coding", "Sleeping", "Others", "Hobby 1", "Hobby 2", "Hobby 3"	
		});
		JScrollPane spHobby = new JScrollPane(lHobby);
		
		SpinnerNumberModel snm = new SpinnerNumberModel(17, 12, 100, 1);
		spAge = new JSpinner(snm);
		
		cbAgree = new JCheckBox("I agree with terms and conditions");
				
		btnRegister = new JButton("Register");
		
		JPanel pnlForm = new JPanel(new GridLayout(7, 2, 5, 5));
		pnlForm.add(lblName);
		pnlForm.add(txtName);
		pnlForm.add(lblPassword);
		pnlForm.add(txtPassword);
		pnlForm.add(lblAddress);
		pnlForm.add(spAddress);
		
		
		JPanel pnlGender = new JPanel(new GridLayout(1, 2));
		pnlGender.add(rbMale);
		pnlGender.add(rbFemale);
		
		pnlForm.add(lblGender);
		pnlForm.add(pnlGender);
		pnlForm.add(lblCampus);
		pnlForm.add(cbCampus);
		pnlForm.add(lblHobby);
		pnlForm.add(spHobby);
		pnlForm.add(lblAge);
		pnlForm.add(spAge);
		
		JPanel pnlWithAgree = new JPanel(new BorderLayout());
		pnlWithAgree.add(pnlForm, BorderLayout.CENTER);
		pnlWithAgree.add(cbAgree, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.add(lblTitle, BorderLayout.NORTH);
		panel.add(pnlWithAgree, BorderLayout.CENTER);
		panel.add(btnRegister, BorderLayout.SOUTH);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(panel);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new RegisterForm();
	}
}
