package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BookHandler;
import controller.GenreHandler;
import helper.SQLGetQuery;

import javax.swing.SwingConstants;

public class PurchasingMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public PurchasingMainForm() {
		
		setTitle("Purchasing Main Form");
		getContentPane().setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//Manage Genre Button
		JButton manageGenreBtn = new JButton("Manage Genre");
		manageGenreBtn.setBounds(10, 11, 140, 35);
		add(manageGenreBtn);
		manageGenreBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Manage Genre menu
				add(new GenreHandler().showManageGenreForm());
			}
		});
		
		//Manage Book Button	
		JButton manageBookBtn = new JButton("Manage Book");
		manageBookBtn.setBounds(10, 79, 140, 35);
		add(manageBookBtn);
		manageBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Manage Book menu
				add(new BookHandler().showManageBookForm());
			}
		});
		
		//View Book Button
		JButton viewBookBtn = new JButton("View Book");
		viewBookBtn.setBounds(10, 148, 140, 35);
		add(viewBookBtn);
		viewBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau Book View menu
				add(new BookHandler().showViewBookForm());
			}
		});
		
		setVisible(true);
		
	}

}
