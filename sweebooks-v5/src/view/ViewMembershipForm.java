package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import controller.MemberHandler;
import model.Member;

public class ViewMembershipForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	public ViewMembershipForm() {
		
		List<Member> lm = new ArrayList<Member>();
		lm = new MemberHandler().getAll();
		
		// TODO: show lm here:
		
	}

}
