package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import main.Main;
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
		
		List<Borrow> lb = new ArrayList<Borrow>();
		
		// Retrieve pending borrow list from DAO
		if (isOnlyCurrentMember) {
			
			// Only retrieve current member's borrow list
			String retrieveCurrentBorrowList = "SELECT * FROM borrows " + 
					"INNER JOIN members " + 
					"ON members.user_id = borrows.member_id " + 
					"WHERE members.user_id = '%s' AND borrows.status = 'Pending'";
			retrieveCurrentBorrowList = String.format(retrieveCurrentBorrowList, Main.user_id);
			
			try {
				
				MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveCurrentBorrowList);
				
				while (MySQLAccess.rs.next()) {
					
					lb.add(new Borrow(MySQLAccess.rs.getString("id"),
							MySQLAccess.rs.getString("member_id"),
							MySQLAccess.rs.getString("status"),
							MySQLAccess.rs.getString("borrow_timestamp")));
					
				}
				
				return lb;
				
			} catch (Exception e) {
				
				// Fail to retrieve from DAO
				JOptionPane.showMessageDialog(null, "Database Error");
				return null;
				
			}
			
		} else {
			
			// Administrator : retrieve all borrow list
			String retrieveAllBorrowList = "SELECT * FROM borrows " + 
					"INNER JOIN members " + 
					"ON members.user_id = borrows.member_id " + 
					"WHERE borrows.status = 'Pending'";
			retrieveAllBorrowList = String.format(retrieveAllBorrowList, Main.user_id);
			
			try {
				
				MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveAllBorrowList);
				
				while (MySQLAccess.rs.next()) {
					
					lb.add(new Borrow(MySQLAccess.rs.getString("id"),
							MySQLAccess.rs.getString("member_id"),
							MySQLAccess.rs.getString("status"),
							MySQLAccess.rs.getString("borrow_timestamp")));
					
				}
				
				return lb;
				
			} catch (Exception e) {
				
				// Fail to retrieve from DAO
				JOptionPane.showMessageDialog(null, "Database Error");
				return null;
				
			}
			
		}
		
	}
	
	public List<Borrow> getAcceptStatus(Date date, boolean isOnlyCurrentMember){
		return new ArrayList<Borrow>();
	}
	
	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}

}
