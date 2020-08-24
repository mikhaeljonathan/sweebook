package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.BookHandler;
import controller.GenreHandler;

public class PurchasingMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public PurchasingMainForm() {
		
		// Create UI
		setTitle("Purchasing Main Form");
		setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		// Manage Genre Button
		JButton manageGenreBtn = new JButton("Manage Genre");
		manageGenreBtn.setBounds(10, 11, 140, 35);
		add(manageGenreBtn);
		manageGenreBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(new GenreHandler().showManageGenreForm());
				
			}
			
		});
		
		// Manage Book Button	
		JButton manageBookBtn = new JButton("Manage Book");
		manageBookBtn.setBounds(10, 79, 140, 35);
		add(manageBookBtn);
		manageBookBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(new BookHandler().showManageBookForm());
				
			}
			
		});
		
		// View Book Button
		JButton viewBookBtn = new JButton("View Book");
		viewBookBtn.setBounds(10, 148, 140, 35);
		add(viewBookBtn);
		viewBookBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(new BookHandler().showViewBookForm());
				
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
		
		setVisible(true);
		
	}

	public void removeInternalFrames() {
		
		remove(new GenreHandler().showManageGenreForm());
		remove(new BookHandler().showManageBookForm());
		remove(new BookHandler().showViewBookForm());
		
	}
	
}
