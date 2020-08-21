package controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JInternalFrame;

import helper.SQLGetQuery;
import helper.Validation;
import main.Main;
import model.Book;
import model.Borrow;
import model.BorrowItem;
import model.CartStorage;
import view.BorrowBookForm;

public class BorrowBookHandler {
	
	public BorrowBookHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JInternalFrame showBorrowBookForm() {
		
		return new BorrowBookForm();
		
	}
	
	public List<Book> getCart(){
		
		CartStorage cs = CartStorage.getInstance();
		List<Book> lb = new ArrayList<Book>();
		
		// Convert Collection into List<E>
		lb.addAll(cs.getCart());
		return lb;
		
	}
	
	public List<Book> getAvailableBook(){
		
		BookHandler bh = new BookHandler();
		return bh.getBookByQuantityMoreThanZero();
		
	}
	
	public boolean addToCart(Book book) {
		
		if (Validation.isUserEverBorrowThisBook(book)) {
			
			// Add the book to the cart storage
			CartStorage cs = CartStorage.getInstance();
			cs.addCart(book);
			
			Book b = new BookHandler().getById(book.getId());
			updateQuantity(b);
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	public boolean removeCart(Book book) {
		
		CartStorage cs = CartStorage.getInstance();
		cs.removeCart(book);
		
		BookHandler bh = new BookHandler();
		Book b = bh.getById(book.getId());
		
		updateBookQuantity(bh, b);
		
		return true;
	}
	
	public boolean borrowBook() {
		
		if (Validation.isUserCanBorrow()) {
			
			// Generate ID for Borrow object
			String uuid = UUID.randomUUID().toString();
			
			// Create date
			Date date = Calendar.getInstance().getTime();  
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	        String strDate = dateFormat.format(date);
	        
	        // Create borrow object
			Borrow b = new Borrow(uuid, Main.user_id, "Pending", strDate);
			Borrow bNew = b.insert();
			
			// Create borrowItem objects
			List<Book> lb = new ArrayList<Book>();
			lb = getCart();
			
			for (Book book : lb) {
				
				BorrowItem bi = new BorrowItem(bNew.getId(), book.getId(), Validation.dummyTimestamp);
				bi.insert();
				
			}
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	// Decrement Book quantity
	private void updateQuantity(Book b) {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		
		inputs.put("id", b.getId());
		inputs.put("name", b.getName());
		inputs.put("genreId", b.getGenreId());
		inputs.put("isbn", b.getIsbn());
		inputs.put("quantity", Integer.toString(b.getQuantity() - 1));
		
		new BookHandler().update(inputs);
		
	}
	
	// Increment book quantity
	private void updateBookQuantity(BookHandler bh, Book b) {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("id", b.getId());
		inputs.put("name", b.getName());
		inputs.put("genreId", b.getGenreId());
		inputs.put("isbn", b.getIsbn());
		inputs.put("quantity", Integer.toString(b.getQuantity() + 1));
		
		bh.update(inputs);
		
	}

}
