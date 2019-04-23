package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import data.ActivityData;
import entities.Activity;

public class ActivityController {
	
	private ActivityData activityData;
	
	// Constructor
	public ActivityController() {
		activityData = new ActivityData();
	}
	
	// Metodos
	public ArrayList<Activity> getAll(){
		try {
			return activityData.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Prueba
			String mensaje_error = e.getMessage();
			System.out.println(mensaje_error);
			
		}
		return null;
	}
	
	public Activity getActivityById(int idAct) {
		try {
			return activityData.getActivityById(idAct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void createActivity(Activity a) {
		try {
			activityData.createActivity(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateActivity(Activity a) {
		try {
			activityData.updateActivity(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteActivity(int idAct) {
		try {
			activityData.deleteActivity(idAct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
