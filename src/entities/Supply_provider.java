package entities;

import java.io.Serializable;

public class Supply_provider implements Serializable{
//CREO QUE ESTA CLASE INTERMEDIA SI VA, O AL MENOS DEBERIA HABER UNA CLASE PARA MOSTRAR TODA LA INFO
	//DEL PROVEDOR Y EL INSUMO CON EL PRECIO Y DEMAS, YA QUE EL PRECIO POR EJEMPLO DEPENDE DE AMBOS A LA VEZ
	
	private int id;
	private Supply supply;
	private Provider provider;
	private float prize;
	private String currency;
	private boolean active;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Supply getSupply() {
		return supply;
	}
	public void setSupply(Supply supply) {
		this.supply = supply;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
}
