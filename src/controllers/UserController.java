package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<User> getAll() {
		try {
			return getUserData().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public User getUserById(int id) {
		try {
			return this.getUserData().getUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUser(int idUser) {
		this.getUserData().deleteUser(idUser) ;
		
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
