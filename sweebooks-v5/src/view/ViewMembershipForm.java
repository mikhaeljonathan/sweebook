package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.MemberHandler;
import model.Member;

public class ViewMembershipForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	public ViewMembershipForm() {
		setResizable(false);
		setTitle("View Membership Form");
		setSize(400, 400); //ukuran gui
		setLocation(160, 10); //start location frame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //biar kalo di close langsung mati
		setVisible(true);
		setClosable(true);
		
		List<Member> lm = new ArrayList<Member>();
		lm = new MemberHandler().getAll();
		
		JPanel membershipPanel = new JPanel();
		membershipPanel.setLayout(new GridLayout(lm.size()+1,0,0,10)); // +1 biar kalau db kosong, grid nya masih ada 1 kolom
																		// kalau 0 kolom 0 baris, error
		JScrollPane membershipScrollPane = new JScrollPane(membershipPanel);
		add(membershipScrollPane);
		
//		System.out.println(lm.size());
		// TODO: show lm here:
		for (Member member : lm) {
			membershipPanel.add(membershipInfoPanel(member.getId(),member.getId(),member.getAddress(),member.getMemberSince()));
		}
		
	}
	
	private JPanel membershipInfoPanel(String id, String name, String address, String since) {
		JPanel membershipInfoPanel = new JPanel();
		membershipInfoPanel.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel membershipID = new JLabel(id);
		membershipInfoPanel.add(membershipID);
		
		JLabel membershipName = new JLabel(name);
		membershipInfoPanel.add(membershipName);
		
		JLabel membershipAddress = new JLabel(address);
		membershipInfoPanel.add(membershipAddress);
		
		JLabel membershipSince = new JLabel(since);
		membershipInfoPanel.add(membershipSince);
		
		return membershipInfoPanel;
	}

}
