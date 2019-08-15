package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Client;

public class ClientController {

	private data.ClientData clientData;

	public ClientController() {
		clientData = new data.ClientData();
	}

	public data.ClientData getClientData() {
		return clientData;
	}

	public void setClientData(data.ClientData clientData) {
		this.clientData = clientData;
	}
	
	public ArrayList<Client> getAll(){
		try {
			return clientData.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Client getClientByIdUser(int id) {
		try {
			return this.getClientData().getClientByIdUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Client> getClientsWithoutUser() {
		try {
			return this.getClientData().getClientsWithoutUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Client getClientById(int idPerson) {
		try {
			return this.getClientData().getClientById(idPerson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(Client c) {
		// TODO Auto-generated method stub
		try {
			this.getClientData().addUser(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void createClient(Client c) {
		try {
			clientData.createClient(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateClient(Client c) {
		try {
			clientData.updateClient(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteClient(int id_cli) {
		try {
			clientData.deleteClient(id_cli);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUser(int id_cli) {
		try {
			clientData.deleteUser(id_cli);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
