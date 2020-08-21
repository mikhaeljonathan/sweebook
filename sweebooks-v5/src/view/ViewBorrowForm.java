package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
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

public class ViewBorrowForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ViewBorrowForm() {
		BorrowTransactionHandler bth = new BorrowTransactionHandler();
		
		List<Borrow> lb = bth.getPendingStatus(SQLGetQuery.getRoleFromUserId(Main.user_id) == "Membership");
		
		// TODO: show lb here
		
		
		// kalau salah satu item borrow di klik akan execute this
		Borrow b = new Borrow();
		List<BorrowItem> lbi = bth.getBookItem(b.getId());
		
		// TODO: show lbi here
		
		
		// TODO: kalau tombol accept diteken keluar ini:
		if (bth.acceptBorrowRequest(b.getId())) {
			
			JOptionPane.showMessageDialog(null, "It's successfully accepted");
			
		}
	}
	
	
}
