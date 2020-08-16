package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controller.BorrowBookHandler;
import model.Book;

public class BorrowBookForm extends JInternalFrame{

	public BorrowBookForm() {
		
		// TODO: create UI

		
		BorrowBookHandler bbh = new BorrowBookHandler();
		
		// Get list all of the available books to be borrowed
		List<Book> listOfAvailableBooks = new ArrayList<Book>();
		listOfAvailableBooks = bbh.getAvailableBook();
		// TODO: tampilin semua List<Book> nya
		
		// TODO: kalau tombol cart diteken execute ini:
		
		// Get list all of the books in the cart
		List<Book> cartList = new ArrayList<Book>();
		cartList = bbh.getCart();
		// TODO: tampilin semua List<Book> nya
		
		// TODO: kalau salah satu buku di listOfAvailableBooks diteken keluar tombol add to cart dan kalau diteken execute ini
		Book b = new Book();
		
		if (bbh.addToCart(b)) {
			// TODO: keluarin success message
		} else {
			// TODO: keluarin error message
		}
		
		
		// TODO: kalau cartnya keluar, bakal mucnul juga borrow button, terus kalo diklik execute this:
		if (bbh.borrowBook()) {
			// TODO: keluarin success message
			
		} else {
			// TODO: keluarin error message
		}
				
		
		// TODO: kalau salah satu item cart di klik, bakal muncul tombol remove cart
		// then keluarin confirmation dialog, klao yes then execute this:
		if (bbh.removeCart(b)) {
			// TODO: keluarin success message
		} else {
			// TODO: keluarin error message
		}
		
		
	}
}
