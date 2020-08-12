package model;
import java.util.ArrayList;
import java.util.List;

public class Member {

	private String id;
	private String address;
	private String memberSince;
	
	public Member() {
		
	}
	
	public Member(String id, String address, String memberSince) {
		
		this.id = id;
		this.address = address;
		this.memberSince = memberSince;
		
	}
	
	public List<Member> all(){
		return new ArrayList<Member>();
	}
	
	public Member insert() {
		
		// TODO
		// insert to database
		// if success return member, else return null
		return new Member();
		
	}

}
