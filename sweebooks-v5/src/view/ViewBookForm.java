package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.BookHandler;
import model.Book;

public class ViewBookForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ViewBookForm() {
		
		BookHandler bh = new BookHandler();
		List<Book> lb = bh.getAll();
		
		// Create UI
		setTitle("View Book Form");
		setSize(700, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(170, 10);
		setResizable(false);
//		setUndecorated(true);
		
		
		//Main Panel
		JPanel mainPanel = new JPanel();
		JScrollPane mainSp = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(mainSp);
		
		//add ListBook to MainPanel
		int i=0;
		for (Book books : lb) {
			i++;
			mainPanel.add(bookPanelForm(books.getName(), books.getGenreId(), books.getIsbn()));
			mainPanel.setLayout(new GridLayout(1+i,0,0,10));
		}
		
		setVisible(true);
		
	}

	private Component bookPanelForm(String name, String genreId, String isbn) {
		
		//List Book Panel
		JPanel bookPanelForm = new JPanel();
		bookPanelForm.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		bookPanelForm.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel titleBookName = new JLabel(name, JLabel.CENTER);
		titleBookName.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bookPanelForm.add(titleBookName);
		
		JLabel titleBookGenre = new JLabel("Genre        : " + genreId);
		titleBookGenre.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bookPanelForm.add(titleBookGenre);
		  
		JLabel titleBookIsbn = new JLabel("ISBN         : " + isbn);
		titleBookIsbn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bookPanelForm.add(titleBookIsbn);
		  
		return bookPanelForm;
		
	}

	
}
