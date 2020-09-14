package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.EmployeeHandler;
import helper.SQLGetQuery;

public class HumanCapitalMainForm extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private EmployeeHandler eh;
	private JLabel welcomeLabel;

	public HumanCapitalMainForm() {
	
		// Create UI
		setTitle("Human Capital Main Form");
		getContentPane().setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		eh = new EmployeeHandler();
		
		// Manage Employee Button
		JButton manageEmployeeBtn = new JButton("Manage Employee");
		manageEmployeeBtn.setBounds(10, 11, 140, 35);
		add(manageEmployeeBtn);
		manageEmployeeBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(eh.showManageEmployeeForm());
				
			}
			
		});
		
		// Logout Button
		JButton logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(10, 63, 140, 35);
		add(logoutBtn);
		logoutBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				dispose();
				Main.user_id = null;
				new LoginForm();
				
			}
			
		});
		
		// Welcome Label
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String time = "night";
		if (hour < 12) {
			
			time = "morning";
			
		} else if (hour < 18) {
			
			time = "afternoon";
			
		} else if (hour < 22){
			
			time = "evening";
			
		}
		welcomeLabel = new JLabel("<HTML>Good " + time + ", " + SQLGetQuery.getNameFromUserId(Main.user_id) + "!"
				+ "<br/><br/>You can view and add employees here!");
		welcomeLabel.setBounds(200, 10, 800, 300);
		welcomeLabel.setPreferredSize(new Dimension(400, 50));
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(welcomeLabel);
		
	}
	
	private void removeInternalFrames() {
		
		welcomeLabel.setVisible(false);
		eh.unshowManageEmployeeForm();
		
	}
	
}