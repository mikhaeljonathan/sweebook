  
package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.BorrowTransactionHandler;
import controller.EmployeeHandler;
import controller.MemberHandler;
import helper.SQLGetQuery;

public class ManagerMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private BorrowTransactionHandler bth;
	private MemberHandler mh;
	private EmployeeHandler eh;
	private JLabel welcomeLabel;

	public ManagerMainForm() {
		
		// Create UI
		setTitle("Manager Main Form");
		getContentPane().setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		bth = new BorrowTransactionHandler();
		mh = new MemberHandler();
		eh = new EmployeeHandler();
		
		// Borrow History Button
		JButton borrowHistoryBtn = new JButton("Borrow History");
		borrowHistoryBtn.setBounds(10, 11, 140, 35);
		add(borrowHistoryBtn);
		borrowHistoryBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bth.showBorrowHistoryForm());
				
			 }
			
		});
		
		// View Membership Button
		JButton viewMembershipBtn = new JButton("View Membership");
		viewMembershipBtn.setBounds(10, 79, 140, 35);
		add(viewMembershipBtn);
		viewMembershipBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(mh.showViewMembershipForm());
				
			}

		});
		
		//Manage Employee Button
		JButton manageEmployeeBtn = new JButton("Manage Employee");
		manageEmployeeBtn.setBounds(10, 148, 140, 35);
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
		logoutBtn.setBounds(10, 216, 140, 35);
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
		String time = "morning";
		if (hour > 11 && hour < 18) {
			
			time = "afternoon";
			
		} else if (hour < 22) {
			
			time = "evening";
			
		} else if (hour <= 24){
			
			time = "night";
			
		}
		welcomeLabel = new JLabel("<HTML>Good " + time + ", Boss " + SQLGetQuery.getNameFromUserId(Main.user_id) + "!"
				+ "<br/><br/>You can view borrows, memberships,"
				+ "<br/>and manage employees here!");
		welcomeLabel.setBounds(200, 10, 800, 300);
		welcomeLabel.setPreferredSize(new Dimension(400, 50));
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(welcomeLabel);
		
	}
	
	private void removeInternalFrames() {
		
		welcomeLabel.setVisible(false);
		bth.unshowBorrowHistoryForm();
		mh.unshowViewMembershipForm();
		eh.unshowManageEmployeeForm();
		
	}
	
}