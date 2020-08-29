package view;

import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controller.GenreHandler;
import model.Genre;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

// This is a singleton class
public class ManageGenreForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ManageGenreForm instance = null;
	
	private GenreHandler gh;
	private JPanel genreListPanel;
	private JTextField genreNameTextField;

	private ManageGenreForm() {
		
		// Create UI
		setTitle("Manage Genre Form");
		setResizable(false);
		setSize(800, 400);
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		KeyAdapter keyAdapter = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					createGenre();
					
				}
				
			}
			
		};
		
		gh = new GenreHandler();
		
		JPanel inputGenrePanel = new JPanel();
		add(inputGenrePanel, BorderLayout.NORTH);
		
		JLabel genreNameLabel = new JLabel("Genre Name");
		inputGenrePanel.add(genreNameLabel);
		
		genreNameTextField = new JTextField();
		genreNameTextField.addKeyListener(keyAdapter);
		genreNameTextField.setColumns(20);
		inputGenrePanel.add(genreNameTextField);
		
		JButton createBtn = new JButton("Create");
		inputGenrePanel.add(createBtn);
		
		// Retrieve all genre
		List<Genre> lg = gh.getAll();
		
		// List Genre
		genreListPanel = new JPanel(new GridLayout(lg.size(), 1, 0, 10));
		JScrollPane listGenreScrollPane = new JScrollPane(genreListPanel);
		add(listGenreScrollPane, BorderLayout.CENTER);
		
		// For each genre
		for (Genre genre : lg) {
			
			JLabel genreInfo = new JLabel(genre.getType());
			genreListPanel.add(genreInfo);
			
		}
		
		createBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				createGenre();
				
			}
			
		});
		
	}
	
	public static ManageGenreForm getInstance() {
		
		if (instance == null) {
			instance = new ManageGenreForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		instance = null;
		
	}
	
	private void createGenre() {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("type", genreNameTextField.getText());
		
		if (gh.insert(inputs) != null) {
			
			JOptionPane.showMessageDialog(null, "Genre successfully created");
			refreshListGenre();
			
		}
		
	}
	
	private void refreshListGenre() {
		
		genreListPanel.setVisible(false);
		genreListPanel.removeAll();
		
		// Retrieve all genre
		List<Genre> lg = gh.getAll();
		
		genreListPanel.setLayout(new GridLayout(lg.size(), 1, 0, 10));
		
		// For each genre
		for (Genre genre : lg) {
			
			JLabel genreInfo = new JLabel(genre.getType());
			genreListPanel.add(genreInfo);
			
		}
		
		genreListPanel.setVisible(true);
		
	}
	
}
