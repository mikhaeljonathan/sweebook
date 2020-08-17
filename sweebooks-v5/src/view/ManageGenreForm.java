package view;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controller.GenreHandler;
import model.Genre;

public class ManageGenreForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ManageGenreForm() {
		
		GenreHandler gh = new GenreHandler();
		List<Genre> lg = gh.getAll();
		// TODO: show lg here
		
		// TODO: kalau klik tombol create
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("type", "nama genre nya di sini");
		
		if (gh.insert(inputs) != null) {
			
			JOptionPane.showMessageDialog(null, "Genre successfully created");
			
		}
		
	}
	
}
