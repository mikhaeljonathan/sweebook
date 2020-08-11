package view;

import controller.MemberHandler;

public class LoginForm {

	public LoginForm() {
		MemberHandler mh = new MemberHandler();
		mh.showCreateMembershipForm();
	}
	
}
