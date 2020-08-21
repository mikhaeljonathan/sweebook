package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BorrowBookHandler;
import model.Book;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

public class BorrowBookForm extends JInternalFrame{

	public BorrowBookForm() {
		//Main Frame
		setResizable(false);
		setTitle("Borrow Book Form");
		setSize(800, 400); //ukuran gui
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //biar kalo di close ga running di background
		getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
		setVisible(true);
		
		// Get list all of the available books to be borrowed
		BorrowBookHandler bbh = new BorrowBookHandler();
		List<Book> listOfAvailableBooks = new ArrayList<Book>();
		listOfAvailableBooks = bbh.getAvailableBook();
		
		// TODO: create UI
		//-------------- Bagian List Book -----------------------------
		JPanel listBookPanel = new JPanel();
//		listOfAvailableBooks.size()
		listBookPanel.setLayout(new GridLayout(10, 0, 0, 10));
		JScrollPane listBookScrollPane = new JScrollPane(listBookPanel);
		getContentPane().add(listBookScrollPane);
		//-------------------------------------------------------------
		
		//-------------- Bagian Panel Cart -----------------------
		JPanel listBookInCartMainPanel = new JPanel();
		getContentPane().add(listBookInCartMainPanel);
		listBookInCartMainPanel.setLayout(new BorderLayout(0, 0));
		
		JButton cartBtn = new JButton("Cart");
		listBookInCartMainPanel.add(cartBtn, BorderLayout.NORTH);
		
		JPanel listBookInCartPanel = new JPanel();
		listBookInCartPanel.setLayout(new GridLayout(10,0,0,10));
		JScrollPane listBookInCartScrollPane = new JScrollPane(listBookInCartPanel);
		listBookInCartScrollPane.setVisible(false);
		listBookInCartMainPanel.add(listBookInCartScrollPane, BorderLayout.CENTER);
		
		JButton borrowBtn = new JButton("Borrow");
		borrowBtn.setVisible(false);
		listBookInCartMainPanel.add(borrowBtn, BorderLayout.SOUTH);
		//-------------------------------------------------
		
		// TODO: tampilin semua List<Book> nya
		// public JPanel panelBookListInfo( STRING, STRING, STRING, INT)
		for (Book books : listOfAvailableBooks) {
			listBookPanel.add(ListBookInfo(books.getId(),books.getName(), books.getGenreId(), books.getIsbn(), books.getQuantity()));
		}
		
		// TODO: kalau tombol cart diteken execute ini:
		cartBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Book> cartList = new ArrayList<Book>();
				cartList = bbh.getCart();
				// TODO: tampilin semua List<Book> nya dari cart
				// public JPanel panelBookInCartInfo( STRING, STRING, STRING, INT)
				for (Book books : cartList) {
					listBookInCartPanel.add(ListBookInCartInfo(books.getId(), books.getName(), books.getGenreId(), books.getIsbn(), books.getQuantity()));
				}
				
				if (!listBookInCartScrollPane.isVisible()) { 
					listBookInCartScrollPane.setVisible(true); //kalau ga nyala, nyalain
					borrowBtn.setVisible(true);
					listBookInCartScrollPane.revalidate();
				} else {
					listBookInCartScrollPane.setVisible(false); //kalau nyala, matiin
					borrowBtn.setVisible(false);
				}
			}
		});
		
		// Get list all of the books in the cart
		
		
		// TODO: kalau salah satu buku di listOfAvailableBooks diteken keluar tombol add to cart dan kalau diteken execute ini
		Book b = new Book();
		
		// TODO: kalau cartnya keluar, bakal mucnul juga borrow button, terus kalo diklik execute this:
		borrowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bbh.borrowBook()) {
					// TODO: keluarin success message
					JOptionPane.showMessageDialog(null, "Success borrow book(s).");
				} else {
					// TODO: keluarin error message
					JOptionPane.showMessageDialog(null, "Failed to borrow book(s)!");
				}
			}
		});
		
		// TODO: kalau salah satu item cart di klik, bakal muncul tombol remove cart
		// then keluarin confirmation dialog, klao yes then execute this:
		
	}
	
	
	public JPanel ListBookInfo(String BookID, String BookName, String Genre, String ISBN, int Quantity) {
		BorrowBookHandler bbh = new BorrowBookHandler();
		Book b = new Book(BookID, BookName, Genre, ISBN, Quantity);
		
		JPanel panelBookListInfo = new JPanel();
		panelBookListInfo.setLayout(new GridLayout(3, 2, 0, 1));
		panelBookListInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(b.getName());
		panelBookListInfo.add(lblName);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		panelBookListInfo.add(btnAddToCart);
		btnAddToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bbh.addToCart(b)) {
					// TODO: keluarin success message
					JOptionPane.showMessageDialog(null, "Book added to cart.");
				} else {
					// TODO: keluarin error message
					JOptionPane.showMessageDialog(null, "Error adding book to cart!");
				}
			}
		});
		
		JLabel lblGenre = new JLabel(b.getGenreId());
		panelBookListInfo.add(lblGenre);
		
		JLabel lblEmpty = new JLabel("");
		panelBookListInfo.add(lblEmpty);
		
		JLabel lblISBN = new JLabel(b.getIsbn());
		panelBookListInfo.add(lblISBN);
		
		JLabel lblQuantity = new JLabel(String.valueOf(b.getQuantity()));
		panelBookListInfo.add(lblQuantity);
		
		return panelBookListInfo;
	}
	
	
	public JPanel ListBookInCartInfo(String BookID, String BookName, String Genre, String ISBN, int Quantity) {
		BorrowBookHandler bbh = new BorrowBookHandler();
		Book b = new Book(BookID, BookName, Genre, ISBN, Quantity);
		
		JPanel listBookInCartInfo = new JPanel();
		listBookInCartInfo.setSize(219, 50);
		listBookInCartInfo.setLayout(new GridLayout(3, 2, 0, 1));
		listBookInCartInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(BookName);
		listBookInCartInfo.add(lblName);
		
		JButton btnRemoveFromCart = new JButton("Remove");
		listBookInCartInfo.add(btnRemoveFromCart);
		btnRemoveFromCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bbh.removeCart(b)) {
					// TODO: keluarin success message
					JOptionPane.showMessageDialog(null, "Remove book success!");
				} else if (!bbh.removeCart(b)) {
					// TODO: keluarin error message
					JOptionPane.showMessageDialog(null, "Failed removing book!");
				}
			}
		});
		
		JLabel lblGenre = new JLabel(Genre);
		listBookInCartInfo.add(lblGenre);
		
		JLabel lblEmpty = new JLabel("");
		listBookInCartInfo.add(lblEmpty);
		
		JLabel lblISBN = new JLabel(ISBN);
		listBookInCartInfo.add(lblISBN);
		
		JLabel lblQuantity = new JLabel(String.valueOf(Quantity));
		listBookInCartInfo.add(lblQuantity);
		
		return listBookInCartInfo;
	}
}