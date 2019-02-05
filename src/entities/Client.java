package entities;

import java.io.Serializable;

public class Client implements Serializable{

	private String CUIT_CUIL;
	private String business_name ;
	private String address;
	private String email;
	private User user;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getCUIT_CUIL() {
		return CUIT_CUIL;
	}
	
	public void setCUIT_CUIL(String cUIT_CUIL) {
		CUIT_CUIL = cUIT_CUIL;
	}
	
	public String getBusiness_name() {
		return business_name;
	}
	
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// ?
	public String toString() {
		return getBusiness_name();
	}
	
	
}
