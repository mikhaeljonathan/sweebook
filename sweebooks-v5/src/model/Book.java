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
		
		// Retrieve books by ID from DAO
		String findBookById = "SELECT * FROM books "
				+ "WHERE id = '%s'";
		findBookById = String.format(findBookById, id);
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findBookById);
			
			while (MySQLAccess.rs.next()) {
				
				this.id = MySQLAccess.rs.getString("id");
				this.genreId = MySQLAccess.rs.getString("genre_id");
				this.name = MySQLAccess.rs.getString("title");
				this.isbn = MySQLAccess.rs.getString("isbn");
				this.quantity = MySQLAccess.rs.getInt("quantity");
				
			}
			
			return this;
			
		} catch (Exception e) {
			
			// Fail to retrieve from database
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}

	public Book insert() {
		
		// Insert book into DAO
		String insertToBook = "INSERT INTO books "
				+ "VALUE('%s', '%s', '%s', %s, %d)";
		insertToBook = String.format(insertToBook, id, genreId, name, isbn, quantity);
		
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
			
			// Fail to update to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}
	
	public boolean delete() {
		
		// Delete book data in DAO
		String deleteBook = "DELETE FROM books " + 
				"WHERE id = '%s'";
		deleteBook = String.format(deleteBook, id);
		
		try {
			
			MySQLAccess.stmt.execute(deleteBook);
			return true;
			
		} catch (Exception e) {
			
			// Fail to delete from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return false;
			
		}
		
	}
	
	public String getByIsbn(String isbn) {
		
		// Get book id by ISBN from DAO
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
			
			// Fail to retrieve from DAO
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
