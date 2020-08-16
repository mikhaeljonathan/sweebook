package view;

import java.util.List;

import javax.swing.JInternalFrame;

import controller.BookHandler;
import model.Book;

public class ViewBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ViewBookForm() {
		
		BookHandler bh = new BookHandler();
		List<Book> lb = bh.getAll();
		
		// TODO: show lb disini
		
		
	}
	
	
}
