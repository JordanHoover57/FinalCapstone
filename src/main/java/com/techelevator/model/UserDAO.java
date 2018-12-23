package com.techelevator.model;

import org.springframework.stereotype.Repository;

public interface UserDAO {

	public void saveUser(String userName, String password, String role, String emailAddress, String firstName, String lastName);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public Object getUserByUserName(String userName);
	
	public void createDefaultCollection(String username);
	
	public void createDefaultWishList(String username);
	
	public void updateUserRole(String username);

}
