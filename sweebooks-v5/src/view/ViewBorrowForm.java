package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BorrowTransactionHandler;
import helper.SQLGetQuery;
import main.Main;
import model.Borrow;
import model.BorrowItem;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class ViewBorrowForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	BorrowTransactionHandler bth = new BorrowTransactionHandler();
	
	public ViewBorrowForm() {
		
		
		//Create UI
		setTitle("Borrow History");
		setSize(800,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setLocation(170, 10);
		setResizable(false);
		setLayout(new GridLayout(1, 2, 0, 0));
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		//ListBorrow Panel
		JPanel listBorrowPanel = new JPanel();
		listBorrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JScrollPane listBorrowSp = new JScrollPane(listBorrowPanel);
		add(listBorrowSp);
		
		//ListBorrowItem Panel
		JPanel listBorrowItemTempPanel = new JPanel();
		listBorrowPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		listBorrowItemTempPanel.setLayout(new GridLayout(1, 1, 0, 0));
		JScrollPane listBorrowItemSp = new JScrollPane(listBorrowItemTempPanel);
		add(listBorrowItemSp);
		
		//Borrow Panel 
		// TODO: show lb di sini
		List<Borrow> lb = bth.getPendingStatus(SQLGetQuery.getRoleFromUserId(Main.user_id) == "Membership");
		listBorrowPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
		for (Borrow borrows : lb) {
			//Borrow Panel
			JPanel borrowPanelForm = new JPanel();
			borrowPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			borrowPanelForm.setLayout(new GridLayout(3, 2, 0, 0));
			
			//Id Label
			JLabel lblId = new JLabel(borrows.getId());
			
			//MemberId Label
			JLabel lblMemberId = new JLabel(borrows.getMemberId());
			
			//Status Label
			JLabel lblStatus = new JLabel(borrows.getStatus());
			
			//BorrowTimestamp Label 
			JLabel lblBorrowTimestamp = new JLabel(borrows.getBorrowTimestamp());
			
			//Accept Button
			JButton acceptBtn = new JButton("Accept");
			if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Administrator")) {
				acceptBtn.setVisible(true);
			}
			acceptBtn.setVisible(false);
			acceptBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) { 	
					//TODO: kalau tombol accept diteken keluar ini:
//							if (bth.acceptBorrowRequest(b.getId())) {
//								
//								JOptionPane.showMessageDialog(null, "It's successfully accepted");
//								
//							}
				}
			});
			
			//Details Button 
			JButton detailsBtn = new JButton("Details");
			listBorrowPanel.add(detailsBtn);
			listBorrowItemTempPanel.setVisible(false);
			detailsBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!listBorrowItemTempPanel.isVisible()) {
						listBorrowItemTempPanel.removeAll();
						listBorrowItemTempPanel.add(getBorrowsItem(borrows));
						listBorrowItemTempPanel.setVisible(true); //kalau ga nyala, nyalain
						detailsBtn.setVisible(true);
					} else {
						listBorrowItemTempPanel.setVisible(false); //kalau nyala, matiin
					}
				}
			});
			
			borrowPanelForm.add(lblId);
			borrowPanelForm.add(lblBorrowTimestamp);
			borrowPanelForm.add(lblMemberId);
			borrowPanelForm.add(detailsBtn);
			borrowPanelForm.add(lblStatus);
			borrowPanelForm.add(acceptBtn);
	
			listBorrowPanel.add(borrowPanelForm);
		}
		
		setVisible(true);

	}
	
	// TODO: show lbi di sini
	public JPanel getBorrowsItem(Borrow b) {
		
		//ListBorrowItem Panel
		JPanel listBorrowItemPanel = new JPanel();
		listBorrowItemPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		List<BorrowItem> lbi = bth.getBookItem(b.getId());
		listBorrowItemPanel.setLayout(new GridLayout(lbi.size(), 1, 5, 5));
		for (BorrowItem borrowItems : lbi) {
			//BorrowItem Panel
			JPanel borrowItemForm = new JPanel();
			borrowItemForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			borrowItemForm.setLayout(new GridLayout(2, 1, 0, 0));
			
			//BookName Label
			JLabel lblBookName = new JLabel(borrowItems.getBookId());
			
			//ReturnTimestamp Label
			JLabel lblReturnTimestamp = new JLabel(borrowItems.getReturnTimestamp());
			
			borrowItemForm.add(lblBookName);
			borrowItemForm.add(lblReturnTimestamp);
			
			listBorrowItemPanel.add(borrowItemForm);
			
		}
		return listBorrowItemPanel;
	}
	
}
