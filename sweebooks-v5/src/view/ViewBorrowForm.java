package view;

import java.awt.BorderLayout;
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
		setTitle("View Borrow Form");
		setSize(800, 400);
		setClosable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
		
		//ListBorrow Panel
		List<Borrow> lb = bth.getPendingStatus(SQLGetQuery.getRoleFromUserId(Main.user_id) == "Membership");
		
		JPanel listBorrowPanel = new JPanel();
		JScrollPane listBorrowSp = new JScrollPane(listBorrowPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		listBorrowPanel.setLayout(new GridLayout(lb.size(), 0, 5, 5));
		for (Borrow borrows : lb) {
			
			listBorrowPanel.add(borrowPanelForm(borrows.getId(), borrows.getMemberId(), borrows.getStatus(), borrows.getBorrowTimestamp()));
			
			List<BorrowItem> lbi = bth.getBookItem(borrows.getId());
			
			//ListBorrowItem Panel
			JPanel listBorrowItemPanel = new JPanel();
			//JList buat list Borrow Item
			JList<BorrowItem> listBorrowItem = new JList<BorrowItem>();
			DefaultListModel listBorrowitemModel = new DefaultListModel();
			for (BorrowItem borrowItems : lbi) {
				listBorrowitemModel.addElement(borrowItems.getId() + "          " + borrowItems.getReturnTimestamp());
			}
			listBorrowItem.setModel(listBorrowitemModel);
			JScrollPane listBorrowItemSp = new JScrollPane(listBorrowItem);
			listBorrowItemPanel.add(listBorrowItemSp);
			
			listBorrowItemPanel.setVisible(false);
			
			//Details Button Panel
			JPanel btnPanel = new JPanel();
			// kalau salah satu item borrow di klik akan execute this
			JButton detailsBtn = new JButton("Details");
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
			
			btnPanel.add(detailsBtn);
			btnPanel.add(listBorrowItemPanel);
			
			listBorrowPanel.add(btnPanel);
		}
		
		add(listBorrowSp);
		setVisible(true);

	}
	
	private Component borrowPanelForm(String id, String memberId, String status, String borrowStamp) {
		//Borrow Panel
		JPanel borrowPanelForm = new JPanel();
		borrowPanelForm.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
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
		
		//Accept Button
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
	
}
