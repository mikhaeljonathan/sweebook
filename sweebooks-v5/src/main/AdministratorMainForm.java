package main;

import javax.swing.JFrame;

import controller.BookHandler;
import controller.BorrowTransactionHandler;
import controller.MemberHandler;

public class AdministratorMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public AdministratorMainForm() {
		
		// TODO: kalau Book View Menu
		new BookHandler().showViewBookForm();
		
		// TODO: kalau Borrow View menu
		new BorrowTransactionHandler().showBorrowForm();
		
		// TODO: kalau Borrow History menu
		new BorrowTransactionHandler().showBorrowHistoryForm();
		
		// TODO: kalau Membership View menu
		new MemberHandler().showViewMembershipForm();
		
	}
	
}
