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
		
		// TODO: retrieve data buku berdasarkan id di database trs return booknya dan masukin ke atribut
		// this.id = id <- example
		return this;
		
	}

	public Book insert() {
		return new Book();
	}
	
	public Book update() {
		
		// TODO: gaatau suruh update apaan
		return this;
		
	}
	
	public boolean delete() {
		return true;
	}
	
	public String getByIsbn(String isbn) {
		return "isbn";
	}
	
	public List<Book> getBookByQuantityMoreThanZero() {
		
		// TODO: minta ke database list buku2 yang quantity nya lbh besar dr 0 terus return
		return new ArrayList<Book>();
	}
	
	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}
	
}
