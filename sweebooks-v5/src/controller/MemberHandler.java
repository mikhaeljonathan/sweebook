package controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import helper.Validation;
import model.Member;
import model.Role;
import model.User;
import view.CreateMembershipForm;
import view.ViewMembershipForm;

public class MemberHandler {

	public MemberHandler() {
		
	}
	
	public JFrame showCreateMembershipForm() {
		
		return new CreateMembershipForm();
		
	}
	
	public JInternalFrame showViewMembershipForm() {
		
		return new ViewMembershipForm();
		
	}
	
	public List<Member> getAll(){ 
		return new ArrayList<Member>();
	}
	
	public Member insert(HashMap<String, String> inputs) {
		return new Member();
	}
	
	public Member createMembership(HashMap<String, String> inputs) {
		
		// Check the constraints
		if (Validation.validateMembership(inputs)) {
			
			// Get Role object for obtaining roleId for User object
			Role r = new RoleHandler().getByName(inputs.get("role"));
			
			// Generate ID for User and Member object
			String uuid = UUID.randomUUID().toString();
			
			// Create the entities
			createUserEntities(uuid, r, inputs);
			return createMemberEntities(uuid, inputs);
			
		} else {
			
			return null;
			
		}
		
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
		if (u.insert() == null) {
			
			// Show error message
			JOptionPane.showMessageDialog(null, "Fail created user");
			
		}
			
	}

	private Member createMemberEntities(String uuid, HashMap<String, String> inputs) {
		
		// Retrieve the attributes
		String address = inputs.get("address");
		
		// Create date
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);
		
		// Create Member object with corresponding attributes
		Member m = new Member(uuid, address, strDate);
		
		// Insert Member object into database and return it
		return m.insert();
		
	}
	
}
