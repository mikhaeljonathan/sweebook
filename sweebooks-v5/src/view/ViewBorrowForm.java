package view;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewBorrowForm extends JInternalFrame{
	
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
	
	public ViewBorrowForm() {
		setSize(600, 450);
		setTitle("View Borrow Form");
		setBorder(null);
		getContentPane().setLayout(null);
		
		//-------------- Bagian List Book -----------------------------
		JPanel panelMainListBook = new JPanel();
		panelMainListBook.setBounds(10, 11, 297, 401);
		getContentPane().add(panelMainListBook);
		panelMainListBook.setLayout(null);
		
		JPanel panelListBook = new JPanel();
		panelListBook.setBounds(10, 11, 277, 379);
		JScrollPane scrollPaneListBook = new JScrollPane(panelListBook);
		scrollPaneListBook.setBounds(10, 11, 277, 379);
		scrollPaneListBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelMainListBook.add(scrollPaneListBook);
		
		//-------------------- Panel Book In Cart Info -----------------------------
		// public JPanel panelBookListInfo( STRING, STRING, STRING, INT)
		//-------------------------------------------------------------------------
		
		for(int i=0;i<20;i++) {
			panelListBook.add(panelBookListInfo("Coba", "Mystery", "BK" + i, 99));
			panelListBook.setLayout(new GridLayout(1+i,0,0,10));
		}
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
		
		JPanel panelCart = new JPanel();
		panelCart.setBounds(271, 54, 219, 261);
		JScrollPane scrollPaneCart = new JScrollPane(panelCart);
		scrollPaneCart.setBounds(10, 11, 253, 318);
		scrollPaneCart.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelMainCart.add(scrollPaneCart);
		
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.setBounds(98, 340, 89, 23);
		panelMainCart.add(btnBorrow);
		
		//-------------------- Panel Book In Cart Info -----------------------------
		// public JPanel panelBookInCartInfo( STRING, STRING, STRING, INT)
		//-------------------------------------------------------------------------
		
		for(int i=0;i<5;i++) {
			panelCart.add(panelBookInCartInfo("COBA", "Mystery", "BK" + i, 99));
			panelCart.setLayout(new GridLayout(1+i,0,0,10));
		}
		
		btnCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		//-------------------------------------------------
	}
}
