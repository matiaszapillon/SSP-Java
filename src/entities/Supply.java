package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Supply implements Serializable{

	private int id;
	private String name;
	private String description;
	private String unity ;
	private int stock;
	private int quantity;
	private ArrayList<Supply_provider> supply_provider = new ArrayList<Supply_provider>();
	
	
	public ArrayList<Supply_provider> getSupply_provider() {
		return supply_provider;
	}
	public void setSupply_provider(ArrayList<Supply_provider> supply_provider) {
		this.supply_provider = supply_provider;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUnity() {
		return unity;
	}
	public void setUnity(String unity) {
		this.unity = unity;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
