package controller;
import java.util.HashMap;

import model.User;

public class UserHandler {

	public UserHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public User insert(HashMap<String, String> inputs) {
		
		String id = inputs.get("id");
		String roleId = inputs.get("roleId");
		String name = inputs.get("name");
		String username = inputs.get("username");
		String password = inputs.get("password");
		String gender = inputs.get("gender");
		
		return new User(id, roleId, name, username, password, gender).insert();
		
	}
	
	public User getByUsername(String username) {
		return new User();
	}
}
