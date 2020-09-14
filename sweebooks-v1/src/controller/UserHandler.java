package controller;
import java.util.HashMap;

import model.User;

public class UserHandler {

	public UserHandler() {
		
	}
	
	public User insert(HashMap<String, String> inputs) {
		
		String id = inputs.get("id");
		String roleId = inputs.get("roleId");
		String name = inputs.get("name");
		String username = inputs.get("username").toLowerCase();
		String password = inputs.get("password");
		String gender = inputs.get("gender");
		
		return new User(id, roleId, name, username, password, gender).insert();
		
	}
	
	public User getByUsername(String username) {
		
		return new User().getByUsername(username);
		
	}
	
}
