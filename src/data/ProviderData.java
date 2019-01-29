package data;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

public class ProviderData {

	// PreparedStatement unico (mayor seguridad y eficiencia)
	// PreparedStatement prepStmt = null;

	public ArrayList<Provider> getAll() throws SQLException {
		ArrayList<Provider> providers = new ArrayList<Provider>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM provider";
		
		// Preparer statement
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		// Ejecutar Query
		rs = stmt.executeQuery(SQLQuery);

		if (rs != null) {
			while (rs.next()) {
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
		
		// Cerrar conexion
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
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT * FROM provider WHERE id_provider = ?";

		// Crear el PreparedStatement de la conexi�n
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, id);
		
		// Ejecutar Query
		rs = prepStmt.executeQuery();

		if (rs != null) {
			while (rs.next()) {
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

			}
		}
		
		// Cerrar conexion
		try {
			if (rs != null)
				rs.close();
			if (prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	public void create(Provider p) throws SQLException {
		PreparedStatement prepStmt = null;
		ResultSet keyResultSet = null;
		String SqlQUery = "INSERT INTO provider (business_name, name, surname, state, description, category, email, address, phone) values (?,?,?,?,?,?,?,?,?)";

		// Crear el PreparedStatement de la conexi�n
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQUery,
				PreparedStatement.RETURN_GENERATED_KEYS);
		prepStmt.setString(1, p.getBusiness_name());
		prepStmt.setString(2, p.getName());
		prepStmt.setString(3, p.getSurname());
		switch (p.getState()) {
			case "APROBADO":
				prepStmt.setInt(4, Provider.APROBADO);
				break;
			case "DESAPROBADO":
				prepStmt.setInt(4, Provider.DESAPROBADO);
				break;
		}
		prepStmt.setString(5, p.getDescription());
		switch (p.getCategory()) {
			case "A":
				prepStmt.setInt(6, Provider.CATEGORY_A);
				break;
			case "B":
				prepStmt.setInt(6, Provider.CATEGORY_B);
				break;
			case "C":
				prepStmt.setInt(6, Provider.CATEGORY_C);
				break;
		}
		prepStmt.setString(7, p.getEmail());
		prepStmt.setString(8, p.getAddress());
		prepStmt.setString(9, p.getPhone());

		// Ejecutar Query
		prepStmt.executeUpdate();

		// Traer claves generadas
		keyResultSet = prepStmt.getGeneratedKeys();
		if (keyResultSet != null && keyResultSet.next()) {
			p.setId(keyResultSet.getInt(1));
		}
		
		// Cerrar conexion
		try {
			if (keyResultSet != null)
				keyResultSet.close();
			if (prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Provider p) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE provider SET business_name = ? ,name = ?, surname = ?, state = ?, description = ?, category = ?, email = ?, address = ?, phone = ? WHERE id_provider = ?";

		// Crear el PreparedStatement de la conexi�n
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setString(1, p.getBusiness_name());
		prepStmt.setString(2, p.getName());
		prepStmt.setString(3, p.getSurname());
		switch (p.getState()) {
			case "APROBADO":
				prepStmt.setInt(4, Provider.APROBADO);
				break;
			case "DESAPROBADO":
				prepStmt.setInt(4, Provider.DESAPROBADO);
				break;
		}
		prepStmt.setString(5, p.getDescription());
		switch (p.getCategory()) {
			case "A":
				prepStmt.setInt(6, Provider.CATEGORY_A);
				break;
			case "B":
				prepStmt.setInt(6, Provider.CATEGORY_B);
				break;
			case "C":
				prepStmt.setInt(6, Provider.CATEGORY_C);
				break;
		}
		prepStmt.setString(7, p.getEmail());
		prepStmt.setString(8, p.getAddress());
		prepStmt.setString(9, p.getPhone());
		prepStmt.setInt(10, p.getId());

		// Ejecutar la Query
		prepStmt.executeUpdate();
		
		// Cerrar conexion
		try {
			if (prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int idProv) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQUery = "DELETE FROM provider where id_provider = ?";

		// Crear el PreparedStatement de la conexi�n
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQUery);
		prepStmt.setInt(1, idProv);

		// Ejecutar la Query
		prepStmt.executeUpdate();
		// Cerrar conexion
		try {
			if (prepStmt != null) {
				prepStmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public ArrayList<Supply> getProvidersByIdSupply(int idSupply) throws SQLException {
		ArrayList<Supply> supplies = new ArrayList<Supply>();
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement("\n" + 
				"SELECT p.id_provider,p.name as 'name provider', p.surname, p.state, p.description, p.category,\n" + 
				"p.email, p.address, p.phone, p.business_name, s.id_supply, sp.prize, sp.currency, sp.active, s.name as 'name supply' \n" + 
				"FROM provider p inner join supply_provider sp on p.id_provider = sp.id_provider\n" + 
				"inner join supply s on s.id_supply = sp.id_supply\n" + 
				"where s.id_supply = ?");
		prepStmt.setInt(1, idSupply);
		// Ejecutar Query
		rs = prepStmt.executeQuery();

		
		if(rs != null) {
			while(rs.next()) {
				Supply s = new Supply();
				s.setId(rs.getInt("id_supply"));
				s.setName(rs.getString("name supply"));
				s.setPrize(rs.getFloat("prize"));
				s.setCurrency(rs.getString("currency"));
				s.setActive(rs.getBoolean("active"));
				
				Provider p = new Provider();
				p.setId(rs.getInt("id_provider"));
				p.setBusiness_name(rs.getString("business_name"));
				p.setName(rs.getString("name provider"));
				p.setSurname(rs.getString("surname"));
				p.setState(rs.getInt("state"));
				p.setDescription(rs.getString("description"));
				p.setCategory(rs.getInt("category"));	
				
				s.setProvider(p);
				supplies.add(s);
			}
		}
		// Cerrar conexiones
		try {
			if (rs != null)
				rs.close();
			if (prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return supplies;
	}
	

}
