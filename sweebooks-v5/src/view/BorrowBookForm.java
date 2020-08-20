package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BorrowBookHandler;
import model.Book;

public class BorrowBookForm extends JInternalFrame{

	public BorrowBookForm() {
		setResizable(false);
		setTitle("Borrow Book Form");
		setSize(800, 400); //ukuran gui
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //biar kalo di close ga running di background
		getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
		setVisible(true);
		
		BorrowBookHandler bbh = new BorrowBookHandler();
		List<Book> listOfAvailableBooks = new ArrayList<Book>();
		listOfAvailableBooks = bbh.getAvailableBook();
		
		// TODO: create UI
		//-------------- Bagian List Book -----------------------------
		JPanel listBookPanel = new JPanel();
		listBookPanel.setLayout(new GridLayout(20, 0, 0, 10));
		JScrollPane listBookScrollPane = new JScrollPane(listBookPanel);
		getContentPane().add(listBookScrollPane);
		//-------------------------------------------------------------
		
		//-------------- Bagian Panel Cart -----------------------
		JPanel listBookInCartPanel = new JPanel();
		getContentPane().add(listBookInCartPanel);
		//-------------------------------------------------
		
		// Get list all of the available books to be borrowed
//		List<Book> listOfAvailableBooks = new ArrayList<Book>();
//		listOfAvailableBooks = bbh.getAvailableBook();
		
		// TODO: tampilin semua List<Book> nya
		// public JPanel panelBookListInfo( STRING, STRING, STRING, INT)
		for (Book books : listOfAvailableBooks) {
			listBookPanel.add(ListBookInfo(listBookPanel.getWidth(),books.getName(), books.getGenreId(), books.getIsbn(), books.getQuantity()));
		}
		
		// TODO: kalau tombol cart diteken execute ini:
//		btnCart.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (!panelMainCart.isVisible()) { 
//					panelMainCart.setVisible(true); //kalau ga nyala, nyalain
//				} else {
//					panelMainCart.setVisible(false); //kalau nyala, matiin
//				}
//			}
//		});
		
		// Get list all of the books in the cart
		List<Book> cartList = new ArrayList<Book>();
		cartList = bbh.getCart();
		// TODO: tampilin semua List<Book> nya dari cart
		// public JPanel panelBookInCartInfo( STRING, STRING, STRING, INT)
//		int iCartList = 0;
//		for (Book books : cartList) {
//			iCartList++;
//			panelCart.add(panelBookInCartInfo(books.getName(), books.getGenreId(), books.getIsbn(), books.getQuantity()));
//			panelCart.setLayout(new GridLayout(1+iCartList,0,0,10));
//		}
		
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
		
	}
	
	
	public JPanel ListBookInfo(int width, String BookName, String Genre, String ISBN, int Quantity) {
		JPanel panelBookListInfo = new JPanel();
		panelBookListInfo.setLayout(new GridLayout(3, 2, 0, 1));
		panelBookListInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(BookName);
		panelBookListInfo.add(lblName);
		
		BorrowBookHandler bbh = new BorrowBookHandler();
		
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
	
	
	public JPanel panelBookInCartInfo(String BookName, String Genre, String ISBN, int Quantity) {
		JPanel panelBookInCartInfo = new JPanel();
		panelBookInCartInfo.setSize(219, 50);
		panelBookInCartInfo.setLayout(new GridLayout(3, 2, 0, 1));
		panelBookInCartInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel lblName = new JLabel(BookName);
		panelBookInCartInfo.add(lblName);
		
		BorrowBookHandler bbh = new BorrowBookHandler();
		
		JButton btnRemoveFromCart = new JButton("Remove");
		panelBookInCartInfo.add(btnRemoveFromCart);
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
		panelBookInCartInfo.add(lblGenre);
		
		JLabel lblEmpty = new JLabel("");
		panelBookInCartInfo.add(lblEmpty);
		
		JLabel lblISBN = new JLabel(ISBN);
		panelBookInCartInfo.add(lblISBN);
		
		JLabel lblQuantity = new JLabel(String.valueOf(Quantity));
		panelBookInCartInfo.add(lblQuantity);
		
		return panelBookInCartInfo;
	}
}