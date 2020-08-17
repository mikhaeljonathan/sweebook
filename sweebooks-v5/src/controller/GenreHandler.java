package controller;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import helper.Validation;
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
		
		String id = inputs.get("id");
		String type = inputs.get("type");
		
		if (Validation.isGenreTypeExist(type)) {
			
			return new Genre(id, type).insert();
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Genre type already exists");
			return null;
			
		}
		
	}

}
