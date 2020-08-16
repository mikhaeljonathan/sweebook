package model;
import java.util.ArrayList;
import java.util.List;

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
		return new ArrayList<BorrowItem>();
	}

}
