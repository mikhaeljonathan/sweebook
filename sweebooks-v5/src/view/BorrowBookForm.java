package view;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BorrowBookHandler;
import model.Book;

public class BorrowBookForm extends JInternalFrame{

	private BorrowBookHandler bbh = new BorrowBookHandler();
	private Book b = new Book();
	
	public JPanel panelBookInCartInfo(String BookName, String Genre, String ISBN, int Quantity) {
		JPanel panelBookInCartInfo = new JPanel();
		panelBookInCartInfo.setSize(219, 50);
		panelBookInCartInfo.setLayout(new GridLayout(3, 2, 0, 1));
		panelBookInCartInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(BookName);
		panelBookInCartInfo.add(lblName);
		
		JButton btnRemoveFromCart = new JButton("Remove");
		panelBookInCartInfo.add(btnRemoveFromCart);
		
		JLabel lblGenre = new JLabel(Genre);
		panelBookInCartInfo.add(lblGenre);
		
		JLabel lblEmpty = new JLabel("");
		panelBookInCartInfo.add(lblEmpty);
		
		JLabel lblISBN = new JLabel(ISBN);
		panelBookInCartInfo.add(lblISBN);
		
		JLabel lblQuantity = new JLabel(String.valueOf(Quantity));
		panelBookInCartInfo.add(lblQuantity);
		
		return panelBookInCartInfo;
	}
	
	public JPanel panelBookListInfo(String BookName, String Genre, String ISBN, int Quantity) {
		JPanel panelBookListInfo = new JPanel();
		panelBookListInfo.setSize(219, 50);
		panelBookListInfo.setLayout(new GridLayout(3, 2, 0, 1));
		panelBookListInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(BookName);
		panelBookListInfo.add(lblName);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		panelBookListInfo.add(btnAddToCart);
		btnAddToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bbh.addToCart(b)) {
					// TODO: keluarin success message
					JOptionPane.showMessageDialog(null, "Book added to cart.");
				} else if (!bbh.addToCart(b)) {
					// TODO: keluarin error message
					JOptionPane.showMessageDialog(null, "Error adding book to cart!");
				}
			}
		});
		
		JLabel lblGenre = new JLabel(Genre);
		panelBookListInfo.add(lblGenre);
		
		JLabel lblEmpty = new JLabel("");
		panelBookListInfo.add(lblEmpty);
		
		JLabel lblISBN = new JLabel(ISBN);
		panelBookListInfo.add(lblISBN);
		
		JLabel lblQuantity = new JLabel(String.valueOf(Quantity));
		panelBookListInfo.add(lblQuantity);
		
		return panelBookListInfo;
	}
	
	public BorrowBookForm() {
		
		// TODO: create UI
		setSize(600, 450);
		setTitle("View Borrow Form");
		setBorder(null);
		getContentPane().setLayout(null);
		
		//-------------- Bagian List Book -----------------------------
		JPanel panelMainListBook = new JPanel();
		panelMainListBook.setBounds(10, 11, 297, 401);
		getContentPane().add(panelMainListBook);
		panelMainListBook.setLayout(null);
		
		//panel buat list book
		JPanel panelListBook = new JPanel();
		panelListBook.setBounds(10, 11, 277, 379);
		JScrollPane scrollPaneListBook = new JScrollPane(panelListBook);
		scrollPaneListBook.setBounds(10, 11, 277, 379);
		scrollPaneListBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelMainListBook.add(scrollPaneListBook);
		//-------------------------------------------------------------
		
		//-------------- Bagian Panel Cart -----------------------
		JButton btnCart = new JButton("Cart");
		btnCart.setBounds(412, 11, 89, 23);
		getContentPane().add(btnCart);
		
		// panel main cart
		JPanel panelMainCart = new JPanel();
		panelMainCart.setBounds(317, 38, 273, 374);
		getContentPane().add(panelMainCart);
		panelMainCart.setLayout(null);
		
		//panel list di dalam cart
		JPanel panelCart = new JPanel();
		panelCart.setBounds(271, 54, 219, 261);
		JScrollPane scrollPaneCart = new JScrollPane(panelCart); //scroll buat panel
		scrollPaneCart.setBounds(10, 11, 253, 318);
		scrollPaneCart.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelMainCart.add(scrollPaneCart);
		
		//button borrow
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.setBounds(98, 340, 89, 23);
		panelMainCart.add(btnBorrow);
		
		panelMainCart.setVisible(false);
		//-------------------------------------------------
		
//		BorrowBookHandler bbh = new BorrowBookHandler();
		// Get list all of the available books to be borrowed
		List<Book> listOfAvailableBooks = new ArrayList<Book>();
		listOfAvailableBooks = bbh.getAvailableBook();
		// TODO: tampilin semua List<Book> nya
		// public JPanel panelBookListInfo( STRING, STRING, STRING, INT)
		int iBookList = 0;
		for (Book books : listOfAvailableBooks) {
			iBookList++;
			panelListBook.add(panelBookListInfo(books.getName(), books.getGenreId(), books.getIsbn(), books.getQuantity()));
			panelListBook.setLayout(new GridLayout(1+iBookList,0,0,10));
		}
		
		// TODO: kalau tombol cart diteken execute ini:
		btnCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!panelMainCart.isVisible()) { 
					panelMainCart.setVisible(true); //kalau ga nyala, nyalain
				} else {
					panelMainCart.setVisible(false); //kalau nyala, matiin
				}
			}
		});
		
		// Get list all of the books in the cart
		List<Book> cartList = new ArrayList<Book>();
		cartList = bbh.getCart();
		// TODO: tampilin semua List<Book> nya dari cart
		// public JPanel panelBookInCartInfo( STRING, STRING, STRING, INT)
		int iCartList = 0;
		for (Book books : cartList) {
			iCartList++;
			panelCart.add(panelBookInCartInfo(books.getName(), books.getGenreId(), books.getIsbn(), books.getQuantity()));
			panelCart.setLayout(new GridLayout(1+iCartList,0,0,10));
		}
		
		// TODO: kalau salah satu buku di listOfAvailableBooks diteken keluar tombol add to cart dan kalau diteken execute ini
//		Book b = new Book();
		
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
