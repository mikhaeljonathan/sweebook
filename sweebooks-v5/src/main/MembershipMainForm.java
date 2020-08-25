package main;

import controller.BookHandler;
import controller.BorrowBookHandler;
import controller.BorrowTransactionHandler;
import view.BorrowBookForm;
import view.ViewBookForm;
import view.ViewBorrowForm;
import view.ViewBorrowHistoryForm;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MembershipMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public MembershipMainForm() {
		
		// Create UI
		setTitle("Membership Main Form");
		setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		// Borrow Book Button
		JButton borrowBookBtn = new JButton("Borrow Book");
		borrowBookBtn.setBounds(10, 11, 140, 35);
		add(borrowBookBtn);
		borrowBookBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(new BorrowBookHandler().showBorrowBookForm());
			    
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
				add(new BookHandler().showViewBookForm());
				
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
				add(new BorrowTransactionHandler().showBorrowForm());
				
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
				add(new BorrowTransactionHandler().showBorrowHistoryForm());
				
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
		
	}
	
	private void removeInternalFrames() {
		
		BorrowBookForm.getInstance().destroy();
		ViewBookForm.getInstance().destroy();
		ViewBorrowForm.getInstance().destroy();
		ViewBorrowHistoryForm.getInstance().destroy();
		
	}
	
}
