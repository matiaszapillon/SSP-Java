package data;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

public class UserData {

	public User isUserValid(String user, String passw) throws SQLException {
		User u = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
		
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SQLQuery);
		stmt.setString(1, user);
		stmt.setString(2, passw);
		
		// Ejecutar query
		rs = stmt.executeQuery();
		
		if (rs != null && rs.next()) {
			u = new User();
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setId(rs.getInt("id_user"));
			u.setType(rs.getInt("type"));
			u.setEmail(rs.getString("email"));			
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
		return u;

	}

	public ArrayList<User> getAll() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM user";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		
		// Ejecutar query
		rs = stmt.executeQuery(SQLQuery);
		
		if (rs != null) {
			while (rs.next()) {
				User u = new User();
				
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setId(rs.getInt("id_user"));
				u.setType(rs.getInt("type"));
				u.setEmail(rs.getString("email"));
				
				users.add(u);
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
		return users;
	}

	public User getUserById(int id) throws SQLException {
		User u = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM user WHERE id_user = ?";
		
		// Armar el statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SQLQuery);
		stmt.setInt(1, id);
		
		// Ejecutar query
		rs = stmt.executeQuery();
		
		if (rs != null && rs.next()) {
			u = new User();
			
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setId(rs.getInt("id_user"));
			u.setType(rs.getInt("type"));
			u.setEmail(rs.getString("email"));
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
		return u;

	}

	public void deleteUserFromClient(int idUser) throws SQLException {
		PreparedStatement updateClient = null;
		PreparedStatement deleteUser = null;
		Connection con = FactoryConexion.getInstancia().getConn();
		try {
			con.setAutoCommit(false);
			// Armar update query statement
			updateClient = con.prepareStatement("UPDATE client SET id_user = ? WHERE id_user = ?");
			updateClient.setNull(1, java.sql.Types.INTEGER);
			updateClient.setInt(2, idUser);
			updateClient.executeUpdate();
			// Armar delete query statement
			deleteUser = con.prepareStatement("DELETE FROM user WHERE id_user = ?");
			deleteUser.setInt(1, idUser);
			deleteUser.executeUpdate();
			
			con.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
		} finally {
			if(updateClient != null) {
				updateClient.close();
			}
			if(deleteUser != null) {
				deleteUser.close();
			}
			con.setAutoCommit(true);
		}
	}

	public void deleteUserFromEmployee(int idUser) throws SQLException {
		// TODO Auto-generated method stub
		
		// Eliminar Usuario de tabla cliente o empleado y elimminar Usuario
		PreparedStatement updateEmployee = null;
		PreparedStatement deleteUser = null;
		Connection con = FactoryConexion.getInstancia().getConn();
		try {
			con.setAutoCommit(false);
			// Armar update query statement
			updateEmployee = con.prepareStatement("UPDATE employee SET id_user = ? WHERE id_user = ?");
			updateEmployee.setNull(1, java.sql.Types.INTEGER);
			updateEmployee.setInt(2, idUser);
			updateEmployee.executeUpdate();
			// Armar delete query statement
			deleteUser = con.prepareStatement("DELETE FROM user WHERE id_user = ?");
			deleteUser.setInt(1, idUser);
			deleteUser.executeUpdate();
			
			con.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
		} finally {
			if(updateEmployee != null) {
				updateEmployee.close();
			}
			if(deleteUser != null) {
				deleteUser.close();
			}
			con.setAutoCommit(true);
		}
	}

	public void create(User u) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		String SqlQuery = "INSERT INTO user (username, password, email, type) values (?,?,?,?)";
		
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, u.getUsername());
		stmt.setString(2, u.getPassword());
		stmt.setString(3, u.getEmail());
		stmt.setInt(4, u.getType());
		
		// Ejecutar query
		stmt.executeUpdate();
		
		// Recuperar claves generadas
		keyResultSet = stmt.getGeneratedKeys();
		if (keyResultSet != null && keyResultSet.next()) {
			u.setId(keyResultSet.getInt(1));
		}
		// Cerrar conexion
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

	public void update(User u) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String SqlQuery = "UPDATE user SET username = ?, email = ?, password = ?, type = ? WHERE id_user = ?";
		
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		stmt.setString(1, u.getUsername());
		stmt.setString(2, u.getEmail());
		stmt.setString(3, u.getPassword());
		stmt.setInt(4, u.getType());
		stmt.setInt(5, u.getId());
		
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

}

/*
 * public User getUserByCredentials(String us, String pw) throws Exception { //
 * TODO: // Consulta que permita traer el objeto User y tambien la tabla
 * employee o // client. ( NO SE COMO HACER ) User u = null; Statement stmt =
 * null; ResultSet rs = null; String SQLQuery =
 * "select * from user u inner join employee e where u.id_user = e.id_user and u.username = '"
 * + us + "' and u.password= '" + pw + "'"; try { stmt =
 * FactoryConexion.getInstancia().getConn().createStatement(); rs =
 * stmt.executeQuery(SQLQuery); if (rs != null && rs.next()) { u = new User();
 * u.setId(rs.getInt("id_user")); u.setUsername(rs.getString("username"));
 * u.setPassword(rs.getString("password")); u.setType(rs.getInt("type"));
 * u.setEmail(rs.getString("email")); if (u.getType() == User.CLIENT) { // EN
 * ESTE CASO SALE POR FALSO PORQUE HARDCODEE CODIGO PARA QUE SEA EMPLEADO Client
 * c = new Client(); c.setId(rs.getInt("id_client"));
 * c.setAddress(rs.getString("address"));
 * c.setBusiness_name(rs.getString("business_name"));
 * c.setCUIT(rs.getString("CUIT")); c.setCUIL(rs.getString("CUIL"));
 * c.setUser(u); u.setClient(c); } else { Employee e = new Employee();
 * e.setId(rs.getInt("id_employee")); e.setAddress(rs.getString("address"));
 * e.setDNI(rs.getString("DNI")); e.setName(rs.getString("name"));
 * e.setSurname(rs.getString("surname")); e.setPhone(rs.getString("phone"));
 * e.setUser(u); u.setEmployee(e); } } } catch (Exception e) { throw e; } return
 * u;
 * 
 * }
 */
