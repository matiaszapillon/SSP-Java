package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Client;
import entities.Employee;
import entities.User;

public class ClientData {

	public Client getClientByIdUser(int id) {

		Client c = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "select * from client where id_user = '" + id + "' ";
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(SQLQuery);
			if (rs != null) {
				c = new Client();
				c.setId(rs.getInt("id_client"));
				c.setAddress(rs.getString("address"));
				c.setBusiness_name(rs.getString("business_name"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setEmail(rs.getString("email"));
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return c;

	}

	public void deleteUser(int idUser) {
		// TODO Auto-generated method stub
		//Eliminar usuario
		
		
	}

	public ArrayList<Client> getClientsWithoutUser() throws SQLException {
		ArrayList<Client> clients = new ArrayList<Client>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM employee where id_user is null";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null) {
			while (rs.next()) {
				Client c = new Client();
				c.setBusiness_name(rs.getString("business_name"));
				c.setAddress(rs.getString("address"));
				c.setId(rs.getInt("id_client"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setEmail(rs.getString("email"));
				clients.add(c);
			}
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
		
	}

}
