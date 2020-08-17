package controller;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;

import model.Genre;
import view.ManageGenreForm;

public class GenreHandler {

	public GenreHandler() {
		
	}
	
	public JInternalFrame showManageGenreForm() {

		return new ManageGenreForm();
		
	}
	
	public List<Genre> getAll() {
		
		return new Genre().all();		
		
	}
	
	public Genre insert(HashMap<String, String> inputs) {
		return new Genre();
	}

}
