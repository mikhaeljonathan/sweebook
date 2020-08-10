import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;

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
		return new ArrayList<Borrow>();
	}
	
	public List<Borrow> getAcceptStatus(Date date){
		return new ArrayList<Borrow>();
	}
	
	public List<BorrowItem> getBookItem(String id){
		return new ArrayList<BorrowItem>();
	}
	
	public boolean acceptBorrowRequest(String id) {
		return true;
	}
	
	public BorrowItem returnBook(HashMap<String, String> inputs) {
		return new BorrowItem();
	}
}
