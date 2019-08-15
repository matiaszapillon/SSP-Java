package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Client;
import entities.User;

public class ClientData {

	// Retornar todos
	public ArrayList<Client> getAll() throws SQLException {
		ArrayList<Client> clients = new ArrayList<Client>();
		Statement stmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT * FROM client";

		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().createStatement();

		// Ejecutar consulta
		rs = stmt.executeQuery(SqlQuery);

		if (rs != null) {
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id_user"));

				Client c = new Client();
				c.setId(rs.getInt("id_client"));
				c.setBusiness_name(rs.getString("business_name"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setAddress(rs.getString("address"));
				c.setEmail(rs.getString("email"));
				c.setUser(u);

				clients.add(c);
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

		return clients;
	}

	// Retornar cliente por ID de usuario
	public Client getClientByIdUser(int id) throws SQLException {
		Client c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "select * from client where id_user = ? ";
		
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SQLQuery);
		stmt.setInt(1, id);
		
		// Ejecutar Query
		rs = stmt.executeQuery();
		
		if (rs != null && rs.next()) {
			c = new Client();
			
			c.setAddress(rs.getString("address"));
			c.setBusiness_name(rs.getString("business_name"));
			c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
			c.setId(rs.getInt("id_client"));
			c.setEmail(rs.getString("email"));
		}
		// Cerrar Conexion
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
			String mensaje_customizado = "No se ha podido cerrar la conexion a DB";
			System.out.println(mensaje_customizado);
		}
		return c;
	}

	// Retornar clientes sin Usuario
	public ArrayList<Client> getClientsWithoutUser() throws SQLException {
		ArrayList<Client> clients = new ArrayList<Client>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM client WHERE id_user is null";
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		// Ejecutar query
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
		// Cerrar conexion
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
			String mensaje_customizado = "No se ha podido cerrar la conexion a DB";
			System.out.println(mensaje_customizado);
		}
		return clients;

	}

	// Retornar cliente por ID
	public Client getClientById(int idPerson) throws SQLException {
		Client c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String SQlQuery = "SELECT * FROM client WHERE id_client = ?";
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SQlQuery);
		stmt.setInt(1, idPerson);
		// Ejecutar query
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id_user"));

			c = new Client();
			c.setBusiness_name(rs.getString("business_name"));
			c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
			c.setId(rs.getInt("id_client"));
			c.setEmail(rs.getString("email"));
			c.setAddress(rs.getString("address"));
			// Agrego id usuario
			c.setUser(u);
		}
		// Cerrar conexion
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			String mensaje_customizado = "No se ha podido cerrar la conexion a DB";
			System.out.println(mensaje_customizado);
			throw e; // cuando se usa?
		}
		return c;
	}

	// Agregar nuevo ususario para el cliente
	public void addUser(Client c) throws SQLException {
		PreparedStatement stmt = null;
		String SqlQuery = "UPDATE client SET id_user = ? WHERE id_client = ?";
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		stmt.setInt(1, c.getUser().getId());
		stmt.setInt(2, c.getId());
		// Ejecutar query
		stmt.executeUpdate();
		// Cerrar conexion
		try {
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Agregar un nuevo cliente
	public void createClient(Client c) throws SQLException {
		PreparedStatement prepStmt = null;
		ResultSet keyResultSet = null;
		String SqlQuery = "INSERT INTO client (business_name, CUIT_CUIL, address, email, id_user) VALUES (?,?,?,?,?)";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery,
				PreparedStatement.RETURN_GENERATED_KEYS);
		prepStmt.setString(1, c.getBusiness_name());
		prepStmt.setString(2, c.getCUIT_CUIL());
		prepStmt.setString(3, c.getAddress());
		prepStmt.setString(4, c.getEmail());
		if (c.getUser() == null) {
			prepStmt.setNull(5, java.sql.Types.INTEGER);
		} else {
			prepStmt.setInt(5, c.getUser().getId());
		}

		// Ejecutar query
		prepStmt.executeUpdate();

		keyResultSet = prepStmt.getGeneratedKeys();
		if (keyResultSet != null && keyResultSet.next()) {
			c.setId(keyResultSet.getInt(1));
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

	// Modificar Cliente
	public void updateClient(Client c) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE client \r\n"
				+ "SET business_name = ?, CUIT_CUIL = ?, address = ?, email = ?, id_user = ? \r\n"
				+ "WHERE id_client = ?";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setString(1, c.getBusiness_name());
		prepStmt.setString(2, c.getCUIT_CUIL());
		prepStmt.setString(3, c.getAddress());
		prepStmt.setString(4, c.getEmail());
		if (c.getUser() == null) {
			prepStmt.setNull(5, java.sql.Types.INTEGER);
		} else {
			prepStmt.setInt(5, c.getUser().getId());
		}
		prepStmt.setInt(6, c.getId());

		// Ejecutar query
		prepStmt.executeUpdate();

		try {
			if (prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Eliminar cliente
	public void deleteClient(int id_cli) throws SQLException {
		PreparedStatement updateProject = null;
		PreparedStatement deleteClient = null;
		Connection con = FactoryConexion.getInstancia().getConn();

		try {
			con.setAutoCommit(false);
			// Armar update query statement
			// Setear null el id_client en los proyectos del cliente que se elimina
			updateProject = con.prepareStatement("UPDATE project SET id_client = ? WHERE id_client = ?");
			updateProject.setNull(1, java.sql.Types.INTEGER);
			updateProject.setInt(2, id_cli);
			updateProject.executeUpdate();
			// Armar delete query statement
			deleteClient = con.prepareStatement("DELETE FROM client WHERE id_client = ?");
			deleteClient.setInt(1, id_cli);
			deleteClient.executeUpdate();

			con.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (updateProject != null) {
				updateProject.close();
			}
			if (deleteClient != null) {
				deleteClient.close();
			}
			con.setAutoCommit(true);
		}
	}

	// Eliminar usuario de un cliente
	public void deleteUser(int id_cli) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE client SET id_user = null WHERE id_client = ?";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, id_cli);

		// Ejecutar query
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

}
