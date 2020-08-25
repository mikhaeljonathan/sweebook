package controller;
import java.util.List;

import model.Role;

public class RoleHandler {

	public RoleHandler() {
		
	}
	
	public List<Role> getAll(){
		
		return new Role().all();
		
	}
	
	public Role getByName(String name) {
		
		return new Role().getByName(name);
		
	}

}
