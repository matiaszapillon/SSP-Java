package entities;

import java.io.Serializable;
import entities.Employee;

public class Stage implements Serializable {

	//EN BASE DE DATOS EL ESTADO VA EN PROJECT_STAGE. EN CODIGO LO PONGO ACA MAS COMODO. 
	//( VER SI ALGUNA VEZ UTILIZAMOS LAS CLASES INTERMEDIAS, CREO Q ESTAN AL PEDO. EJ: PROJECT_STAGE, PROJECT_SUPPLY , etc.. )
	//PONIENDO EL ATRIBUTO EN LA CLASE YA ES SUFICIENTE, DESP POR BASE DE DATOS HAGO OTRAS COSAS.
	
	//PARA SIMPLIFICAR PUSIMOS QUE TODAS LAS ETAPAS SON CORRELATIVAS DE LA ANTERIOR. (TODAS DEPENDIENTES)
    public final static int NO_INICIADA = 1;
	public final static int EN_CURSO = 2;
	public final static int FINALIZADA = 3;
	private int id_stage;
	private String name;
	private String description;
	private int state;
	private Employee attendant;
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getState() {
		switch(state) {
			case NO_INICIADA: return "No iniciada";
			case EN_CURSO: return "En curso";
			case FINALIZADA: return "Finalizada";
		}
		return "";
	}
	
	public int getIdState() {
		return state;
	}
	
	public int getId() {
		return id_stage;
	}
	
	public void setId(int id) {
		this.id_stage = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setEmployee(Employee employee) {
		this.attendant = employee;
	}
	
	public Employee getEmployee() {
		return attendant;
	}
	
}
