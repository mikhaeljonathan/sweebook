package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.BorrowTransactionHandler;
import controller.EmployeeHandler;
import controller.MemberHandler;

public class HumanCapitalMainForm extends JFrame{

	private static final long serialVersionUID = 1L;

	public HumanCapitalMainForm() {
	
		// Create UI
		setTitle("Human Capital Main Form");
		getContentPane().setLayout(null);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//Manage Employee Button
		JButton manageEmployeeBtn = new JButton("Manage Employee");
		manageEmployeeBtn.setBounds(10, 11, 140, 35);
		add(manageEmployeeBtn);
		manageEmployeeBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// TODO: kalau Manage Employee Menu
				add(new EmployeeHandler().showManageEmployeeForm());
				
			}
			
		});
		
	}
	
}
