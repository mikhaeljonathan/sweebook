package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ViewBorrowHistoryForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ViewBorrowHistoryForm instance = null;
	
	private BorrowTransactionHandler bth;
	private JPanel listBorrowItemTempPanel;
	private JPanel listBorrowPanel;
	private int month = 0;
	private int year = 0;
	
	private ViewBorrowHistoryForm () {
		
		// Create UI
		setTitle("Borrow History");
		setSize(800,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
		setLayout(new BorderLayout(5, 5));;
		
		bth = new BorrowTransactionHandler();
		
		// Top Panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 4, 10, 10));
		
		// Month 
		JLabel lblMonth = new JLabel("Month");
		JComboBox<String> cbMonth = new JComboBox<>(new String[] {
				"-"
		});
		for(int i = 1; i <= 12; i++) {
			
			cbMonth.addItem(Integer.toString(i));
			
		}
		cbMonth.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					month = cbMonth.getSelectedIndex();
					refreshAll(month, year);
					
				}
				
			}
			
		});
		
		// Year
		JLabel lblYear = new JLabel("Year");
		JComboBox<String> cbYear = new JComboBox<>(new String[] {
				"-"
		});
		for(int i = 2001; i <= 2020; i++) {
			
			cbYear.addItem(Integer.toString(i));
			
		}
		cbYear.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					year = cbYear.getSelectedIndex();
					refreshAll(month, year);
					
				}
				
			}
			
		});
		
		// Add all to the top panel
		topPanel.add(lblMonth);
		topPanel.add(cbMonth);
		topPanel.add(lblYear);
		topPanel.add(cbYear);
		
		// Retrieve all borrows
		List<Borrow> lb = bth.getAcceptStatus(null);
		
		// Mid Panel
		JPanel midPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		
		// ListBorrow Panel
		listBorrowPanel = new JPanel(new GridLayout(lb.size(), 1, 5, 5));
		listBorrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JScrollPane listBorrowSp = new JScrollPane(listBorrowPanel);
		midPanel.add(listBorrowSp);
		
		// ListBorrowItem Panel
		listBorrowItemTempPanel = new JPanel(new GridLayout(1, 1, 0, 0));
		listBorrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JScrollPane listBorrowItemSp = new JScrollPane(listBorrowItemTempPanel);
		listBorrowItemTempPanel.setVisible(false);
		midPanel.add(listBorrowItemSp);
		
		// Borrow Panel 
		int counter = 1;
		for (Borrow borrows : lb) {
			
			listBorrowPanel.add(getBorrowView(borrows, counter));
			counter++;
			
		}
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		setVisible(true);
		
	}
	
	public static ViewBorrowHistoryForm getInstance() {
		
		if (instance == null) {
			instance = new ViewBorrowHistoryForm();
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
		timeStamp = timeStamp.substring(0, timeStamp.length() - 11);
		JLabel lblBorrowTimestamp = new JLabel("Borrow date: " + timeStamp);
		
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
		
		return borrowPanelForm;
		
	}

	public JPanel getBorrowItemView(Borrow borrow) {
		
		// Retrieve borrow items for corresponding borrow
		List<BorrowItem> lbi = bth.getBookItem(borrow.getId());
		
		// ListBorrowItem Panel
		JPanel listBorrowItemPanel = new JPanel(new GridLayout(lbi.size(), 1, 5, 5));
		listBorrowItemPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// For each borrow item
		for (BorrowItem borrowItem : lbi) {
			
			// BorrowItem Panel
			JPanel borrowItemForm = new JPanel(new GridLayout(3, 1, 0, 0));
			borrowItemForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
			// BookName Label
			JLabel lblBookName = new JLabel(SQLGetQuery.getBookNameFromId(borrowItem.getBookId()));
			
			// ReturnTimestamp Label
			String returnTime = borrowItem.getReturnTimestamp();
			returnTime = returnTime.substring(0, returnTime.length() - 2);
			if (!new BorrowItem().isBookAlreadyReturn(borrowItem.getId(), borrowItem.getBookId())) {
				
				returnTime = "Hasn't returned";
				
			}
			JLabel lblReturnTimestamp = new JLabel("Return time: " + returnTime);
			
			// Return Button (Membership only)
			JButton returnBtn = new JButton("Return");
			returnBtn.setVisible(false);
			if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Membership")) {
				
				if (!new BorrowItem().isBookAlreadyReturn(borrowItem.getId(), borrowItem.getBookId())) 
					returnBtn.setVisible(true);
				
			}
			returnBtn.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					HashMap<String, String> inputs = new HashMap<String, String>();
					inputs.put("id", borrowItem.getId());
					inputs.put("bookId", borrowItem.getBookId());
					
					if (bth.returnBook(inputs) != null) {
						
						JOptionPane.showMessageDialog(null, "Book is successfully returned");
						refreshListBorrowItem(borrow);
						
					}
					
				}
				
			});
			
			borrowItemForm.add(lblBookName);
			borrowItemForm.add(lblReturnTimestamp);
			borrowItemForm.add(returnBtn);
			
			listBorrowItemPanel.add(borrowItemForm);
			
		}
		
		return listBorrowItemPanel;
		
	}
	
	private void refreshAll(int month, int year) {
		
		Date date = null;
		
		String sDate = "01/%02d/%d";
		sDate = String.format(sDate, month, year + 2000);
		
		if (month != 0 || year != 0) {
			
			try {
				
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
				
			} catch (Exception e2) {
				
			}
			
		}
		
		// Retrieve borrow
		List<Borrow> lb = bth.getAcceptStatus(date);
		
		// Borrow panel
		listBorrowPanel.setVisible(false);
		listBorrowPanel.removeAll();
		
		listBorrowPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
		
		int counter = 1;
		for (Borrow borrows : lb) {
			
			listBorrowPanel.add(getBorrowView(borrows, counter));
			counter++;
			
		}
		listBorrowPanel.setVisible(true);
		
		// Borrow item panel
		listBorrowItemTempPanel.removeAll();
		listBorrowItemTempPanel.setVisible(false);
		
	}
	
	
	private void refreshListBorrowItem(Borrow borrow) {
		
		listBorrowItemTempPanel.setVisible(false);
		listBorrowItemTempPanel.removeAll();
		listBorrowItemTempPanel.add(getBorrowItemView(borrow));
		listBorrowItemTempPanel.setVisible(true);
		
	}

}