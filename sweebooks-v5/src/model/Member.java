package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.MySQLAccess;

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
		
		// Insert member into DAO
		String insertToMember = "INSERT INTO members "
				+ "VALUE('%s', '%s', '%s')";
		insertToMember = String.format(insertToMember, id, address, memberSince);
		
		try {
			
			MySQLAccess.stmt.execute(insertToMember);
			return this;
			
		} catch (Exception e) {
			
			// Fail to insert to DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
	}

}
