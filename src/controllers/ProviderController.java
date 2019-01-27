package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import data.ProviderData;
import entities.Provider;

public class ProviderController {
	
	private ProviderData providerData;
	
	public ProviderController () {
		providerData = new ProviderData();
	}
	
	public ProviderData getProviderData(){
		return providerData;
	}
    
	public void setProviderData(ProviderData provData) {
		this.providerData = provData;
	}
	
	public ArrayList<Provider> getAll() {
		try {
			return providerData.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Provider getProviderById(int id) {
		try {
			return this.providerData.getProviderById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void create(Provider prov) {
		try {
			this.providerData.create(prov);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 
	public void update(Provider prov) {
		// TODO Auto-generated method stub
		try {
			this.providerData.update(prov);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int idProv) {
		try {
			this.providerData.delete(idProv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public ArrayList<Provider> getProvidersByIdSupply(int idSupply) {
		try {
			return this.providerData.getProvidersByIdSupply(idSupply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
}