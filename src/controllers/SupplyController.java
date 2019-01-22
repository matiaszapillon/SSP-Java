package controllers;

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
		return this.getSupplyData().getSuppliesByProject(idProject);
		
	}
	
	
}
