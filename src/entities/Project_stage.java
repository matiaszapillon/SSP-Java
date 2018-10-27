package entities;

import java.io.Serializable;

public class Project_stage implements Serializable{

	private int id;
	private Employee employee;
	private Stage stage;
	private Project project;
	public Employee getEmployee() {
		return employee;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
