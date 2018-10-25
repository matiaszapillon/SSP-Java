package entities;

import java.util.ArrayList;

public class Project {

	
	private String name;
	private String description;
	private Client client;
	private ArrayList<Supply> supplies = new ArrayList();
	
	
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
	
}
