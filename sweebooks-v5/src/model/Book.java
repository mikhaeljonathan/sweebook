package model;
import java.util.ArrayList;
import java.util.List;

import main.MySQLAccess;

public class Book {

	private String id;
	private String name;
	private String genreId;
	private String isbn;
	private	int quantity;
	
	public Book() {
		
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
		
		String findBookById = "SELECT * FROM books "
				+ "WHERE id = '%s'";
		findBookById = String.format(findBookById, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findBookById);
			
			String id2 = "";
			String genre_id = "";
			String title = "";
			String isbn = "";
			int quantity = 0;
			
			while (MySQLAccess.rs.next()) {
				
				id2 = MySQLAccess.rs.getString("id");
				genre_id = MySQLAccess.rs.getString("genre_id");
				title = MySQLAccess.rs.getString("title");
				isbn = MySQLAccess.rs.getString("isbn");
				quantity = MySQLAccess.rs.getInt("quantity");
				
			}
			
			this.id = id2;
			this.genreId = genre_id;
			this.name = title;
			this.isbn = isbn;
			this.quantity = quantity;
			
			return this;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}

	public Book insert() {
		return new Book();
	}
	
	public Book update() {
		
		// Update books in the DAO
		String updateBook = "UPDATE books " + 
				"SET id = '%s', genre_id = '%s', title = '%s', isbn = '%s', quantity = %d " + 
				"WHERE id = '%s'";
		updateBook = String.format(updateBook, id, genreId, name, isbn, quantity);
		
		try {
			
			MySQLAccess.stmt.execute(updateBook);
			return this;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	public boolean delete() {
		return true;
	}
	
	public String getByIsbn(String isbn) {
		return "isbn";
	}
	
	public List<Book> getBookByQuantityMoreThanZero() {
		
		// Retrieve available books from DAO
		List<Book> availableBooks= new ArrayList<Book>();
		String retrieveAvailableBook = "SELECT * FROM books "
				+ "WHERE quantity > 0";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveAvailableBook);
			
			while (MySQLAccess.rs.next()) {
				
				// Retrieve each attribute
				String id = MySQLAccess.rs.getString("id");
				String genre_id = MySQLAccess.rs.getString("genre_id");
				String title = MySQLAccess.rs.getString("title");
				String isbn = MySQLAccess.rs.getString("isbn");
				int quantity = MySQLAccess.rs.getInt("quantity");
				
				// Add Book object into List<Book>
				Book b = new Book(id, genre_id, title, isbn, quantity);
				availableBooks.add(b);
				
			}
			
			return availableBooks;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
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
