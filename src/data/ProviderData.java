package data;

import java.sql.*;
import java.util.ArrayList;

import entities.*;


public class ProviderData {
	
	public ArrayList<Provider> getAll() throws SQLException {
		ArrayList<Provider> providers = new ArrayList<Provider>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM provider";
		
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		
		if(rs != null) {
			while(rs.next()) {
				Provider p = new Provider();
				p.setId(rs.getInt("id_provider"));
				p.setBusiness_name(rs.getString("business_name"));
				p.setName(rs.getString("name"));
				p.setSurname(rs.getString("surname"));
				p.setState(rs.getInt("state"));
				p.setDescription(rs.getString("description"));
				p.setCategory(rs.getInt("category"));
				p.setEmail(rs.getString("email"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				
				providers.add(p);
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
		return providers;
	}
	
	public Provider getProviderById(int id) throws SQLException {
		Provider p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT * FROM provider WHERE id_provider = ?";
		
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		stmt.setInt(1, id);
		
		rs = stmt.executeQuery(SqlQuery);
		
		if(rs != null) {
			while(rs.next()) {
				p = new Provider();
				
				p.setId(id);
				p.setBusiness_name(rs.getString("business_name"));
				p.setName(rs.getString("name"));
				p.setSurname(rs.getString("surname"));
				p.setState(rs.getInt("state"));
				p.setDescription(rs.getString("description"));
				p.setCategory(rs.getInt("category"));
				p.setEmail(rs.getString("email"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				
				return p;
			}
		}
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Aca se devuelve con null si no lo encontró
		return p;
	}
	
	public void create (Provider p) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
				"INSERT INTO provider (business_name, name, surname, state, description, category, email, address, phone) values (?,?,?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		stmt.setString(1, p.getBusiness_name());
		stmt.setString(2, p.getName());
		stmt.setString(3, p.getSurname());
		stmt.setString(4, p.getState());
		stmt.setString(5, p.getDescription());
		switch(p.getCategory()) {
			case "A": stmt.setInt(6, Provider.CATEGORY_A); 
			case "B": stmt.setInt(6, Provider.CATEGORY_B); 
			case "C": stmt.setInt(6, Provider.CATEGORY_C); 
		}
		stmt.setString(7, p.getEmail());
		stmt.setString(8, p.getAddress());
		stmt.setString(9, p.getPhone());
		stmt.executeUpdate();
		
		keyResultSet = stmt.getGeneratedKeys();
		
		if (keyResultSet != null && keyResultSet.next()) {
			p.setId(keyResultSet.getInt(1));
		}

		try {
			if (keyResultSet != null)
				keyResultSet.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update (Provider p) throws SQLException {
		PreparedStatement stmt = null;
		stmt = FactoryConexion.getInstancia().getConn()
				.prepareStatement("UPDATE provider SET business_name = ? ,name = ?, surname = ?, state = ?, description = ?, category = ?, email = ?, address = ?, phone = ? WHERE id_provider = ?");
		
		stmt.setString(1, p.getBusiness_name());
		stmt.setString(2, p.getName());
		stmt.setString(3, p.getSurname());
		stmt.setString(4, p.getState());
		stmt.setString(5, p.getDescription());
		switch(p.getCategory()) {
			case "A": stmt.setInt(6, Provider.CATEGORY_A); 
			case "B": stmt.setInt(6, Provider.CATEGORY_B); 
			case "C": stmt.setInt(6, Provider.CATEGORY_C); 
		}
		stmt.setString(7, p.getEmail());
		stmt.setString(8, p.getAddress());
		stmt.setString(9, p.getPhone());
		
		stmt.setInt(10, p.getId());
		
		stmt.executeUpdate();
		
		try {
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete (int idProv) throws SQLException {
		PreparedStatement stmt = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement("DELETE FROM provider where id_provider = ?");
		stmt.executeUpdate();
		
		try {
			if(stmt != null) {
				stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
