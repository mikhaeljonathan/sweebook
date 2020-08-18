package main;

import javax.swing.JFrame;

import controller.BorrowTransactionHandler;
import controller.EmployeeHandler;
import controller.MemberHandler;

public class ManagerMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public ManagerMainForm() {
		
		// TODO: kalau Borrow History menu
		new BorrowTransactionHandler().showBorrowHistoryForm();
		
		// TODO: kalau Membership View menu
		new MemberHandler().showViewMembershipForm();
		
		// TODO: kalau Manage Employee Menu
		new EmployeeHandler().showManageEmployeeForm();
		
	}
	
}
