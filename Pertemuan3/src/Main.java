import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame{
	
	public Main() {
		setTitle("Main Frame"); //judul
		setSize(300, 300); //ukuran gui
		setDefaultCloseOperation(EXIT_ON_CLOSE); //biar kalo di close ga running di background
		setLocationRelativeTo(null); //awal2 gui nya ke tengah
//		setResizable(false); //biar gk bisa diresize
		
		// panel -> jpanel
		// layout manager
		// border layout, grid layout, flow layout, card layout, grid back layout
		
		
		
		
		JPanel panel1 = new JPanel(new BorderLayout(5, 5));
		//BorderLayout -> North, west, east, south, center
		
		panel1.add(new JButton("North"), BorderLayout.NORTH);
		panel1.add(new JButton("West"), BorderLayout.WEST);
		panel1.add(new JButton("East"), BorderLayout.EAST);
		panel1.add(new JButton("South"), BorderLayout.SOUTH);
		panel1.add(new JButton("Center"), BorderLayout.CENTER);
		
		
		
		
		
		
		JPanel panel2 = new JPanel(new GridLayout(3, 3, 5, 5));
		for (int i = 1; i < 10; i++) {
			panel2.add(new JButton(i + ""));
		}
		panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		
		
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		for (int i = 1; i <= 3; i++) {
			panel3.add(new JButton(i + ""));
		}
		
		
		
		
		
		
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.add(panel3, BorderLayout.NORTH);
		panel4.add(panel2, BorderLayout.CENTER);
		panel4.add(new JPanel(), BorderLayout.SOUTH);
		
		
		
		
		
		
		add(panel4);
		
		setVisible(true); //default nya false
	}

}
