package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BookHandler;
import helper.SQLGetQuery;
import model.Book;

// This is a singleton class
public class ViewBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ViewBookForm instance = null;

	private ViewBookForm() {
		
		List<Book> lb = new BookHandler().getAll();
		
		// Create UI
		setTitle("View Book Form");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
	
		// Main Panel
		JPanel mainPanel = new JPanel(new GridLayout(lb.size(), 0, 0, 10));
		JScrollPane mainSp = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(mainSp);
		
		// For each book
		for (Book book : lb) {
			
			mainPanel.add(bookPanelForm(book));
			
		}
		
		setVisible(true);
		
	}
	
	public static ViewBookForm getInstance() {
		
		if (instance == null) {
			instance = new ViewBookForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		instance = null;
		
	}

	private Component bookPanelForm(Book book) {
		
		// List Book Panel
		JPanel bookPanelForm = new JPanel(new GridLayout(3, 1, 0, 0));
		bookPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel titleBookName = new JLabel(book.getName(), JLabel.CENTER);
		titleBookName.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bookPanelForm.add(titleBookName);
		
		String genre = SQLGetQuery.getTypeFromGenreId(book.getGenreId());
		JLabel titleBookGenre = new JLabel("Genre        : " + genre);
		titleBookGenre.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bookPanelForm.add(titleBookGenre);
		  
		JLabel titleBookIsbn = new JLabel("ISBN         : " + book.getIsbn());
		titleBookIsbn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bookPanelForm.add(titleBookIsbn);
		  
		return bookPanelForm;
		
	}
	
}
