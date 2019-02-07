package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Project;
import entities.Stage;

public class ProjectController {
	
	private data.ProjectData projectData ;
	
	public data.ProjectData getProjectData() {
		return projectData;
	}

	public void setProjectData(data.ProjectData projectData) {
		this.projectData = projectData;
	}

	public ProjectController() {
		projectData = new data.ProjectData();
	}

	public Project getProjectById(int idProject) {
		try {
			return this.getProjectData().getProjectById(idProject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Project> getAll() {
		try {
			return this.getProjectData().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addSupplyToProject(int idProject, int idSupply, int idProvider, int quantity) {
		try {
			this.projectData.addSupplyToProject(idProject,idSupply,idProvider,quantity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Project getProjectWithStages(int idProject) {
		try {
			return projectData.getProjectWithStages(idProject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Stage> getStagesOutOfProject(int idProject){
		try {
			return projectData.getStagesOutOfProject(idProject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addStageToProject(int idProject, int idStage) {
		try {
			projectData.addStageToProject(idProject, idStage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
