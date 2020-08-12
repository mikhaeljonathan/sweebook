package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Borrow {
	
	private String id;
	private String memberId;
	private String status;
	private String borrowTimestamp;

	public Borrow() {
		// TODO Auto-generated constructor stub
	}
	
	public Borrow find(String id) {
		return new Borrow();
	}
	
	public Borrow insert() {
		
		// TODO: insert ke database
		
		
		return this;
		
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
