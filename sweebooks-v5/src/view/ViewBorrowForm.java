package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BorrowTransactionHandler;
import helper.SQLGetQuery;
import main.Main;
import model.Borrow;
import model.BorrowItem;

// This is a singleton class
public class ViewBorrowForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ViewBorrowForm instance = null;
	
	private BorrowTransactionHandler bth;
	private JPanel listBorrowPanel;
	private JPanel listBorrowItemTempPanel;
	
	private ViewBorrowForm() {
		
		// Create UI
		setTitle("Borrow View");
		setSize(800,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
		setLayout(new GridLayout(1, 2, 0, 0));
		setBorder(BorderFactory.createLineBorder(Color.black, 2));

		bth = new BorrowTransactionHandler();
		
		// Retrieve all borrows
		List<Borrow> lb = bth.getPendingStatus(SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Membership"));
		
		// ListBorrow Panel
		listBorrowPanel = new JPanel(new GridLayout(lb.size(), 1, 5, 5));
		listBorrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JScrollPane listBorrowSp = new JScrollPane(listBorrowPanel);
		add(listBorrowSp);
		
		// ListBorrowItem Panel
		listBorrowItemTempPanel = new JPanel(new GridLayout(1, 1, 0, 0));
		listBorrowItemTempPanel.setVisible(false);
		listBorrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JScrollPane listBorrowItemSp = new JScrollPane(listBorrowItemTempPanel);
		add(listBorrowItemSp);
		
		// Borrow Panel 
		int counter = 1;
		for (Borrow borrow : lb) {
			
			listBorrowPanel.add(getBorrowView(borrow, counter));
			counter++;
			
		}
		
		setVisible(true);

	}
	
	public static ViewBorrowForm getInstance() {
		
		if (instance == null) {
			instance = new ViewBorrowForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		instance = null;
		
	}
	
	public JPanel getBorrowView(Borrow borrow, int counter) {
		
		// Borrow Panel
		JPanel borrowPanelForm = new JPanel();
		borrowPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		borrowPanelForm.setLayout(new GridLayout(3, 2, 0, 0));
		
		// Id Label
		JLabel lblId = new JLabel(counter + ".");
		counter++;
		
		// MemberId Label
		JLabel lblUsername = new JLabel("Username: " + SQLGetQuery.getUsernameFromId(borrow.getMemberId()));
		
		// Status Label
		String status = borrow.getStatus();
		JLabel lblStatus = new JLabel(status);
		if (status.equals("Pending")) {
			
			lblStatus.setForeground(Color.RED);
			
		} else {
			
			lblStatus.setForeground(Color.GREEN);
			
		}
		
		// BorrowTimestamp Label 
		String timeStamp = borrow.getBorrowTimestamp();
		timeStamp = timeStamp.substring(0, timeStamp.length() - 2);
		JLabel lblBorrowTimestamp = new JLabel("Borrow time: " + timeStamp);
		
		// Accept Button (Administrator only)
		JButton acceptBtn = new JButton("Accept");
		acceptBtn.setVisible(false);
		if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Administrator")) {
			
			acceptBtn.setVisible(true);
			
		}
		acceptBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) { 	
				
				if (bth.acceptBorrowRequest(borrow.getId())) {
					
					JOptionPane.showMessageDialog(null, "It's successfully accepted");
					refreshListBorrowPanel();
					
				}
				
			}
			
		});
		
		// Details Button 
		JButton detailsBtn = new JButton("Details");
		detailsBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (!listBorrowItemTempPanel.isVisible()) {
					
					refreshListBorrowItem(borrow);
					
				} else {
					
					listBorrowItemTempPanel.setVisible(false);
					
				}
				
			}
			
		});
		
		borrowPanelForm.add(lblId);
		borrowPanelForm.add(lblBorrowTimestamp);
		borrowPanelForm.add(lblUsername);
		borrowPanelForm.add(detailsBtn);
		borrowPanelForm.add(lblStatus);
		borrowPanelForm.add(acceptBtn);
		
		return borrowPanelForm;
		
	}
	
	public JPanel getBorrowItemView(Borrow b) {
		
		// Retrieve borrow items for corresponding borrow
		List<BorrowItem> lbi = bth.getBookItem(b.getId());
		
		// ListBorrowItem Panel
		JPanel listBorrowItemPanel = new JPanel(new GridLayout(lbi.size(), 1, 5, 5));
		listBorrowItemPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// For each borrow item
		for (BorrowItem borrowItem : lbi) {
			
			String bookId = borrowItem.getBookId();
			
			// BorrowItem Panel
			JPanel borrowItemForm = new JPanel(new GridLayout(2, 1, 0, 0));
			borrowItemForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
			// BookName Label
			JLabel lblBookName = new JLabel("Book : " + SQLGetQuery.getBookNameFromId(bookId));
			borrowItemForm.add(lblBookName);
			
			JLabel lblGenre = new JLabel("Genre : " + SQLGetQuery.getTypeFromBookId(bookId));
			borrowItemForm.add(lblGenre);
			
			listBorrowItemPanel.add(borrowItemForm);
			
		}
		
		return listBorrowItemPanel;
		
	}
	
	private void refreshListBorrowPanel() {
		
		listBorrowPanel.setVisible(false);
		listBorrowPanel.removeAll();
		int counter = 1;
		List<Borrow> lb = bth.getPendingStatus(SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Membership"));
		
		listBorrowPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
		
		for (Borrow borrow : lb) {
			
			listBorrowPanel.add(getBorrowView(borrow, counter));
			counter++;
			
		}
		listBorrowPanel.setVisible(true);
		
	}
	
	private void refreshListBorrowItem(Borrow borrow) {
		
		listBorrowItemTempPanel.setVisible(false);
		listBorrowItemTempPanel.removeAll();
		listBorrowItemTempPanel.add(getBorrowItemView(borrow));
		listBorrowItemTempPanel.setVisible(true);
		
	}
	
}