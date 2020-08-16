package main;


import controller.BorrowBookHandler;

public class MembershipMainForm {
	
	public MembershipMainForm() {
		
		// TODO: begitu membership klik borrow book menu langsung execute ini:
		BorrowBookHandler bbh = new BorrowBookHandler();
		bbh.showBorrowBookForm();
		
	}
	
}
