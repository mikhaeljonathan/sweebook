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

public class ViewBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ViewBookForm() {
		
		List<Book> lb = new BookHandler().getAll();
		
		// Create UI
		setTitle("View Book Form");
		setSize(700, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
		
		// Main Panel
		JPanel mainPanel = new JPanel();
		JScrollPane mainSp = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(mainSp);
		
		// Add List<Book> to MainPanel
		mainPanel.setLayout(new GridLayout(lb.size(), 0, 0, 10));
		
		for (Book books : lb) {
			
			mainPanel.add(bookPanelForm(books.getName(), books.getGenreId(), books.getIsbn()));
			
		}
		
		setVisible(true);
		
	}

	private Component bookPanelForm(String name, String genreId, String isbn) {
		
		// List Book Panel
		JPanel bookPanelForm = new JPanel();
		bookPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		bookPanelForm.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel titleBookName = new JLabel(name, JLabel.CENTER);
		titleBookName.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bookPanelForm.add(titleBookName);
		
		String genre = SQLGetQuery.getTypeFromGenreId(genreId);
		JLabel titleBookGenre = new JLabel("Genre        : " + genre);
		titleBookGenre.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bookPanelForm.add(titleBookGenre);
		  
		JLabel titleBookIsbn = new JLabel("ISBN         : " + isbn);
		titleBookIsbn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bookPanelForm.add(titleBookIsbn);
		  
		return bookPanelForm;
		
	}
	
}
