package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import Helper.SQLGetQuery;
import main.Main;
import model.Borrow;
import model.BorrowItem;

public class ViewBorrowForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	BorrowTransactionHandler bth = new BorrowTransactionHandler();
	
	public ViewBorrowForm() {
		
		//Create UI
		setTitle("View Borrow Form");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
		
		// TODO: show lb here
		List<Borrow> lb = bth.getPendingStatus(SQLGetQuery.getRoleFromUserId(Main.user_id) == "Membership");
		
		//ListBorrow Panel
		JPanel listBorrowPanel = new JPanel();
		JScrollPane listBorrowSp = new JScrollPane(listBorrowPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//add list borrow to listBorrowPanel
		int i=0;
		for (Borrow borrows : lb) {
			i++;
			listBorrowPanel.setLayout(new GridLayout(i+1, 0, 0, 0));
			listBorrowPanel.add(borrowPanelForm(borrows.getId(), borrows.getMemberId(), borrows.getStatus(), borrows.getBorrowTimestamp()));
			
			// kalau salah satu item borrow di klik akan execute this
			Borrow b = new Borrow();
			List<BorrowItem> lbi = bth.getBookItem(b.getId());
			
			//ListBorrowItem Panel
			JPanel listBorrowItemPanel = new JPanel();
			JScrollPane listBorrowItemSp = new JScrollPane(listBorrowItemPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			//Details Button
			JButton detailsBtn = new JButton("Details");
			listBorrowPanel.add(detailsBtn);
			detailsBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!listBorrowItemPanel.isVisible()) { 
						listBorrowItemPanel.setVisible(true); //kalau ga nyala, nyalain
						detailsBtn.setVisible(true);
						listBorrowItemPanel.revalidate();
					} else {
						listBorrowItemPanel.setVisible(false); //kalau nyala, matiin
					}
				}
			});
			
			//add list borrow item to listBorrowItem Panel
			int k=0;
			for (BorrowItem borrowItems : lbi) {
				k++;
				listBorrowItemPanel.add(borrowItemPanelForm(borrowItems.getBookId(), borrowItems.getReturnTimestamp()));
				listBorrowItemPanel.setLayout(new GridLayout(k+1, 0, 0, 0));
			}
			listBorrowPanel.add(listBorrowItemSp);
			listBorrowItemPanel.setVisible(false);
			
		}
		
		getContentPane().add(listBorrowSp);
		setVisible(true);
		

	}
	
	private Component borrowPanelForm(String id, String memberId, String status, String borrowStamp) {
		//Borrow Panel
		JPanel borrowPanelForm = new JPanel();
		borrowPanelForm.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
		borrowPanelForm.setLayout(new GridLayout(5, 1, 0, 0));
		
		//Id Title
		JLabel titleId = new JLabel(id, JLabel.LEFT);
		titleId.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		borrowPanelForm.add(titleId);
		
		//BorrowStamp Title
		JLabel titleBorrowStamp = new JLabel(borrowStamp);
		titleBorrowStamp.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		borrowPanelForm.add(titleBorrowStamp);
		
		//MemberId Title
		JLabel titleMemberId = new JLabel(memberId, JLabel.LEFT);
		titleMemberId.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		borrowPanelForm.add(titleMemberId);
		
		//Status Title
		JLabel titleStatus = new JLabel(status, JLabel.LEFT);
		titleStatus.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		borrowPanelForm.add(titleStatus);
		
		//Accpet Button
		JButton acceptBtn = new JButton("Accept");
		if (SQLGetQuery.getRoleFromUserId(Main.user_id).equals("Administrator")) {
			acceptBtn.setVisible(true);
		}
		acceptBtn.setVisible(false);
		borrowPanelForm.add(acceptBtn);
		acceptBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 	
				//TODO: kalau tombol accept diteken keluar ini:
//				if (bth.acceptBorrowRequest(b.getId())) {
//					
//					JOptionPane.showMessageDialog(null, "It's successfully accepted");
//					
//				}
			}
		});
		
		return borrowPanelForm;
	}
	
	private Component borrowItemPanelForm(String bookName, String returnStamp) {
		//BorrowItem Panel
		JPanel borrowItemPanel = new JPanel();
		borrowItemPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
		borrowItemPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		//BookName Title
		JLabel titleBookName = new JLabel(bookName);
		titleBookName.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		borrowItemPanel.add(titleBookName);
		
		//ReturnStamp Title
		JLabel titleReturnStamp = new JLabel(returnStamp);
		titleReturnStamp.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		borrowItemPanel.add(titleReturnStamp);
		
		return borrowItemPanel;
	}
	
}
