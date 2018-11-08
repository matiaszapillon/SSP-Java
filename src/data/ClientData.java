package data;

import java.sql.PreparedStatement;
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
			if (rs != null && rs.next()) {
				c = new Client();
				c.setAddress(rs.getString("address"));
				c.setBusiness_name(rs.getString("business_name"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setId(rs.getInt("id_client"));
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
		// Eliminar usuario

	}

	public ArrayList<Client> getClientsWithoutUser() throws SQLException {
		ArrayList<Client> clients = new ArrayList<Client>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM client where id_user is null";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null) {
			while (rs.next()) {
				Client c = new Client();
				c.setEmail(rs.getString("email"));
				c.setAddress(rs.getString("address"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setId(rs.getInt("id_client"));
				c.setBusiness_name(rs.getString("business_name"));
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

	public Client getClientById(int idPerson) throws SQLException {
		Client c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
				"select * from client where id_client=?");
		stmt.setInt(1, idPerson);
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			c = new Client();
			c.setBusiness_name(rs.getString("business_name"));
			c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
			c.setId(rs.getInt("id_client"));
			c.setEmail(rs.getString("email"));
			c.setAddress(rs.getString("address"));
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}

		return c;

	}

	public void addUser(Client c) throws SQLException {
		PreparedStatement stmt = null ;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement("update client set id_user = ? where id_client =?");
		stmt.setInt(1, c.getUser().getId());
		stmt.setInt(2, c.getId());
		stmt.executeUpdate();
		
		try {
			if(stmt != null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
