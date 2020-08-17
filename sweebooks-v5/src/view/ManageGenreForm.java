package view;

import java.util.List;

import javax.swing.JInternalFrame;

import controller.GenreHandler;
import model.Genre;

public class ManageGenreForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;

	public ManageGenreForm() {
		
		List<Genre> lg = new GenreHandler().getAll();
		// TODO: show lg here
		
	}
	
}
