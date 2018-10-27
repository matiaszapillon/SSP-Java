package entities;

import java.io.Serializable;

public class Stage implements Serializable {
	
    public final static int NO_INICIADA = 1;
	public final static int EN_CURSO = 2;
	public final static int FINALIZADA = 3;
	private int id ;
	private String name;
	private String description;
	private int state;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
