package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.swing.border.Border;
import javax.swing.text.html.ListView;

import controller.BorrowTransactionHandler;
import model.Borrow;
import model.BorrowItem;

public class ViewBorrowHistoryForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	public ViewBorrowHistoryForm () {
		
		BorrowTransactionHandler bth = new BorrowTransactionHandler();
		
		//Create UI
		setTitle("Borrow History");
		setSize(800,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setLocation(170, 10);
		setResizable(false);
		setLayout(new BorderLayout(5, 5));;
		
		//Panel Atas
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 4, 0, 0));
		
		//Month Label 
		JLabel lblMonth = new JLabel("Month");
		
		//Month ComboBox
		JComboBox<Integer> cbMonth = new JComboBox<>(new Integer[] {
				-1
		});
		for(int i = 1;i<=12;i++) {
			cbMonth.addItem(i);
		}
		
		//Year Label
		JLabel lblYear = new JLabel("Year");
		
		//Year ComboBox
		JComboBox<Integer> cbYear = new JComboBox<>(new Integer[] {
				-1
		});
		//buat nambah tahun di combo box
		for(int i = 2001;i<=2999;i++) {
			cbYear.addItem(i);
		}
		
		topPanel.add(lblMonth);
		topPanel.add(cbMonth);
		topPanel.add(lblYear);
		topPanel.add(cbYear);
		
		int month = cbMonth.getSelectedIndex();
		int year = cbYear.getSelectedIndex();
		
		String sDate = "01/%02d/%d";
		sDate = String.format(sDate, month, year);
		Date date = null;
		
		try {
			
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
			
		} catch (Exception e) {
			
		}
		
		//Panel Tengah
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		//List Borrow Panel
		JPanel listBorrowPanel = new JPanel();
		JScrollPane listBorrowSp = new JScrollPane(listBorrowPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//ListBorrowItem Panel
		JPanel listBorrowItemPanel = new JPanel();
		JScrollPane listBorrowItemSp = new JScrollPane(listBorrowItemPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		//Panel untuk tampung list Borrow
		JPanel listBorrowTempPanel = new JPanel();
		
		// TODO: show lb di sini
		List<Borrow> lb = bth.getAcceptStatus(date);
		listBorrowTempPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
		for (Borrow borrows : lb) {
			//Borrow Panel
			JPanel borrowPanelForm = new JPanel();
			borrowPanelForm.setLayout(new GridLayout(3, 2, 0, 0));
			
			//Id Label
			JLabel lblId = new JLabel(borrows.getId());
			
			//MemberId Label
			JLabel lblMemberId = new JLabel(borrows.getMemberId());
			
			//Status Label
			JLabel lblStatus = new JLabel(borrows.getStatus());
			
			//BorrowTimestamp Label 
			JLabel lblBorrowTimestamp = new JLabel(borrows.getBorrowTimestamp());
			
			//empty Label
			JLabel lblEmpty = new JLabel();
			
			borrowPanelForm.add(lblId);
			borrowPanelForm.add(lblBorrowTimestamp);
			borrowPanelForm.add(lblMemberId);
			borrowPanelForm.add(lblEmpty);
			borrowPanelForm.add(lblStatus);
			borrowPanelForm.add(lblEmpty);
			
			listBorrowTempPanel.add(borrowPanelForm);
			
			//ListBorrowItemTempPanel buat tampung list BorrowItem
			JPanel listBorrowItemTempPanel = new JPanel();
			
			// TODO: show lbi di sini
			List<BorrowItem> lbi = bth.getBookItem(borrows.getId());
			for (BorrowItem borrowItems : lbi) {
				//BorrowItem Panel
				JPanel borrowItemForm = new JPanel();
				borrowItemForm.setLayout(new GridLayout(3, 1, 0, 0));
				
				//BookName Label
				JLabel lblBookName = new JLabel(borrowItems.getBookId());
				
				//ReturnTimestamp Label
				JLabel lblReturnTimestamp = new JLabel(borrowItems.getReturnTimestamp());
				
				//Return Button
				JButton returnBtn = new JButton();
				returnBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						String denda = JOptionPane.showInputDialog(null, "Denda: ", "Konfirmasi Denda");
						
						JOptionPane.showMessageDialog(null, "Kembalian anda: ");
						
						// TODO: kalau salah satu lbi di klik return keluar ini
//						BorrowItem borrowItems = new BorrowItem();
						
						// Create date
						Date dateNow = Calendar.getInstance().getTime();  
				        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
				        String strDate = dateFormat.format(dateNow);
						
						HashMap<String, String> inputs = new HashMap<String, String>();
						inputs.put("id", borrowItems.getId());
						inputs.put("bookId", borrowItems.getBookId());
						inputs.put("returnTimestamp", strDate);
						
//						if (bth.returnBook(inputs) != null) {
//							
//							JOptionPane.showMessageDialog(null, "Book is successfully returned");
//							
//						}
					}
				});
				
				borrowItemForm.add(lblBookName);
				borrowItemForm.add(lblReturnTimestamp);
				borrowItemForm.add(returnBtn);
				
				listBorrowItemTempPanel.add(borrowItemForm);
			}
			listBorrowItemPanel.add(listBorrowItemTempPanel);
			
			//Waktu click list keluar borrow itemnya
			borrowPanelForm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!listBorrowItemSp.isVisible()) { 
						listBorrowItemSp.setVisible(true); //kalau ga nyala, nyalain
					} else {
						listBorrowItemSp.setVisible(false); //kalau nyala, matiin
					}
				}
			});
		}
		listBorrowPanel.add(listBorrowTempPanel);
		listBorrowItemSp.setVisible(false);
		
		midPanel.add(listBorrowSp);
		midPanel.add(listBorrowItemSp);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		setVisible(true);
	}

}