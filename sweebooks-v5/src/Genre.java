import java.util.ArrayList;
import java.util.List;

public class Genre {

	private String id;
	private String type;
	
	public Genre() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Genre> all(){
		return new ArrayList<Genre>();
	}
	
	public Genre insert() {
		return new Genre();
	}
	
	public Genre getByType(String type) {
		return new Genre();
	}

}
