package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.BookHandler;
import controller.BorrowTransactionHandler;
import controller.MemberHandler;
import helper.SQLGetQuery;

public class AdministratorMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private BookHandler bh;
	private BorrowTransactionHandler bth;
	private MemberHandler mh;
	private JLabel welcomeLabel;

	public AdministratorMainForm() {
		
		// Create UI
		setTitle("Administrator Main Form");
		setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		bh = new BookHandler();
		bth = new BorrowTransactionHandler();
		mh = new MemberHandler();
		
		// View Book Button
		JButton viewBookBtn = new JButton("View Book");
		viewBookBtn.setBounds(10, 11, 140, 35);
		add(viewBookBtn);
		viewBookBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bh.showViewBookForm());
				
			}
			
		});
		
		//ViewBorrowForm Button
		JButton viewBorrowBtn = new JButton("View Borrow");
		viewBorrowBtn.setBounds(10, 79, 140, 35);
		add(viewBorrowBtn);
		viewBorrowBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bth.showBorrowForm());
				
			}
		});
		
		//Borrow History Button	
		JButton borrowHistoryBtn = new JButton("Borrow History");
		borrowHistoryBtn.setBounds(10, 148, 140, 35);
		add(borrowHistoryBtn);
		borrowHistoryBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bth.showBorrowHistoryForm());
				
			}
			
		});
		
		//MembershipView Button	
		JButton membershipViewBtn = new JButton("Membership View");
		membershipViewBtn.setBounds(10, 215, 140, 35);
		add(membershipViewBtn);
		membershipViewBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(mh.showViewMembershipForm());
			}
			
		});
		
		// Logout Button
		JButton logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(10, 283, 140, 35);
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
				+ "<br/><br/>You can view books, membership and manage borrows here!");
		welcomeLabel.setBounds(200, 10, 800, 300);
		welcomeLabel.setPreferredSize(new Dimension(400, 50));
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(welcomeLabel);

	}
	
	private void removeInternalFrames() {
		
		welcomeLabel.setVisible(false);
		bh.unshowViewBookForm();
		bth.unshowBorrowForm();
		bth.unshowBorrowHistoryForm();
		mh.unshowViewMembershipForm();
		
	}
	
}