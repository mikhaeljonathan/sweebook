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
import controller.GenreHandler;
import helper.SQLGetQuery;

public class PurchasingMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private GenreHandler gh;
	private BookHandler bh;
	private JLabel welcomeLabel;
	
	public PurchasingMainForm() {
		
		// Create UI
		setTitle("Purchasing Main Form");
		setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		gh = new GenreHandler();
		bh = new BookHandler();
		
		// Manage Genre Button
		JButton manageGenreBtn = new JButton("Manage Genre");
		manageGenreBtn.setBounds(10, 11, 140, 35);
		add(manageGenreBtn);
		manageGenreBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				removeInternalFrames();
				add(gh.showManageGenreForm());
				
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
				add(bh.showManageBookForm());
				
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
				add(bh.showViewBookForm());
				
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
		String time = "night";
		if (hour < 12) {
			
			time = "morning";
			
		} else if (hour < 18) {
			
			time = "afternoon";
			
		} else if (hour < 22){
			
			time = "evening";
			
		}
		welcomeLabel = new JLabel("<HTML>Good " + time + ", " + SQLGetQuery.getNameFromUserId(Main.user_id) + "!"
				+ "<br/><br/>You can manage books and genres here!");
		welcomeLabel.setBounds(200, 10, 800, 300);
		welcomeLabel.setPreferredSize(new Dimension(400, 50));
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(welcomeLabel);
		
	}

	public void removeInternalFrames() {
		
		welcomeLabel.setVisible(false);
		gh.unshowManageGenreForm();
		bh.unshowManageBookForm();
		bh.unshowViewBookForm();
		
	}
	
}
