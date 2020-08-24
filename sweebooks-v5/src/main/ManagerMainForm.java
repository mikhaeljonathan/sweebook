package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.BookHandler;
import controller.BorrowBookHandler;
import controller.BorrowTransactionHandler;
import controller.EmployeeHandler;
import controller.MemberHandler;

public class ManagerMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public ManagerMainForm() {
		
		// Create UI
		setTitle("Manager Main Form");
		getContentPane().setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		// Borrow History Button
		JButton borrowHistoryBtn = new JButton("Borrow History");
		borrowHistoryBtn.setBounds(10, 11, 140, 35);
		add(borrowHistoryBtn);
		borrowHistoryBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// TODO: kalau Borrow History menu
				add(new BorrowTransactionHandler().showBorrowHistoryForm());
				
			 }
			
		});
		
		// View Membership Button
		JButton viewMembershipBtn = new JButton("View Membership");
		viewMembershipBtn.setBounds(10, 79, 140, 35);
		add(viewMembershipBtn);
		viewMembershipBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// TODO: kalau Membership View menu
				add(new MemberHandler().showViewMembershipForm());
				
			}

		});
		
		//Manage Employee Button
		JButton manageEmployeeBtn = new JButton("Manage Employee");
		manageEmployeeBtn.setBounds(10, 148, 140, 35);
		add(manageEmployeeBtn);
		manageEmployeeBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// TODO: kalau Manage Employee Menu
				add(new EmployeeHandler().showManageEmployeeForm());
				
			}
			
		});
		
	}
	
}
