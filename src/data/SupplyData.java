package data;

import java.sql.PreparedStatement;
import java.sql.* ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Client;
import entities.Project;
import entities.Provider;
import entities.Stage;
import entities.Supply;

public class SupplyData {

	public ArrayList<Supply> getSuppliesByProject(int idProject) throws SQLException  {
		// TODO Auto-generated method stub
		ArrayList<Supply> supplies = new ArrayList<Supply>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT s.id_supply, s.name as 'name supply', s.description as 'description supply', s.unity, s.stock, sp.id_supply_provider,\r\n" + 
					"	sp.prize, sp.currency, sp.active, p.id_provider,p.business_name,p.address, p.email,p.phone, p.name as 'name provider', \r\n" + 
					"    p.surname, p.state, p.description as 'description provider', p.category, ps.quantity\r\n" + 
					"FROM project_supply ps \r\n" + 
					"INNER JOIN supply s ON ps.id_supply = s.id_supply\r\n" + 
					"INNER JOIN supply_provider sp ON sp.id_supply = s.id_supply AND sp.id_provider = ps.id_provider\r\n" + 
					"INNER JOIN provider p ON p.id_provider = ps.id_provider\r\n" + 
					"WHERE ps.id_project = ?");
		stmt.setInt(1, idProject);
		rs = stmt.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				Supply s = new Supply();
				s.setName(rs.getString("name supply"));
				s.setId(rs.getInt("id_supply"));
				s.setDescription(rs.getString("description supply"));
				s.setUnity(rs.getString("unity"));
				s.setStock(rs.getInt("stock"));
				s.setActive(rs.getBoolean("active"));
				s.setCurrency(rs.getString("currency"));
				s.setPrize(rs.getInt("prize"));
				s.setQuantity(rs.getInt("quantity"));

				Provider p = new Provider();
				p.setDescription(rs.getString("description provider"));
				p.setId(rs.getInt("id_provider"));
				p.setName(rs.getString("name provider"));
				p.setAddress(rs.getString("address"));
				p.setBusiness_name(rs.getString("business_name"));
				p.setSurname(rs.getString("surname"));
				p.setCategory(rs.getInt("category"));
				p.setState(rs.getInt("state"));
				p.setEmail(rs.getString("email"));
				p.setPhone(rs.getString("phone"));

				s.setProvider(p);
				supplies.add(s);
			}
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return supplies;
	}

	public ArrayList<Supply> getAllSupplies() throws SQLException {
		ArrayList<Supply> supplies = new ArrayList<Supply>();
		Statement stmt = null;
		ResultSet rs = null;
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery("select * from supply");
		if (rs != null) {
			while (rs.next()) {
				Supply s = new Supply();
				s.setName(rs.getString("name"));
				s.setId(rs.getInt("id_supply"));
				s.setDescription(rs.getString("description"));
				s.setUnity(rs.getString("unity"));
				s.setStock(rs.getInt("stock"));
				supplies.add(s);
			}
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return supplies;
		
	}

	public void deleteSupplyFromProject(int idSupply, int idProject) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "delete from project_supply\n" + 
				"where id_project = ? and id_supply = ? ";
		
		// Armar statement 
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		prepStmt.setInt(2, idSupply);

		
		// Ejecutar query
		prepStmt.executeUpdate();
		
		// Cerrar conexion
		try {
			if(prepStmt != null) {
				prepStmt.close();
			}
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		
	}

	public void updateQuantityFromProject(int idProject, int idSupply, int quantity) throws SQLException {
		// TODO Auto-generated method stub

		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE project_supply SET quantity = ? WHERE id_project = ? AND id_supply = ?";
		
		// Armar statement 
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, quantity);
		prepStmt.setInt(2, idProject);
		prepStmt.setInt(3, idSupply);

		
		// Ejecutar query
		prepStmt.executeUpdate();
		
		// Cerrar conexion
		try {
			if(prepStmt != null) {
				prepStmt.close();
			}
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
