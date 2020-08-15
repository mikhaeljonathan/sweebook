package main;

import java.util.ArrayList;
import java.util.List;

import controller.BorrowBookHandler;
import model.Book;

public class MembershipMainForm {
	
	public MembershipMainForm() {
		
		// TODO: begitu membership klik borrow book menu langsung execute ini:
		BorrowBookHandler bbh = new BorrowBookHandler();
		bbh.showBorrowBookForm();
		
	}
	
}
