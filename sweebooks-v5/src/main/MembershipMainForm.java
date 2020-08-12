package main;

import java.util.ArrayList;
import java.util.List;

import controller.BorrowBookHandler;
import model.Book;

public class MembershipMainForm {
	
	public MembershipMainForm() {
		
		// TODO: begitu membership klik borrow book menu langsung execute ini:
		BorrowBookHandler bbh = new BorrowBookHandler();
		bbh.showBorrowBookForm();
		
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
		
	}
	
}
