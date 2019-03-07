package controllers;
import java.sql.SQLException;
import java.util.ArrayList;
import data.ActivityData;
import data.StageData;
import entities.Activity;
import entities.Stage;



public class StageController {
	private StageData stageData;
	
	// Constructor
	public StageController() {
		stageData = new StageData();
	}
	
	// Metodos
	public ArrayList<Stage> getAll(){
		try {
			return stageData.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Stage getStageByName(String nameStage) {
		try {
			return stageData.getStageByName(nameStage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}


	

	