package model;
import java.util.ArrayList;
import java.util.List;

public class Book {

	private String id;
	private String name;
	private String genreId;
	private String isbn;
	private	int quantity;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Book> all() {
		return new ArrayList<Book>();
	}
	
	public Book find(String id) {
		return new Book();
	}

	public Book insert() {
		return new Book();
	}
	
	public Book update() {
		return new Book();
	}
	
	public boolean delete() {
		return true;
	}
	
	public String getByIsbn(String isbn) {
		return "isbn";
	}
	
	public List<Book> getBookByQuantityMoreThanZero() {
		return new ArrayList<Book>();
	}
	
}
