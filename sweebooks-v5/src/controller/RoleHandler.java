package controller;
import java.util.ArrayList;
import java.util.List;

import model.Role;

public class RoleHandler {

	public RoleHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Role> getAll(){
		return new ArrayList<Role>();
	}
	
	public Role getByName(String name) {
		
		Role r = new Role();
		r.getByName(name);
		return r;
		
	}

}
