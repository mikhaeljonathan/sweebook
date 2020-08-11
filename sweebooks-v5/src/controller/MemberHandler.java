package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import model.Member;
import view.CreateMembershipForm;

public class MemberHandler {

	public MemberHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JFrame showCreateMembershipForm() {
		return new CreateMembershipForm();
	}
	
	public JInternalFrame showViewMembershipForm() {
		JInternalFrame JIF = new JInternalFrame();
		return JIF;
	}
	
	public List<Member> getAll(){ 
		return new ArrayList<Member>();
	}
	
	public Member insert(HashMap<String, String> inputs) {
		return new Member();
	}
	
	public Member createMembership(HashMap<String, String> inputs) {
		return new Member();
	}
	
	

}
