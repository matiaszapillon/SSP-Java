package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Project implements Serializable{

	private int id;
	private String name;
	private String description;
	private Client client;
	private ArrayList<Supply> supplies = new ArrayList<Supply>();
	private ArrayList<Stage> stages = new ArrayList<Stage>();
	private float totalCost;
	
	
	
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Stage> getStages() {
		return stages;
	}
	
	public void setStages(ArrayList<Stage> stages) {
		this.stages = stages;
	}
	
	public ArrayList<Supply> getSupplies() {
		return supplies;
	}
	
	public void setSupplies(ArrayList<Supply> supplies) {
		this.supplies = supplies;
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
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public String getState() {	
		boolean flag = false;
		
		// Validar si hay etapas cargadas
		if(this.getStages().isEmpty()) {
			return "No Iniciado";
		} 
		// Validar si todas las etapas estan finalizadas 
		// Caso verdadero --> Proyecto Finalizado
		// Sino --> Proyecto en Curso
		for(Stage stage : getStages()) {			
			if(stage.getIdState() == Stage.FINALIZADA) {
				flag = true;
			} else {
				flag = false;
				break;				
			}
		}
		
		if(flag) {
			return "Finalizado";
		} else {
			return "En Curso";
		}
			
	}
	
	public Stage getCurrentStage() {
		if(this.getStages()!=null) {
			for (Stage stage : this.getStages()) {
				if(stage.getIdState() == Stage.EN_CURSO) {
					return stage;
				}
		}}
	return null;
	}
	
	public void calculateTotalCost(ArrayList<Supply> supplies) {
		float total = 0 ;
		for(Supply s: supplies) {
			total += ( s.getPrize() * s.getQuantity() );
		}
		this.setTotalCost(total);
	}
}
