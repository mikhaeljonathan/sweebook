package model;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import helper.SQLGetQuery;
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
		
		// Retrieve book data by id from DAO
		String bookById = "SELECT * FROM borrows " + 
				"WHERE id = '%s'";
		bookById = String.format(bookById, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(bookById);
			
			while (MySQLAccess.rs.next()) {
				
				this.id = MySQLAccess.rs.getString("id");
				this.memberId = MySQLAccess.rs.getString("member_id");
				this.status = MySQLAccess.rs.getString("status");
				this.borrowTimestamp = MySQLAccess.rs.getString("borrow_timestamp");
				
			}
			
			return this;
			
		} catch (Exception e){
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
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
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
				
	}
	
	public Borrow update() {
		
		// Update borrow to DAO
		String updateBorrow = "UPDATE borrows " + 
				"SET id = '%s', member_id = '%s', status = '%s', borrow_timestamp = '%s' " + 
				"WHERE id = '%s'";
		updateBorrow = String.format(updateBorrow, id, memberId, status, borrowTimestamp, id);
		
		try {
			
			MySQLAccess.stmt.execute(updateBorrow);
			return this;
			
		} catch (Exception e) {
			
			// Fail to update to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public boolean isBookStillBorrowing(String userId, String bookId) {
		
		// Retrieve the book title from any borrow items object of the corresponding userID from DAO
		String isThereAnyData = "SELECT books.title FROM books " + 
				"INNER JOIN borrow_items " + 
				"ON borrow_items.book_id = books.id " + 
				"INNER JOIN borrows " + 
				"ON borrows.id = borrow_items.borrow_id " + 
				"INNER JOIN members " + 
				"ON members.user_id = borrows.member_id " + 
				"WHERE members.user_id = '%s' AND books.id = '%s' AND borrow_items.return_timestamp = '1990-01-01 12:00:00'";
		isThereAnyData = String.format(isThereAnyData, userId, bookId);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(isThereAnyData);
			
			String title = "";
			
			while (MySQLAccess.rs.next()) {
				
				title = MySQLAccess.rs.getString("books.title");
				
			}
			
			if (title.isEmpty()) {
				
				return false;
				
			} else {
				
				return true;
				
			}
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public int getCountBookStillBorrowing(String userId) {
		
		int cartSize = CartStorage.getInstance().getCart().size();
		return cartSize + SQLGetQuery.countBooksBorrowedByUser(userId);
		
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
				JOptionPane.showMessageDialog(null, "Database error");
				return null;
				
			}
			
		} else {
			
			// Administrator : retrieve all borrow list
			String retrieveAllBorrowList = "SELECT * FROM borrows " + 
					"WHERE borrows.status = 'Pending'";
			
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
				JOptionPane.showMessageDialog(null, "Database error");
				return null;
				
			}
			
		}
		
	}
	
	public List<Borrow> getAcceptStatus(Date date, boolean isOnlyCurrentMember){
		
		List<Borrow> lb = new ArrayList<Borrow>();
		
		// Retrieve accepted borrow list from DAO
		if (isOnlyCurrentMember) {
			
			// Only retrieve current member's borrow list
			String retrieveCurrentBorrowList = "";
			
			if (date == null) { // No specific month and year
				
				retrieveCurrentBorrowList = "SELECT * FROM borrows " + 
						"INNER JOIN members " + 
						"ON members.user_id = borrows.member_id " + 
						"WHERE members.user_id = '%s' AND borrows.status = 'Accepted'";
				retrieveCurrentBorrowList = String.format(retrieveCurrentBorrowList, Main.user_id);
				
			} else { // Specific month and year
				
				int month = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
				int year = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
				
				retrieveCurrentBorrowList = "SELECT * FROM borrows " + 
						"INNER JOIN members " + 
						"ON members.user_id = borrows.member_id " + 
						"WHERE members.user_id = '%s' AND borrows.status = 'Accepted' AND " +
						"MONTH(borrows.borrow_timestamp) = %d AND YEAR(borrows.borrow_timestamp) = %d";
				retrieveCurrentBorrowList = String.format(retrieveCurrentBorrowList, Main.user_id, month, year);
				
			}
			
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
			String retrieveAllBorrowList = "";
			
			if (date == null) { // No specific month and year
				
				retrieveAllBorrowList = "SELECT * FROM borrows " +
						"WHERE borrows.status = 'Accepted'";
				
			} else { // Specific month and year
			
				retrieveAllBorrowList = "SELECT * FROM borrows " + 
						"WHERE borrows.status = 'Accepted' AND MONTH(borrows.borrow_timestamp) = %d AND YEAR(borrows.borrow_timestamp) = %d";
				
				retrieveAllBorrowList = String.format(retrieveAllBorrowList, 
						date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue(),
						date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear());
				
			}
			
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
	
	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}
	
	public String getMemberId() {
		
		return memberId;
		
	}
	
	public String getBorrowTimestamp() {
		
		return borrowTimestamp;
		
	}
	
	public String getStatus() {
		
		return status;
		
	}

	public void setMemberId(String memberId) {
		
		this.memberId = memberId;
		
	}

	public void setBorrowTimestamp(String borrowTimestamp) {
		
		this.borrowTimestamp = borrowTimestamp;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}

	public void setStatus(String status) {
		
		this.status = status;
		
	}

}
