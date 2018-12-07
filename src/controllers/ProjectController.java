package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Project;

public class ProjectController {
	
	private data.projectData projectData ;
	
	public data.projectData getProjectData() {
		return projectData;
	}

	public void setProjectData(data.projectData projectData) {
		this.projectData = projectData;
	}

	public ProjectController() {
		projectData = new data.projectData();
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

}
