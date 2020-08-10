package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;

import model.Book;

public class BookHandler {
	
	public BookHandler() {
		// TODO Auto-generated constructor stub
	}

	public JInternalFrame showViewBookForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public JInternalFrame showManageBookForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public List<Book> getAll(){
		return new ArrayList<Book>();
	}
	
	public Book getById(String id) {
		return new Book();
	}
	
	public Book getByIsbn(String isbn) {
		return new Book();
	}
	
	public List<Book> getBookByQuantityMoreThanZero(){
		return new ArrayList<Book>();
	}
	
	public Book insert(HashMap<String, String> inputs) {
		return new Book();
	}
	
	public Book update(HashMap<String, String> inputs) {
		return new Book();
	}
	
	public Book restockBook(String isbn) {
		return new Book();
	}
	
	public boolean delete(String id) {
		return true;
	}
	
}
