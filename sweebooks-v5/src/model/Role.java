package model;
import java.util.ArrayList;
import java.util.List;

public class Role {

	private String id;
	private String name;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Role> all(){
		return new ArrayList<Role>();
	}

	public Role getByName(String name) {
		
		// TODO
		// ambil data2 role dr database berdasarkan nama rolenya dan masukin ke constructor
		// this.id = id <- variabel yg didapat dari mySQL
		
		return this;
	}
	
	public String getId() {
		return id;
	}
}
