package controller;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
		
		return new Book().getByIsbn(isbn);
		
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
			
			Book b = new Book().getByIsbn(isbn);
			
			if (b != null) { // ISBN exists in DAO
				
				b = updateBookQuantity(b.find(b.getId()));
				
			} else { // ISBN doesn't exist in DAO
				
				b = createNewBook(isbn);
				
			}
			
			return b;
			
		} else {
			
			return null;
			
		}
		
	}
	
	public boolean delete(String id) {
		
		return new Book(id, "", "", "", 0).delete();
		
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
		
		JPanel addBookPanel = new JPanel(new GridLayout(0, 1));
		
		JTextField nameTextField = new JTextField();
		addBookPanel.add(new JLabel("Book Name :"));
		addBookPanel.add(nameTextField);
		
		JComboBox<String> genreComboBox = new JComboBox<String>();
		addBookPanel.add(new JLabel("Genre :"));
		List<Genre> lg = new GenreHandler().getAll();
		
		// For each genre
		for (Genre genre : lg) 
		{
			genreComboBox.addItem(genre.getType());
			
		}
		addBookPanel.add(genreComboBox);
		
		JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		addBookPanel.add(new JLabel("Quantity :"));
		addBookPanel.add(quantitySpinner);
		
		
		while (true) {
			
			int option = JOptionPane.showConfirmDialog(null, addBookPanel, "Add Book Form", JOptionPane.DEFAULT_OPTION);
			
			if (option == JOptionPane.YES_OPTION) {
				
				String name = nameTextField.getText();
				String quantity = quantitySpinner.getValue().toString();
				Genre g = new Genre().getByType(genreComboBox.getSelectedItem().toString());
				
				if (CheckInput.validateBookInput(name, quantity)) {
					
					return new Book(UUID.randomUUID().toString(), name, g.getId(), isbn, Integer.parseInt(quantity)).insert();
					
				}
				
			} else {
				
				return null;
				
			}
			
		}
		
	}
	
}
