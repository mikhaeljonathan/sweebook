package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.MySQLAccess;

public class Borrow {
	
	private String id;
	private String memberId;
	private String status;
	private String borrowTimestamp;

	public Borrow() {
		
	}
	
	public Borrow(String id, String memberId, String status, String borrowTimestamp) {
	
		this.id = id;
		this.memberId = memberId;
		this.status = status;
		this.borrowTimestamp = borrowTimestamp;
		
	}
	
	public Borrow find(String id) {
		return new Borrow();
	}
	
	public Borrow insert() {
		
		// Insert borrow into DAO
		String insertToBorrow = "INSERT INTO borrows "
				+ "VALUE('%s', '%s', '%s', '%s')";
		insertToBorrow = String.format(insertToBorrow, id, memberId, status, borrowTimestamp);
		
		try {
			
			MySQLAccess.stmt.execute(insertToBorrow);
			return this;
			
		} catch (Exception e) {
			
			// Fail to insert to DAO
			return null;
			
		}
				
	}
	
	public Borrow update() {
		return new Borrow();
	}
	
	public boolean isBookStillBorrowing(String userId, String bookId) {
		return true;
	}
	
	public int getCountBookStillBorrowing(String userId) {
		return 0;
	}
	
	public List<Borrow> getPendingStatus(boolean isOnlyCurrentMember){
		return new ArrayList<Borrow>();
	}
	
	public List<Borrow> getAcceptStatus(Date date, boolean isOnlyCurrentMember){
		return new ArrayList<Borrow>();
	}
	
	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}

}
