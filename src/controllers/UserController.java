package controllers;

import java.sql.SQLException;

import data.*;
import entities.User;

public class UserController {

	private data.UserData userData;

	public UserController() {
		userData = new data.UserData();
	}

	public data.UserData getUserData() {
		return userData;
	}

	public void setUserData(data.UserData userData) {
		this.userData = userData;
	}

	public User isUserValid(String name, String pw) {
		
			try {
				return getUserData().isUserValid(name, pw);
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			return null;	
	}

/*	public User getUserByCredentials(String username, String password) {
		
		User u = new User();
		try {
			u = this.getUserData().getUserByCredentials(username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	} */

}
