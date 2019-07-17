package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Project implements Serializable{

	private int id;
	private String name;
	private String description;
	private Client client;
	private ArrayList<Supply> supplies = new ArrayList<Supply>();
	private ArrayList<Stage> stages = new ArrayList<Stage>();
	private float totalCost = 0;
	private Date startDate;
	private Date endDate;
	
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
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
		// Caso especial que se cree un proyecto se cree las etapas y se comience el proyecto mas adelante.
	
		if(this.getStages().get(0).getIdState() == Stage.NO_INICIADA) {
			return "No iniciado";
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
	public boolean isFinished() {
		// TODO Auto-generated method stub
		boolean flag = false;
		for(Stage stage : getStages()) {			
			if(stage.getIdState() == Stage.FINALIZADA) {
				flag = true;
			} else {
				flag = false;
				break;				
			}
		}
		
		return flag;
			
		
	}
	public void sendEmailToClient(Client c,Project p) {
		String to =  "matiaszapillon@gmail.com" ; //c.getEmail();
		String subject = "The project has finished";
		String body = "Dear "+ c.getBusiness_name()+"your project "+ p.getName() + "has already finished, you can"
				+"check the state in the Web www.http://localhost:8080/SSP/logIn.html , cheers";
		util.Emailer.getInstance().send(to, subject, body);
	}
}
