package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// This is a singleton class
public class CartStorage {
	
	private static CartStorage instance = null;
	private HashMap<String, Book> carts;
	
	private CartStorage() {
		
		carts = new HashMap<String, Book>();
		
	}
	
	public static CartStorage getInstance() {
		
		if (instance == null) {
			instance = new CartStorage();
		}
		
		return instance;
		
	}
	
	public Collection<Book> getCart(){
		
		Collection<Book> cb = new ArrayList<Book>();
		
		// Iterate through HashMap and add the books into the collection
		for (Map.Entry<String, Book> me : carts.entrySet()) {
			
			cb.add(me.getValue());
			
		}
		
		return cb;		
	}
	
	public void addCart(Book book) {
		
		carts.put(book.getId(), book);
		
	}
	
	public void removeCart(Book book) {
		
		carts.remove(book.getId());
		
	}

}
