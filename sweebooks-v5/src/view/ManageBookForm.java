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
import model.Book;
import model.Genre;

public class ManageBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	public ManageBookForm() {
	
		BookHandler bh = new BookHandler();
		
		//create UI
		setTitle("Manage Book Form");
		setSize(800,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setClosable(true);
		setLocation(170,  10);
		setResizable(false);
		setLayout(new BorderLayout(5, 5));
		
		//Panel Atas
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(5, 5));
		
		//ISBN Label
		JLabel isbnTitle = new JLabel("ISBN : ");
		
		//ISBN TextField
		JTextField isbnField = new JTextField();
		//RestockBook Button
		
		JButton restockBookBtn = new JButton("Restock");
		restockBookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: kalau tombol restock ditekan
				String isbn = "dummy string";
				if (bh.restockBook(isbn) != null) {
					
					JOptionPane.showMessageDialog(null, "Book successfully restocked");
					
				}
			}
		});
		
		topPanel.add(isbnTitle, BorderLayout.WEST);
		topPanel.add(isbnField, BorderLayout.CENTER);
		topPanel.add(restockBookBtn, BorderLayout.EAST);
		
		
		//Panel Tengah
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		//ListBook Panel
		JPanel listBookPanel = new JPanel();
		listBookPanel.setLayout(new BorderLayout(5, 5));
		JScrollPane listBookSp = new JScrollPane(listBookPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		
		//Panel untuk tampung list Book
		JPanel listBookTempPanel = new JPanel();
		
		// show lb here
		List<Book> lb = bh.getAll();
		listBookTempPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
		for (Book books : lb) {
			//Panel Buku
			JPanel bookPanelForm = new JPanel();
			bookPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			bookPanelForm.setLayout(new GridLayout(5, 1, 0, 0));
			
			//bookName Label
			JLabel lblBookName = new JLabel("Book Name: " + books.getName());
			
			//genre Label
			JLabel lblBookGenre = new JLabel("Genre: " + books.getGenreId());
			
			//isbn Label
			JLabel lblIsbn = new JLabel("ISBN: " + books.getIsbn());
			
			//quantity Label
			JLabel lblQuantity = new JLabel("Quantity: " + books.getQuantity());
			
			//delete Button
			JButton deleteBtn = new JButton("Delete");
			deleteBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO: kalau tombol delete book di setiap list buku ditekan
					int clickConfirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete this book ?");
					if(clickConfirm == 0) {
						// show confirmation dialog dulu baru execute this:
						Book b = new Book();
						if (bh.delete(b.getId())) {
							
							JOptionPane.showMessageDialog(null, "Book successfully deleted");
							
						}
					}
				}
			});;
			
			bookPanelForm.add(lblBookName);
			bookPanelForm.add(lblBookGenre);
			bookPanelForm.add(lblIsbn);
			bookPanelForm.add(lblQuantity);
			bookPanelForm.add(deleteBtn);
			
			listBookTempPanel.add(bookPanelForm);
		}
		listBookPanel.add(listBookTempPanel);
		
		//List Genre Panel
		JPanel listGenrePanel = new JPanel();
		listGenrePanel.setLayout(new BorderLayout(5, 5));
		JScrollPane listGenreSp = new JScrollPane(listGenrePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//List Genre Temp
		JPanel listGenreTempPanel = new JPanel();
		
		List<Genre> lg = new GenreHandler().getAll();
		listGenreTempPanel.setLayout(new GridLayout(lb.size(), 1, 5, 5));
//		listGenreTempPanel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (!listBookSp.isVisible()) { 
//					listBookSp.setVisible(true); //kalau ga nyala, nyalain
//				} else {
//					listBookSp.setVisible(false); //kalau nyala, matiin
//				}
//			}
//		});
		// show lg here
		for (Genre genres : lg) {
			//Genre Panel
			JPanel genrePanelForm = new JPanel();
			genrePanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			genrePanelForm.setLayout(new GridLayout(1, 1, 0, 0));
			
			//genre Label
			JLabel lblGenre = new JLabel(genres.getType());
			lblGenre.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
			
			genrePanelForm.add(lblGenre);
			
			listGenreTempPanel.add(genrePanelForm);
		}
		listGenrePanel.add(listGenreTempPanel, BorderLayout.CENTER);
//		listBookSp.setVisible(false);

		midPanel.add(listGenreSp);
		midPanel.add(listBookSp);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER );
		setVisible(true);
	}
}
