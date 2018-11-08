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

	public Client getClientByIdUser(int id) {

		return this.getClientData().getClientByIdUser(id);
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

}
