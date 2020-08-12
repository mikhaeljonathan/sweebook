package view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class BorrowBookForm extends JInternalFrame{

	public BorrowBookForm() {
		
		// TODO: create UI
		setTitle("Create Membership");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		JPanel panel1 = new JPanel();
		add(panel1);
		setVisible(true);
		
	}
}
