package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.BookHandler;
import controller.GenreHandler;
import helper.SQLGetQuery;
import model.Book;
import model.Genre;

// This is a singleton class
public class ManageBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ManageBookForm instance = null;
	
	private BookHandler bh;
	private JPanel listBookTempPanel;
	
	public ManageBookForm() {
	
		bh = new BookHandler();
		
		// Create UI
		setTitle("Manage Book Form");
		setSize(800,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170,  10);
		setResizable(false);
		setLayout(new BorderLayout(5, 5));
		
		// Top Panel
		JPanel topPanel = new JPanel(new BorderLayout(5, 5));
		
		// ISBN Label
		JLabel isbnTitle = new JLabel("ISBN : ");
		
		// ISBN TextField
		JTextField isbnField = new JTextField();
		
		// RestockBook Button
		JButton restockBookBtn = new JButton("Restock");
		restockBookBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String isbn = isbnField.getText();
				if (bh.restockBook(isbn) != null) {
					
					JOptionPane.showMessageDialog(null, "Book successfully restocked");
					refreshListBookPanel();
					
				}
				
			}
			
		});
		
		topPanel.add(isbnTitle, BorderLayout.WEST);
		topPanel.add(isbnField, BorderLayout.CENTER);
		topPanel.add(restockBookBtn, BorderLayout.EAST);
		
		
		// Mid Panel
		JPanel midPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		
		// ListBook Panel
		JPanel listBookPanel = new JPanel(new BorderLayout(5, 5));
		JScrollPane listBookSp = new JScrollPane(listBookPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	

		// Retrieve all the books
		List<Book> lb = bh.getAll();
		
		// Panel for retrieving list Book
		listBookTempPanel = new JPanel(new GridLayout(lb.size(), 1, 5, 5));
		
		// For each book
		for (Book book : lb) {
			
			listBookTempPanel.add(bookPanelInfo(book));
			
		}
		
		listBookPanel.add(listBookTempPanel);
		
		// List Genre Panel
		JPanel listGenrePanel = new JPanel(new BorderLayout(5, 5));
		JScrollPane listGenreSp = new JScrollPane(listGenrePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Retrieve all genres
		List<Genre> lg = new GenreHandler().getAll();
		
		// Panel for retrieving list genres
		JPanel listGenreTempPanel = new JPanel(new GridLayout(lg.size() + 1, 1, 5, 5));
		
		// For each genre
		for (Genre genres : lg) {
			
			// Genre Panel
			JPanel genrePanelForm = new JPanel(new GridLayout(1, 1, 0, 0));
			genrePanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
			// genre Label
			JLabel lblGenre = new JLabel(genres.getType());
			lblGenre.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
			
			genrePanelForm.add(lblGenre);
			
			listGenreTempPanel.add(genrePanelForm);
			
		}
		
		listGenrePanel.add(listGenreTempPanel, BorderLayout.CENTER);

		midPanel.add(listGenreSp);
		midPanel.add(listBookSp);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);

		setVisible(true);
		
	}
	
	public static ManageBookForm getInstance() {
		
		if (instance == null) {
			instance = new ManageBookForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		instance = null;
		
	}
	
	private JPanel bookPanelInfo(Book book) {
		
		// Book panel
		JPanel bookPanelForm = new JPanel(new GridLayout(5, 1, 0, 0));
		bookPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// bookName Label
		JLabel lblBookName = new JLabel("Book Name: " + book.getName());
		
		// genre Label
		JLabel lblBookGenre = new JLabel("Genre: " + SQLGetQuery.getTypeFromGenreId(book.getGenreId()));
		
		// ISBN Label
		JLabel lblIsbn = new JLabel("ISBN: " + book.getIsbn());
		
		// quantity Label
		JLabel lblQuantity = new JLabel("Quantity: " + book.getQuantity());
		
		// delete Button
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int clickConfirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete this book ?");
				
				if (clickConfirm == JOptionPane.YES_OPTION) {
					
					if (bh.delete(book.getId())) {
						
						JOptionPane.showMessageDialog(null, "Book successfully deleted");
						refreshListBookPanel();
						
					}
					
				}
				
			}
			
		});;
		
		bookPanelForm.add(lblBookName);
		bookPanelForm.add(lblBookGenre);
		bookPanelForm.add(lblIsbn);
		bookPanelForm.add(lblQuantity);
		bookPanelForm.add(deleteBtn);
		
		return bookPanelForm;
		
	}
	
	private void refreshListBookPanel() {
		
		listBookTempPanel.setVisible(false);
		listBookTempPanel.removeAll();
		
		// Retrieve all the books
		List<Book> lb = bh.getAll();
		
		listBookTempPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
		
		// For each book
		for (Book book : lb) {
		
			listBookTempPanel.add(bookPanelInfo(book));
			
		}
		
		listBookTempPanel.setVisible(true);
		
	}
	
}