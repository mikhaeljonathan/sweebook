package main;

import controller.BookHandler;
import controller.BorrowBookHandler;
import controller.BorrowTransactionHandler;
import helper.SQLGetQuery;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MembershipMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private BorrowBookHandler bbh;
	private BookHandler bh;
	private BorrowTransactionHandler bth;
	private JLabel welcomeLabel;

	public MembershipMainForm() {
		
		// Create UI
		setTitle("Membership Main Form");
		setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		bbh = new BorrowBookHandler();
		bh = new BookHandler();
		bth = new BorrowTransactionHandler();
		
		// Borrow Book Button
		JButton borrowBookBtn = new JButton("Borrow Book");
		borrowBookBtn.setBounds(10, 11, 140, 35);
		add(borrowBookBtn);
		borrowBookBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bbh.showBorrowBookForm());
			    
			 }
			
		});
		
		// View Book Button
		JButton bookViewBtn = new JButton("Book View");
		bookViewBtn.setBounds(10, 79, 140, 35);
		add(bookViewBtn);
		bookViewBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bh.showViewBookForm());
				
			}

		});
		
		// View Borrow Button
		JButton borrowViewBtn = new JButton("Borrow View");
		borrowViewBtn.setBounds(10, 148, 140, 35);
		add(borrowViewBtn);
		borrowViewBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bth.showBorrowForm());
				
			}
			
		});
		 
		// Borrow History Button
		JButton borrowHistoryBtn = new JButton("Borrow History");
		borrowHistoryBtn.setBounds(10, 215, 140, 35);
		add(borrowHistoryBtn);
		borrowHistoryBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(bth.showBorrowHistoryForm());
				
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
				+ "<br/>Welcome to Sweebook Library!"
				+ "<br/><br/>You can borrow your favorite books here!"
				+ "<br/>Enjoy!</HTML>");
		welcomeLabel.setBounds(200, 10, 800, 300);
		welcomeLabel.setPreferredSize(new Dimension(400, 50));
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(welcomeLabel);
		
	}
	
	private void removeInternalFrames() {
		
		welcomeLabel.setVisible(false);
		bbh.unshowBorrowBookForm();
		bh.unshowViewBookForm();
		bth.unshowBorrowForm();
		bth.unshowBorrowHistoryForm();
		
	}
	
}
