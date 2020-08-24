package view;

import java.util.ArrayList;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ManageGenreForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private JTextField genreNameTextField;

	public ManageGenreForm() {
		setTitle("Manage Genre Form");
		setResizable(false);
		setSize(800, 400); //ukuran gui
		setLocation(160, 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //biar kalo di close langsung mati
		setVisible(true);
		setClosable(true);
		
		//---------------------- panel buat input genre ------------------------------------
		JPanel inputGenrePanel = new JPanel();
		add(inputGenrePanel, BorderLayout.NORTH);
		
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
		genreListPanel.setLayout(new GridLayout(lg.size()+1,1,0,10));
		JScrollPane listGenreScrollPane = new JScrollPane(genreListPanel);
		add(listGenreScrollPane, BorderLayout.CENTER);
		for (Genre genre : lg) {
			JLabel genreInfo = new JLabel(genre.getType());
			genreListPanel.add(genreInfo);
		}
		//----------------------------------------------------------------------------------
		
		// TODO: kalau klik tombol create
		createBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				HashMap<String, String> inputs = new HashMap<String, String>();
				inputs.put("type", genreNameTextField.getText());
//				System.out.println(genreNameTextField.getText());
				if (gh.insert(inputs) != null) {
					JOptionPane.showMessageDialog(null, "Genre successfully created");
				}
			}
			
		});
	}
	
}
