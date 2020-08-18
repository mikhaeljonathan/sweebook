package controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import Helper.SQLGetQuery;
import Helper.Validation;
import main.Main;
import model.Book;
import model.Borrow;
import model.BorrowItem;
import view.ViewBorrowForm;
import view.ViewBorrowHistoryForm;

public class BorrowTransactionHandler {
	
	public BorrowTransactionHandler() {
		
	}
	
	public JInternalFrame showBorrowForm() {
		
		return new ViewBorrowForm();
	}
	
	public JInternalFrame showBorrowHistoryForm() {
		
		return new ViewBorrowHistoryForm();
		
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
		
		if (!SQLGetQuery.getReturnTimestampFromIdAndBookId(inputs.get("id"), inputs.get("bookId")).equals(Validation.dummyTimestamp)) {
			
			BorrowItem bi = new BorrowItem(
					inputs.get("id"),
					inputs.get("bookId"),
					inputs.get("returnTimestamp"));
			
			bi =  bi.update();
			
			Book b = new BookHandler().getById(bi.getBookId());
			updateQuantity(b);
			
			int fine = calculateFine(bi);
			if (fine > 0) {
				
				while (true) {
					
					try {
						
						String fineInputtedString = JOptionPane.showInputDialog("Fine: " + fine + "\nEnter your money: ");
						int fineInputted = Integer.parseInt(fineInputtedString);
						
						if (fineInputted >= fine) {
							
							JOptionPane.showMessageDialog(null, "Book is successfully returned\n"
									+ "Change: " + (fineInputted - fine));
							break;
							
						} else {
							
							JOptionPane.showMessageDialog(null, "Money inputted is less than the fine");
							
						}
						
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "Input number!");
						
					}
					
				}
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Book is successfully returned");
				
			}
			
			return bi;
			
		} else {
			
			JOptionPane.showMessageDialog(null, "The book is already returned");
			return null;
			
		}
		
	}
	
	// Increment Book quantity
	private void updateQuantity(Book b) {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		
		inputs.put("id", b.getId());
		inputs.put("name", b.getName());
		inputs.put("genreId", b.getGenreId());
		inputs.put("isbn", b.getIsbn());
		inputs.put("quantity", Integer.toString(b.getQuantity() + 1));
		
		new BookHandler().update(inputs);
		
	}
	
	private int calculateFine(BorrowItem bi) {
		
		Date returnDate = new Date();
		Date borrowDate = new Date();
		
		try {
			
			returnDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(bi.getReturnTimestamp());
			borrowDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(
					new Borrow().find(bi.getId()).getBorrowTimestamp());
			
		} catch (Exception e) {
			
			
		}
		
		long returnDateInMillisecond = returnDate.getTime();
		long borrowDateInMillisecond = borrowDate.getTime();
		
		long diff = returnDateInMillisecond - borrowDateInMillisecond;
		diff = diff - Validation.twoWeeksInMillisecond;
		
		if (diff > 0) {

			long howManyDays = diff / Validation.twoWeeksInMillisecond;
			return ((int)howManyDays * 1000);
			
		} else {
			
			return 0;
			
		}
		
	}
	
}
