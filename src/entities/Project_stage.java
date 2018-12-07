package entities;

import java.io.Serializable;

public class Project_stage implements Serializable{

    public final static int NO_INICIADA = 1;
	public final static int EN_CURSO = 2;
	public final static int FINALIZADA = 3;
	private int state;
	private int id;
	private Employee employee;
	private Stage stage;
	private Project project;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getState() {
		switch(state) {
		case NO_INICIADA: return "No iniciada"  ;
		case EN_CURSO: return "En curso" ;
		case FINALIZADA: return "Finalizada" ;
		}
		return "";
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
