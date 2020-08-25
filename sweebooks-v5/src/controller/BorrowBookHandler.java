package controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import helper.Validation;
import main.Main;
import model.Book;
import model.Borrow;
import model.BorrowItem;
import model.CartStorage;
import view.BorrowBookForm;

public class BorrowBookHandler {
	
	public BorrowBookHandler() {
		
	}
	
	public JInternalFrame showBorrowBookForm() {
		
		return BorrowBookForm.getInstance();
		
	}
	
	public void unshowBorrowBookForm() {
		
		BorrowBookForm.getInstance().destroy();
		
	}
	
	public List<Book> getCart(){
		
		List<Book> lb = new ArrayList<Book>();
		
		// Convert Collection into List<E>
		lb.addAll(CartStorage.getInstance().getCart());
		return lb;
		
	}
	
	public List<Book> getAvailableBook(){
		
		return new BookHandler().getBookByQuantityMoreThanZero();
		
	}
	
	public boolean addToCart(Book book) {
		
		if (new Borrow().isBookStillBorrowing(Main.user_id, book.getId())) {
			
			JOptionPane.showMessageDialog(null, "You have borrowed this book");
			return false;
			
		} 
		
		if (Validation.isBookIsInTheCart(book)) {
			
			JOptionPane.showMessageDialog(null, "The book is already in the cart");
			return false;
			
		}
		
		// Add the book to the cart storage
		CartStorage.getInstance().addCart(book);
		
		updateQuantity(new BookHandler().getById(book.getId()));
		
		return true;
		
	}
	
	public boolean removeCart(Book book) {
		
		CartStorage.getInstance().removeCart(book);
		
		updateBookQuantity(new BookHandler().getById(book.getId()));
		
		return true;
		
	}
	
	public boolean removeAllCart() {
		
		List<Book> lb = getCart();
		
		for (Book b : lb) {
			
			CartStorage.getInstance().removeCart(b);
			
		}
		
		return true;
		
	}
	
	public boolean borrowBook() {
		
		if (new Borrow().getCountBookStillBorrowing(Main.user_id) <= 10) {
			
			// Generate ID for Borrow object
			String uuid = UUID.randomUUID().toString();
			
			// Create date
	        String strDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
	        
	        // Create borrow object
			Borrow b = new Borrow(uuid, Main.user_id, "Pending", strDate).insert();
			
			// Create borrowItem objects
			List<Book> lb = getCart();
			
			for (Book book : lb) {
				
				// Hasn't returned yet so the return date is dummy
				new BorrowItem(b.getId(), book.getId(), Validation.dummyTimestamp).insert();
				
			}
			
			return true;
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Can't borrow more than 10 books!");
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
	private void updateBookQuantity(Book b) {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("id", b.getId());
		inputs.put("name", b.getName());
		inputs.put("genreId", b.getGenreId());
		inputs.put("isbn", b.getIsbn());
		inputs.put("quantity", Integer.toString(b.getQuantity() + 1));
		
		new BookHandler().update(inputs);
		
	}

}
