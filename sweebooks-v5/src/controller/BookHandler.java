package controller;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JInternalFrame;

import helper.CheckInput;
import model.Book;
import model.Genre;
import view.ManageBookForm;
import view.ViewBookForm;

public class BookHandler {
	
	public BookHandler() {
		
	}

	public JInternalFrame showViewBookForm() {
		
		return ViewBookForm.getInstance();
		
	}
	
	public JInternalFrame showManageBookForm() {
		
		return ManageBookForm.getInstance();
		
	}
	
	public List<Book> getAll(){
		
		return new Book().all();
		
	}
	
	public Book getById(String id) {
		
		return new Book().find(id);
		
	}
	
	public Book getByIsbn(String isbn) {
		return new Book();
	}
	
	public List<Book> getBookByQuantityMoreThanZero(){
		
		return new Book().getBookByQuantityMoreThanZero();
		
	}
	
	public Book insert(HashMap<String, String> inputs) {
		return new Book();
	}
	
	public Book update(HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String id = inputs.get("id");
		String name = inputs.get("name");
		String genreId = inputs.get("genreId");
		String isbn = inputs.get("isbn");
		int quantity = Integer.parseInt(inputs.get("quantity"));
		
		return new Book(id, name, genreId, isbn, quantity).update();
		
	}
	
	public Book restockBook(String isbn) {
		
		if (CheckInput.validateIsbn(isbn)) {
			
			Book b = new Book();
			String id = b.getByIsbn(isbn);
			
			if (id != null) { // ISBN exists in DAO
				
				b = updateBookQuantity(b.find(id));
				
			} else { // ISBN doesn't exist in DAO
				
				// TODO: nanti show form untuk buat buku baru
				b = createNewBook(isbn);
				
			}
			
			return b;
			
		} else {
			
			return null;
			
		}
		
	}
	
	public boolean delete(String id) {
		
		return new Book(id, "dummy name", "dummy genre_id", "dummy isbn", 0).delete();
		
	}
	
	// Increment book quantity
	private Book updateBookQuantity(Book b) {
		
		String id = b.getId();
		String name = b.getName();
		String genreId = b.getGenreId();
		String isbn = b.getIsbn();
		int quantity = b.getQuantity() + 1;
		
		return new Book(id, name, genreId, isbn, quantity).update();
		
	}
	
	private Book createNewBook(String isbn) {
		
		// TODO: ada textfield buat nama buku, combobox buat genre, textfield buat quantity
		
		String name = "nama buku";
		String quantity = "1";
		Genre g = new Genre();
		
		if (CheckInput.validateBookInput(name, quantity)) {
			
			return new Book(UUID.randomUUID().toString(), name, g.getId(), isbn, Integer.parseInt(quantity)).insert();
			
		} else {
			
			return null;
		}
		
	}
	
}
