import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

public class BorrowBookHandler {
	
	public BorrowBookHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JInternalFrame showBorrowBookForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public List<Book> getCart(){
		return new ArrayList<Book>();
	}
	
	public List<Book> getAvailableBook(){
		return new ArrayList<Book>();
	}
	
	public boolean addToCart(Book book) {
		return true;
	}
	
	public boolean removeCart(Book book) {
		return true;
	}
	
	public boolean borrowBook() {
		return true;
	}

}
