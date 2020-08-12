package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;

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
		// Convert Collection into List
		lb.addAll(cs.getCart());
		return lb;
		
	}
	
	public List<Book> getAvailableBook(){
		
		BookHandler bh = new BookHandler();
		return bh.getBookByQuantityMoreThanZero();
		
	}
	
	public boolean addToCart(Book book) {
		
		if (validate()) { //TODO: validate apa ni?
			
			// Add the book to the cart storage
			CartStorage cs = CartStorage.getInstance();
			cs.addCart(book);
			
			BookHandler bh = new BookHandler();
			Book b = bh.getById(book.getId());
			
			updateQuantity(); //TODO: gatau suruh ngapain
			
			HashMap<String, String> inputs = new HashMap<String, String>();
			bh.update(inputs);
			
			return true;
			
		} else {
			return false;
		}
		
	}
	
	public boolean removeCart(Book book) {
		return true;
	}
	
	public boolean borrowBook() {
		
		if (validate()) {
			
			Borrow b = new Borrow();
			Borrow bNew = new Borrow();
			bNew = b.insert();
			
			List<Book> lb = new ArrayList<Book>();
			lb = getCart();
			
			for (Book book : lb) {
				
				BorrowItem bi = new BorrowItem(bNew.getId(), book.getId());
				bi.insert();
				
			}
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	private boolean validate() {
		return true;
	}
	
	private void updateQuantity() {
		
	}

}
