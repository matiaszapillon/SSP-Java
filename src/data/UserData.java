package data;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

public class UserData {

	public User isUserValid(String nombreUsuario, String pw) throws SQLException {
		User u = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "select * from user u where u.username = '" + nombreUsuario + "' and u.password = '" + pw
				+ "'";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null && rs.next()) {
			u = new User();
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setId(rs.getInt("id_user"));
			u.setType(rs.getInt("type"));
			u.setEmail(rs.getString("email"));
			return u;
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
		return u;

	}

	public ArrayList<User> getAll() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM user";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
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

	/*
	 * select u.* , c.business_name as 'Nombre' from user u inner join client c on
	 * u.id_user = c.id_user union select u.*, CONCAT( e.name ,' ' , e.surname) as
	 * 'Nombre' from user u inner join employee e on u.id_user = e.id_employee //
	 * ESTA CONSULTA TRAE UNA LISTA DE TODOS LOS USUARIOS Y TAMBIEN EL NOMBRE DEL
	 * CLIENTE O EMPLEADO.
	 */
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
