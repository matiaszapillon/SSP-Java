package entities;

import java.io.Serializable;

public class Project_supply implements Serializable{
	
	private int id;
	private Project project;
	private Supply supply;
	private int quantity;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Supply getSupply() {
		return supply;
	}
	public void setSupply(Supply supply) {
		this.supply = supply;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
