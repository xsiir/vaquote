package org.sienkiewicz.user;

import org.springframework.context.annotation.Scope;

@Scope(value = "session")
public class LoggedUser {

	private String name;
	private Integer age;

	public LoggedUser(User user) {
		
	}


	
}
