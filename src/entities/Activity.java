package entities;

import java.io.Serializable;

public class Activity implements Serializable{
	
	//MODIFICAR CLASE, EL ESTADO NO VA ACA.
    public final static int NO_INICIADA = 1;
	public final static int EN_CURSO = 2;
	public final static int FINALIZADA = 3;
    
	// Variables tabla original
	private int id_actividad;
	private String duration;
	private String description;
	
	// Variables tabla intermedia
	private Stage stage;
	private int state;
	
	// Metodos 
	public int getId() {
		return id_actividad;
	}
	
	public void setId(int id) {
		this.id_actividad = id;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getDescription() {
		return description;
	}	
	
	public void setDescription(String description) {
		this.description = description;
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
		return this.state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
		
	
}
