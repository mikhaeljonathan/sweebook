package main;

import java.util.ArrayList;
import java.util.List;

import controller.BorrowBookHandler;
import controller.MemberHandler;
import model.Book;

import view.ViewBorrowForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MembershipMainForm extends JFrame{
 
	 public MembershipMainForm() {
		 setTitle("Membership Main Form");
		 getContentPane().setLayout(null);
		 setSize(900, 500);
		 setLocationRelativeTo(null);
	  
		 JButton btnBorrowBook = new JButton("Borrow Book");
		 btnBorrowBook.addMouseListener(new MouseAdapter() {
		 @Override
		  public void mouseClicked(MouseEvent e) {
			 ViewBorrowForm borrowForm = new ViewBorrowForm();
		     borrowForm.setResizable(false);
//   		 borrowForm.setBounds(174, 11, 500, 261);
		     borrowForm.setLocation(174, 11);
		     getContentPane().add(borrowForm);
		     borrowForm.getContentPane().setLayout(null);
		     borrowForm.setVisible(true);
		  }
		 });
		 
		 btnBorrowBook.setBounds(10, 11, 140, 35);
		 getContentPane().add(btnBorrowBook);
		  
		 JButton btnBookView = new JButton("Book View");
		 btnBookView.addMouseListener(new MouseAdapter() {
		 @Override
		  public void mouseClicked(MouseEvent e) {
		    
		   }
		  });
		 btnBookView.setBounds(10, 79, 140, 35);
		 getContentPane().add(btnBookView);
		  
		 JButton btnBorrowView = new JButton("Borrow View");
		 btnBorrowView.setBounds(10, 148, 140, 35);
		 getContentPane().add(btnBorrowView);
		  
		 JButton btnBorrowHistory = new JButton("Borrow History");
		 btnBorrowHistory.setBounds(10, 215, 140, 35);
		 getContentPane().add(btnBorrowHistory);
		  
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
		  
		 setVisible(true);
	 }
}
