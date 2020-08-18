package main;

import javax.swing.JFrame;

import controller.BookHandler;
import controller.GenreHandler;

public class PurchasingMainForm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public PurchasingMainForm() {
		
		// TODO: kalau Manage Genre menu
		new GenreHandler().showManageGenreForm();
		
		// TODO: kalau Manage Book menu
		new BookHandler().showManageBookForm();
		
		// TODO: kalau Book View menu
		new BookHandler().showViewBookForm();
		
	}

}
