package main;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import view.ViewBookForm;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PurchasingMainForm extends JFrame {
	
	public PurchasingMainForm() {
		setTitle("Purchasing Main Form");
		getContentPane().setLayout(null);
		setSize(900, 500);
		setLocationRelativeTo(null);
		
		JButton btnManageGenre = new JButton("Manage Genre");
		btnManageGenre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//tampilin manageGenreForm
			}
		});
		btnManageGenre.setBounds(10, 45, 123, 33);
		getContentPane().add(btnManageGenre);
		
		JButton btnManageBook = new JButton("Manage Book");
		btnManageBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//tampilin manageBookForm
			}
		});
		btnManageBook.setBounds(10, 105, 123, 33);
		getContentPane().add(btnManageBook);
		
		JButton btnBookView = new JButton("Book View");
		btnBookView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewBookForm viewBookForm = new ViewBookForm();
				viewBookForm.setResizable(false);
				viewBookForm.setLocation(175, 10);
				getContentPane().add(viewBookForm);
				viewBookForm.getContentPane().setLayout(null);
				viewBookForm.setVisible(true);
			}
		});
		btnBookView.setBounds(10, 165, 123, 33);
		getContentPane().add(btnBookView);
		
		setVisible(true);
	}
}
