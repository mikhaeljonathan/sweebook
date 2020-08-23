package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.BookHandler;
import controller.BorrowTransactionHandler;
import controller.GenreHandler;
import controller.MemberHandler;

public class AdministratorMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public AdministratorMainForm() {
		
		setTitle("Administrator Main Form");
		getContentPane().setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//View Book Button
		JButton viewBookBtn = new JButton("View Book");
		viewBookBtn.setBounds(10, 11, 140, 35);
		add(viewBookBtn);
		viewBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Book View Menu
				add(new BookHandler().showViewBookForm());
			}
		});
		
		//ViewBorrowForm Button
		JButton viewBorrowBtn = new JButton("View Borrow");
		viewBorrowBtn.setBounds(10, 79, 140, 35);
		add(viewBorrowBtn);
		viewBorrowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Borrow View menu
				add(new BorrowTransactionHandler().showBorrowForm());
			}
		});
		
		//Borrow History Button	
		JButton borrowHistoryBtn = new JButton("Borrow History");
		borrowHistoryBtn.setBounds(10, 148, 140, 35);
		add(borrowHistoryBtn);
		borrowHistoryBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Borrow History menu
				add(new BorrowTransactionHandler().showBorrowHistoryForm());
			}
		});
		
		//MembershipView Button	
		JButton membershipViewBtn = new JButton("Membership View");
		membershipViewBtn.setBounds(10, 215, 140, 35);
		add(membershipViewBtn);
		membershipViewBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Membership View menu
				add(new MemberHandler().showViewMembershipForm());
			}
		});
		
		setVisible(true);
		
	}
	
}
