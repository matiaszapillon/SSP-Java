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
	private Provider provider;
	private float prize;
	private String currency;
	private boolean active;
	
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public float getPrize() {
		return prize;
	}
	
	public void setPrize(float prize) {
		this.prize = prize;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
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
	
	@Override
	public boolean equals(Object obj) {
	    return (obj instanceof Supply) && this.id == ((Supply)obj).getId();
	}
	
	
}
