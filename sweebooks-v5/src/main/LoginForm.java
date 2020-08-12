package main;

import java.util.HashMap;

import controller.MemberHandler;
import model.Member;

public class LoginForm {

	public LoginForm() {
		
		// TODO: after the create membership button pressed, then do below
		
		// Show the form
		MemberHandler mh = new MemberHandler();
		mh.showCreateMembershipForm();
		
		// Handle the input field
		handleCreateMembershipInput(mh);
		
	}
	
	private void handleCreateMembershipInput(MemberHandler mh) {
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		
		// Put all the text in input field to the HashMap
		inputs.put("name", "input1");
		inputs.put("gender", "input1");
		inputs.put("address", "input1");
		inputs.put("username", "input1");
		inputs.put("password", "input1");
		// Actor who handle create membership use case always "membership"
		inputs.put("role", "membership"); 
		
		// Create new Member entity
		Member m = mh.createMembership(inputs);
		
		// Show Member entity in UI
		showMemberInGUI(m);
		
	}
	
	private void showMemberInGUI(Member m) {
		
		// TODO: tampilin m di UI
		if (m != null) {
			
		} else {
			//show error message
		}
		
	}
	
}
