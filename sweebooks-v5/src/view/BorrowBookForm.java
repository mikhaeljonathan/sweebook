package view;

import java.awt.Color;
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

import controller.BorrowBookHandler;
import helper.SQLGetQuery;
import helper.Validation;
import model.Book;

import java.awt.BorderLayout;

// This is a singleton class
public class BorrowBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static BorrowBookForm instance = null;
	
	private BorrowBookHandler bbh;
	private JPanel listBookPanel;
	private JPanel listBookInCartPanel;
	private JScrollPane listBookInCartScrollPane;
	private JButton borrowBtn;
	
	private BorrowBookForm() {
		
		// Create UI
		setResizable(false);
		setTitle("Borrow Book Form");
		setSize(800, 400);
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2, 0, 0));
		setVisible(true);
		
		// Initialize handler
		bbh = new BorrowBookHandler();
		
		// Get list all of the available books to be borrowed
		List<Book> listOfAvailableBooks = bbh.getAvailableBook();
		
		// List of available books panel
		listBookPanel = new JPanel(new GridLayout(listOfAvailableBooks.size() + 1, 0, 0, 10));
		
		// Retrieve all books
		for (Book book : listOfAvailableBooks) {
			
			listBookPanel.add(listBookInfo(book));
			
		}
		
		// Add ScrollPane
		JScrollPane listBookScrollPane = new JScrollPane(listBookPanel);
		add(listBookScrollPane);
		
		// Main Panel Cart
		JPanel cartMainPanel = new JPanel();
		cartMainPanel.setLayout(new BorderLayout(0, 0));
		add(cartMainPanel);
		
		// Cart button
		JButton cartBtn = new JButton("Cart");
		cartMainPanel.add(cartBtn, BorderLayout.NORTH);
		
		// List of book in cart panel
		listBookInCartPanel = new JPanel();
		listBookInCartScrollPane = new JScrollPane(listBookInCartPanel);
		cartMainPanel.add(listBookInCartScrollPane, BorderLayout.CENTER);
		listBookInCartScrollPane.setVisible(false);
		
		// Borrow button
		borrowBtn = new JButton("Borrow");
		cartMainPanel.add(borrowBtn, BorderLayout.SOUTH);
		borrowBtn.setVisible(false);
		
		
		// Action for cart button
		cartBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (!listBookInCartScrollPane.isVisible()) {
					
					showCart();
					
				} else {
					
					unshowCart();
					
				}
				
			}
			
		});
		
		// Action for borrow button
		borrowBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (bbh.borrowBook()) {
					
					JOptionPane.showMessageDialog(null, "Success borrow book(s).");
					bbh.removeAllCart();
					unshowCart();
					refreshListBookPanel();
					
				}
				
			}
			
		});
		
	}
	
	public static BorrowBookForm getInstance() {
		
		if (instance == null) {
			instance = new BorrowBookForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		bbh.removeAllCart();
		instance = null;
		
	}
	
	public JPanel listBookInfo(Book b) {
		
		// Create components
		JPanel panelBookListInfo = new JPanel(new GridLayout(3, 2, 10, 10));
		panelBookListInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// Set the components
		JLabel lblName = new JLabel(b.getName());
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (new BorrowBookHandler().addToCart(b)) {
					
					JOptionPane.showMessageDialog(null, "Book added to cart");
					unshowCart();
					showCart();
					refreshListBookPanel();
					
				}
				
			}
			
		});
		if (Validation.isBookIsInTheCart(b)) {
			
			btnAddToCart.setVisible(false);
			
		}
		JLabel lblGenre = new JLabel(SQLGetQuery.getTypeFromGenreId(b.getGenreId()));
		JLabel lblEmpty = new JLabel("");
		JLabel lblISBN = new JLabel("ISBN: " + b.getIsbn());
		JLabel lblQuantity = new JLabel("Stock: " + String.valueOf(b.getQuantity()));
		
		// Add the components to the main panel
		panelBookListInfo.add(lblName);
		panelBookListInfo.add(btnAddToCart);
		panelBookListInfo.add(lblGenre);
		panelBookListInfo.add(lblEmpty);
		panelBookListInfo.add(lblISBN);
		panelBookListInfo.add(lblQuantity);
		
		return panelBookListInfo;
		
	}
	
	public JPanel listBookInCartInfo(Book b) {
		
		// Create components
		JPanel listBookInCartInfo = new JPanel();
		listBookInCartInfo.setLayout(new GridLayout(3, 2, 0, 10));
		listBookInCartInfo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// Set the components
		JLabel lblName = new JLabel(b.getName());
		JButton btnRemoveFromCart = new JButton("Remove");
		btnRemoveFromCart.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to remove this book from cart?");
				if (result == JOptionPane.YES_OPTION) {
					
					if (new BorrowBookHandler().removeCart(b)) {
						
						unshowCart();
						showCart();
						refreshListBookPanel();
						
					}
					
				}
				
			}
			
		});
		JLabel lblGenre = new JLabel(SQLGetQuery.getTypeFromGenreId(b.getGenreId()));
		JLabel lblEmpty = new JLabel("");
		JLabel lblISBN = new JLabel("ISBN: " + b.getIsbn());
		
		// Add the components to the main panel
		listBookInCartInfo.add(lblName);
		listBookInCartInfo.add(btnRemoveFromCart);
		listBookInCartInfo.add(lblGenre);
		listBookInCartInfo.add(lblEmpty);
		listBookInCartInfo.add(lblISBN);
		
		return listBookInCartInfo;
		
	}
	
	private void showCart() {

		// Retrieve the carts
		List<Book> cartList = bbh.getCart();
		
		// If empty don't show
		if (cartList.isEmpty()) {
			
			return;
			
		}
		
		// Make the grid
		listBookInCartPanel.setLayout(new GridLayout(cartList.size(), 0, 0, 10));
		
		// Retrieve every book in carts
		for (Book book : cartList) {
			
			listBookInCartPanel.add(listBookInCartInfo(book));
			
		}
		
		// Show entire panel
		listBookInCartScrollPane.setVisible(true);
		borrowBtn.setVisible(true);
		
	}

	private void unshowCart() {
		
		listBookInCartPanel.removeAll();
		listBookInCartScrollPane.setVisible(false);
		borrowBtn.setVisible(false);
		
	}
	
	private void refreshListBookPanel() {
		
		listBookPanel.setVisible(false);
		// Remove all
		listBookPanel.removeAll();
		
		// Get list all of the available books to be borrowed
		List<Book> listOfAvailableBooks = bbh.getAvailableBook();
		
		listBookPanel.setLayout(new GridLayout(listOfAvailableBooks.size() + 1, 0, 0, 10));
		
		// Retrieve all books
		for (Book book : listOfAvailableBooks) {
			
			listBookPanel.add(listBookInfo(book));
			
		}
		listBookPanel.setVisible(true);
		
	}
	
}