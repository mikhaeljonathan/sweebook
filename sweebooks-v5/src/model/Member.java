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
		
		List<Member> lm = new ArrayList<Member>();
		
		// Retrieve all member data from DAO
		String findAllMembers = "SELECT * FROM members";
		
		try {
			
			MySQLAccess.rs = MySQLAccess.stmt.executeQuery(findAllMembers);
			
			while (MySQLAccess.rs.next()) {
				
				// Add Member object into List<Member>
				lm.add(new Member(MySQLAccess.rs.getString("user_id"), 
						MySQLAccess.rs.getString("address"),
						MySQLAccess.rs.getString("member_since")));
				
			}
			
			return lm;
			
		} catch (Exception e) {
			
			// Fail to retrieve from DAO
			JOptionPane.showMessageDialog(null, "Database error");
			return null;
			
		}
		
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

	// Getter and Setter
	
	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		
		this.id = id;
		
	}

	public String getAddress() {
		
		return address;
		
	}

	public void setAddress(String address) {
		
		this.address = address;
		
	}

	public String getMemberSince() {
		
		return memberSince;
		
	}

	public void setMemberSince(String memberSince) {
		
		this.memberSince = memberSince;
		
	}
	
}
