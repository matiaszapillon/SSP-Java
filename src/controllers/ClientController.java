package controllers;

import entities.Client;

public class ClientController {
	
	private data.ClientData clientData;

	public ClientController() {
		clientData = new data.ClientData() ;
	}
	public Client getClientByIdUser(int id) {
		
		return clientData.getClientByIdUser(id) ;
	}
	
	
	

}
