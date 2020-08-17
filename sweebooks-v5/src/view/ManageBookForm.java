package view;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controller.BookHandler;
import controller.GenreHandler;
import model.Book;
import model.Genre;

public class ManageBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	public ManageBookForm() {
		
		List<Genre> lg = new GenreHandler().getAll();
		// show lg here
		
		BookHandler bh = new BookHandler();
		List<Book> lb = bh.getAll();
		// show lb here
		
		
		// TODO: kalau tombol restock ditekan
		String isbn = "dummy string";
		if (bh.restockBook(isbn) != null) {
			
			JOptionPane.showMessageDialog(null, "Book successfully restocked");
			
		}
		
		// TODO: kalau tombol delete book di setiap list buku ditekan
		// show confirmation dialog dulu baru execute this:
		Book b = new Book();
		if (bh.delete(b.getId())) {
			
			JOptionPane.showMessageDialog(null, "Book successfully deleted");
			
		}
		
	}
}
