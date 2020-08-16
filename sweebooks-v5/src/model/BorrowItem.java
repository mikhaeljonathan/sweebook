package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.MySQLAccess;

public class BorrowItem {
	
	private String id;
	private String bookId;
	private String returnTimestamp;
	
	public BorrowItem() {
		
	}
	
	public BorrowItem(String id, String bookId, String returnTimestamp) {
		
		this.id = id;
		this.bookId = id;
		this.returnTimestamp = returnTimestamp;
		
	}
	
	public BorrowItem insert() {
		
		// Insert borrowItem into DAO
		String insertToBorrowItem = "INSERT INTO borrow_items "
				+ "VALUE('%s', '%s', '%s')";
		insertToBorrowItem = String.format(insertToBorrowItem, id, bookId, returnTimestamp);
		
		try {
			
			MySQLAccess.stmt.execute(insertToBorrowItem);
			return this;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	public BorrowItem update() {
		return new BorrowItem();
	}
	
	public boolean isBookAlreadyReturn(String id, String bookId) {
		return true;
	}
	
	public List<BorrowItem> getBookItem(String id){
		
		List<BorrowItem> lbi = new ArrayList<BorrowItem>();
		
		// Retrieve book item of single borrow from DAO
		String getBookItems = "SELECT borrow_items.borrow_id, borrow_items.book_id, borrow_items.return_timestamp FROM borrow_items " + 
				"INNER JOIN borrows " + 
				"ON borrows.id = borrow_items.borrow_id";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(getBookItems);
			
			while (MySQLAccess.rs.next()) {
				
				lbi.add(new BorrowItem(MySQLAccess.rs.getString("borrow_items.borrow_id"),
						MySQLAccess.rs.getString("borrow_items.book_id"),
						MySQLAccess.rs.getString("borrow_items.return_timestamp")));
				
			}
			
			return lbi;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
}
