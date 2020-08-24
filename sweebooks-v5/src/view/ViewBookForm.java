package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BookHandler;
import model.Book;

// This is a singleton class
public class ViewBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ViewBookForm instance = null;

	private ViewBookForm() {
		
		BookHandler bh = new BookHandler();
		List<Book> lb = bh.getAll();
		
		// Create UI
		setTitle("View Book Form");
		setSize(600, 450);
		setBorder(null);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 248);
		add(scrollPane);
		
		JPanel mainPanel = new JPanel();
		scrollPane.setRowHeaderView(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel listBookPanel = new JPanel();
		scrollPane.setViewportView(listBookPanel);
		listBookPanel.setLayout(null);
		
		int i=0;
		for (Book books : lb) {
			i++;
			listBookPanel.add(bookPanelForm(books.getName(), books.getGenreId(), books.getIsbn()));
			listBookPanel.setLayout(new GridLayout(1+i,0,0,10));
		}
		
	}
	
	public static ViewBookForm getInstance() {
		
		if (instance == null) {
			instance = new ViewBookForm();
		}
		
		return instance;
		
	}

	private Component bookPanelForm(String name, String genreId, String isbn) {
		
		JPanel bookPanelForm = new JPanel();
		bookPanelForm.setSize(219, 50);
		bookPanelForm.setLayout(new GridLayout(3, 1, 0, 0));
		bookPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(name);
		bookPanelForm.add(lblName);
		
		JLabel lblGenre = new JLabel(genreId);
		bookPanelForm.add(lblGenre);
		  
		JLabel lblisbn = new JLabel(isbn);
		bookPanelForm.add(lblisbn);
		  
		return bookPanelForm;
		
	}
	
}
