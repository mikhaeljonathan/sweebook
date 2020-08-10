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
		return new Role();
	}
}
