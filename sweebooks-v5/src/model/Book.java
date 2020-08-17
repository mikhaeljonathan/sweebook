package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
		
		List<Book> lb = new ArrayList<Book>();
		
		// Retrieve all book data from DAO
		String findAllBook = "SELECT * FROM books";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findAllBook);
			
			while (MySQLAccess.rs.next()) {
				
				// Add Book object into List<Book>
				lb.add(new Book(MySQLAccess.rs.getString("id"), 
						MySQLAccess.rs.getString("title"),
						MySQLAccess.rs.getString("genre_id"),
						MySQLAccess.rs.getString("isbn"),
						MySQLAccess.rs.getInt("quantity")));
				
			}
			
			return lb;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
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
				genre_id = MySQLAccess.rs.getString("title");
				title = MySQLAccess.rs.getString("genre_id");
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
		
		// Insert book into DAO
		String insertToBook = "INSERT INTO books "
				+ "VALUE('%s', '%s', '%s', %s, %d)";
		insertToBook = String.format(insertToBook, id, name, genreId, isbn, quantity);
		
		try {
			
			MySQLAccess.stmt.execute(insertToBook);
			return this;
			
		} catch (Exception e) {
			
			// Fail to insert to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public Book update() {
		
		// Update books in the DAO
		String updateBook = "UPDATE books " + 
				"SET id = '%s', genre_id = '%s', title = '%s', isbn = '%s', quantity = %d " + 
				"WHERE id = '%s'";
		updateBook = String.format(updateBook, id, genreId, name, isbn, quantity, id);
		
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
		
		// Get book id by isbn
		String retrieveId = "SELECT id FROM books "
				+ "WHERE isbn = '%s'";
		retrieveId = String.format(retrieveId, isbn);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveId);
			
			String isbnRetrieved = "";
			while(MySQLAccess.rs.next()) {
				
				isbnRetrieved = MySQLAccess.rs.getString("id");
				
			}
			
			if (isbnRetrieved.isEmpty()) {
				
				return null;
				
			} else {
				
				return isbnRetrieved;
				
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public List<Book> getBookByQuantityMoreThanZero() {
		
		// Retrieve available books from DAO
		List<Book> availableBooks= new ArrayList<Book>();
		String retrieveAvailableBook = "SELECT * FROM books "
				+ "WHERE quantity > 0";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(retrieveAvailableBook);
			
			while (MySQLAccess.rs.next()) {
				
				// Add Book object into List<Book>
				availableBooks.add(new Book(MySQLAccess.rs.getString("id"), 
						MySQLAccess.rs.getString("title"),
						MySQLAccess.rs.getString("genre_id"),
						MySQLAccess.rs.getString("isbn"),
						MySQLAccess.rs.getInt("quantity")));
				
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
