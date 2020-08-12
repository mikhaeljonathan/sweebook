package model;
import java.util.ArrayList;
import java.util.List;

public class BorrowItem {
	
	private String id;
	private String bookId;
	private String returnTimestamp;
	
	public BorrowItem() {
		
	}
	
	public BorrowItem(String id, String bookId) {
		
		this.id = id;
		this.bookId = id;
		
	}
	
	public BorrowItem insert() {
		
		// TODO: insert to database
		return this;
		
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
