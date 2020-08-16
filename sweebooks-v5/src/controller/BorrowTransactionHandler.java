package controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import helper.SQLGetQuery;
import main.Main;
import model.Borrow;
import model.BorrowItem;

public class BorrowTransactionHandler {
	
	public BorrowTransactionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JInternalFrame showBorrowForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public JInternalFrame showBorrowHistoryForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public List<Borrow> getPendingStatus(boolean isOnlyCurrentMember){
		
		return new Borrow().getPendingStatus(isOnlyCurrentMember);
		
	}
	
	public List<Borrow> getAcceptStatus(Date date){
		
		return new Borrow().getAcceptStatus(date, SQLGetQuery.getRoleFromUserId(Main.user_id) == "Membership");
		
	}
	
	public List<BorrowItem> getBookItem(String id){
		
		return new BorrowItem().getBookItem(id);
		
	}
	
	public boolean acceptBorrowRequest(String id) {
		
		Borrow b = new Borrow().find(id);
		
		if (b.getStatus().equals("Accepted")) {
			
			JOptionPane.showMessageDialog(null, "It's already accepted");
			return false;
			
		} else {
			
			b.setStatus("Accepted");
			
			if (b.update() != null) {
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		}
		
	}
	
	public BorrowItem returnBook(HashMap<String, String> inputs) {
		return new BorrowItem();
	}
}
