package controller;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;

import model.Book;
import view.ViewBookForm;

public class BookHandler {
	
	public BookHandler() {
		
	}

	public JInternalFrame showViewBookForm() {
		
		return new ViewBookForm();
		
	}
	
	public JInternalFrame showManageBookForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public List<Book> getAll(){
		
		return new Book().all();
		
	}
	
	public Book getById(String id) {
		
		Book b = new Book();
		return b.find(id);
		
	}
	
	public Book getByIsbn(String isbn) {
		return new Book();
	}
	
	public List<Book> getBookByQuantityMoreThanZero(){
		
		Book b = new Book();
		return b.getBookByQuantityMoreThanZero();
		
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
		String quantity = inputs.get("quantity");
		
		Book b = new Book(id, name, genreId, isbn, Integer.parseInt(quantity));
		
		return b.update();
		
	}
	
	public Book restockBook(String isbn) {
		return new Book();
	}
	
	public boolean delete(String id) {
		return true;
	}
	
}
