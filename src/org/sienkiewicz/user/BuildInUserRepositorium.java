package org.sienkiewicz.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sienkiewicz.api.UserRepositorium;
import org.springframework.stereotype.Repository;

@Repository
public class BuildInUserRepositorium implements UserRepositorium{
	
	private List<User> usersList = new ArrayList<User>();

	public void add(User user) {
		if(!ifExsist(user)) {
			usersList.add(user);
		} 
	}
	
	private boolean ifExsist(User user) {
		
		System.out.println("W poszukiwaniu : " + user.getLogin());
		
		boolean ifExisist = usersList.stream().filter(item -> item.getLogin().equals(user.getLogin()))
		.findAny().isPresent();
				
		return ifExisist;
	}
	
	public List<User> getAll(){
		return usersList;
	}

}
