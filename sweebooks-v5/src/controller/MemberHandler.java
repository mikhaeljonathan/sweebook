package controller;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import check.CheckInput;
import model.Member;
import model.Role;
import model.User;
import view.CreateMembershipForm;

public class MemberHandler {

	public MemberHandler() {
		
		// Empty constructor
		
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
		
		// Check the constraints
		
		if (validate(inputs)) {
			
			// If validated then do the next jobs:
			
			// Get Role object for obtaining roleId for User object
			RoleHandler rh = new RoleHandler();
			Role r = rh.getByName(inputs.get("role"));
			
			// Generate ID for User and Member object
			String uuid = UUID.randomUUID().toString();
			
			// Create the entities
			createUserEntities(uuid, r, inputs);
			return createMemberEntities(uuid, inputs);
			
		} else {
			
			return null;
			
		}
		
	}
	
	private boolean validate(HashMap<String, String> inputs) {
		
		// The constraints are given on the Microsoft Word
		
		// Retrieve the attributes
		String name = inputs.get("name");
		String gender = inputs.get("gender");
		String address = inputs.get("address");
		String username = inputs.get("username");
		String password = inputs.get("password");
		
		// Check the constraint
		if (!CheckInput.validateName(name)) return false;
		if (!CheckInput.validateGender(gender)) return false;
		if (!CheckInput.validateAddress(address)) return false;
		if (!CheckInput.validateUsername(username)) return false;
		if (!CheckInput.validatePassword(password)) return false;
		
		return true;
	}

	private void createUserEntities(String uuid, Role r, HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String name = inputs.get("name");
		String gender = inputs.get("gender");
		String username = inputs.get("username");
		String password = inputs.get("password");
		
		// Create User object with corresponding attributes
		User u = new User(uuid, r.getId(), name, username, password, gender);
		
		// Insert User object into database
		u.insert();
		
	}
	
	private Member createMemberEntities(String uuid, HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String address = inputs.get("address");
		
		// Create Member object with corresponding attributes
		Member m = new Member(uuid, address, LocalDate.now().toString());
		
		// Insert Member object into database
		m.insert();
		
		// Return the object
		return m;
	}
}
