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
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select s.id_supply, s.name as 'name supply', s.description as 'description supply', s.unity, s.stock, sp.id_supply_provider,\n" + 
					"sp.prize, sp.currency, sp.active, p.id_provider,p.business_name,\n" + 
					"p.address, p.email,p.phone, p.name as 'name provider', p.surname, p.state,\n" + 
					"p.description as 'description provider', p.category, ps.quantity\n" + 
					"from supply s inner join supply_provider sp on s.id_supply = sp.id_supply_provider\n" + 
					"inner join project_supply ps on ps.id_supply = s.id_supply\n" + 
					"inner join provider p on ps.id_provider = p.id_provider\n" + 
					"where ps.id_project = ?");
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

}
