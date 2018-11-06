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

	public void deleteUser(int idUser) {
		// TODO Auto-generated method stub
		this.getClientData().deleteUser(idUser);

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

}
