package org.sienkiewicz.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final BuildInUserRepositorium userRepositorium;
	
	@Autowired
	public UserService(BuildInUserRepositorium userRepositorium) {
		super();
		this.userRepositorium = userRepositorium;
	}

	public void saveUser(User user) {
		userRepositorium.add(user);
	}
	
	public List<User> getAll(){
		return userRepositorium.getAll();
	}
	
	
	
	

}
