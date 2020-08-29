package view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.MemberHandler;
import helper.SQLGetQuery;
import model.Member;

// This is a singleton class
public class ViewMembershipForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static ViewMembershipForm instance = null;
	
	private ViewMembershipForm() {
		
		// Create UI
		setResizable(false);
		setTitle("View Membership Form");
		setSize(400, 400);
		setLocation(160, 10);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		List<Member> lm = new MemberHandler().getAll();
		
		JPanel membershipPanel = new JPanel(new GridLayout(lm.size() + 1, 0, 0, 10));
		JScrollPane membershipScrollPane = new JScrollPane(membershipPanel);
		add(membershipScrollPane);
		
		// For each member
		int counter = 1;
		for (Member member : lm) {
			
			membershipPanel.add(membershipInfoPanel(member, counter));
			counter++;
			
		}
		
	}
	
	private JPanel membershipInfoPanel(Member member, int counter) {
		
		JPanel membershipInfoPanel = new JPanel(new GridLayout(4, 2, 0, 0));
		
		JLabel membershipID = new JLabel(counter + ".");
		membershipInfoPanel.add(membershipID);
		
		JLabel membershipUsername = new JLabel("Username: " + SQLGetQuery.getUsernameFromId(member.getId()));
		membershipInfoPanel.add(membershipUsername);
		
		JLabel membershipAddress = new JLabel("<html>Address: <br/>" + member.getAddress() + "</html>");
		membershipInfoPanel.add(membershipAddress);
		
		String memberSince = member.getMemberSince();
		memberSince = memberSince.substring(0, memberSince.length() - 2);
		JLabel membershipSince = new JLabel("Member since: " + memberSince);
		membershipInfoPanel.add(membershipSince);
		
		return membershipInfoPanel;
		
	}
	
	public static ViewMembershipForm getInstance() {
		
		if (instance == null) {
			instance = new ViewMembershipForm();
		}
		
		return instance;
		
	}
	
	public void destroy() {
		
		setVisible(false);
		instance = null;
		
	}

}
