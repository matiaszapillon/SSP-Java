package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import data.SupplyData;
import entities.Supply;

public class SupplyController {

	private data.SupplyData supplyData;
	
	
	public SupplyController() {
		supplyData = new SupplyData();
	}


	public data.SupplyData getSupplyData() {
		return supplyData;
	}


	public void setSupplyData(data.SupplyData supplyData) {
		this.supplyData = supplyData;
	}


	public ArrayList<Supply> getSuppliesByProject(int idProject) {
		// TODO Auto-generated method stub
		try {
			return this.getSupplyData().getSuppliesByProject(idProject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}


	public ArrayList<Supply> getAllSupplies() {
		try {
			return this.supplyData.getAllSupplies();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public void deleteSupplyFromProject(int idSupply, int idProject) {
		// TODO Auto-generated method stubpublic
		try { this.supplyData.deleteSupplyFromProject(idSupply,idProject);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	public void updateQuantityFromProject(int idProject, int idSupply, int quantity) {
		// TODO Auto-generated method stub
		try { this.supplyData.updateQuantityFromProject(idProject,idSupply, quantity);
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
