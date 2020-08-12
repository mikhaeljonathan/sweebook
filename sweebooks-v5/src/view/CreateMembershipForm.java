package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MemberHandler;

public class CreateMembershipForm extends JFrame{
	
	public CreateMembershipForm() {
		
		//TODO : create UI
		setTitle("Create Membership");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		setLocationRelativeTo(null);
		JPanel panel1 = new JPanel();
		add(panel1);
		setVisible(true);
	}
	
}