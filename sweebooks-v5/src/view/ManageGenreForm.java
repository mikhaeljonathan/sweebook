package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controller.BookHandler;
import controller.GenreHandler;
import model.Genre;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManageGenreForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private JTextField genreNameTextField;
	private JTable table;

	public ManageGenreForm() {
		setTitle("Manage Genre Form");
		setResizable(false);
		setSize(800, 400); //ukuran gui
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //biar kalo di close ga running di background
		setVisible(true);
		
		//---------------------- panel buat input genre ------------------------------------
		JPanel inputGenrePanel = new JPanel();
		getContentPane().add(inputGenrePanel, BorderLayout.NORTH);
		
		JLabel genreNameLabel = new JLabel("Genre Name");
		inputGenrePanel.add(genreNameLabel);
		
		genreNameTextField = new JTextField();
		inputGenrePanel.add(genreNameTextField);
		genreNameTextField.setColumns(20);
		
		JButton createBtn = new JButton("Create");
		inputGenrePanel.add(createBtn);
		//----------------------------------------------------------------------------------
		
		GenreHandler gh = new GenreHandler();
		List<Genre> lg = new ArrayList<Genre>();
		lg = gh.getAll();
		// TODO: show lg here
		//----------------------------- panel buat list genre ------------------------------
		JPanel genreListPanel = new JPanel();
		getContentPane().add(genreListPanel, BorderLayout.CENTER);
		
		JList<Genre> listGenre = new JList<Genre>();
		DefaultListModel listGenreModel = new DefaultListModel();
		for (Genre genre : lg) {
			listGenreModel.addElement(genre.getType());
		}
		listGenre.setModel(listGenreModel);
		JScrollPane listGenreScrollPane = new JScrollPane(listGenre);
		genreListPanel.add(listGenreScrollPane);
		
		//----------------------------------------------------------------------------------
		
		// TODO: kalau klik tombol create
		createBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				HashMap<String, String> inputs = new HashMap<String, String>();
				inputs.put("type", genreNameTextField.getText());
//				System.out.println(genreNameTextField.getText());
				if (gh.insert(inputs) != null) {
					validate();
					repaint();
					JOptionPane.showMessageDialog(null, "Genre successfully created");
					
				}
			}
			
		});
	}
	
}
