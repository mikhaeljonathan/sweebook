package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import model.Member;

public class MemberHandler {

	public MemberHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JFrame showCreateMembership() {
		JFrame JF = new JFrame();
		return JF;
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
