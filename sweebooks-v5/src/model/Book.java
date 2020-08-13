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
	
	
	public Book(String id, String name, String genreId, String isbn, int quantity) {
		
		this.id = id;
		this.name = name;
		this.genreId = genreId;
		this.isbn = isbn;
		this.quantity = quantity;
		
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

	public String getName() {
		
		return name;
		
	}

	public String getGenreId() {
		
		return genreId;
		
	}

	public String getIsbn() {
		
		return isbn;
		
	}

	public int getQuantity() {
		
		return quantity;
		
	}
	
	public void setId(String id) {
		
		this.id = id;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public void setGenreId(String genreId) {
		
		this.genreId = genreId;
		
	}

	public void setIsbn(String isbn) {
		
		this.isbn = isbn;
		
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
		
	}
	
	
	
}
