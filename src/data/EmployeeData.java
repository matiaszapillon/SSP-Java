package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Client;
import entities.Employee;
import entities.User;

public class EmployeeData {

	public ArrayList<Employee> getAll() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM employee";
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		// Ejecutar query
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null) {
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id_employee"));
				e.setDNI(rs.getString("DNI"));
				e.setName(rs.getString("name"));
				e.setSurname(rs.getString("surname"));
				e.setAddress(rs.getString("address"));
				e.setPhone(rs.getString("phone"));
				e.setEmail(rs.getString("email"));
				User u = new User();
				u.setId(rs.getInt("id_user"));
				e.setUser(u);
				employees.add(e);
			}
		}
		// Cerrar conexion
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {

			ex.printStackTrace();
		}
		return employees;
	}

	public Employee getEmployeeByIdUser(int id_user) throws SQLException {
		Employee e = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String SqlQuery = "select * from employee where id_user = ?";
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		stmt.setInt(1, id_user);
		// Ejecutar Query
		rs = stmt.executeQuery();

		if (rs != null && rs.next()) {
			e = new Employee();
			e.setAddress(rs.getString("address"));
			e.setDNI(rs.getString("DNI"));
			e.setName(rs.getString("name"));
			e.setId(rs.getInt("id_employee"));
			e.setSurname(rs.getString("surname"));
			e.setPhone(rs.getString("phone"));
			e.setPhone(rs.getString("email"));
			
			return e;
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

		return e;

	}

	public void deleteUser(int idUser) {
		// TODO Auto-generated method stub//
		// Eliminar usuario.
	}

	public ArrayList<Employee> getEmployeesWithoutUser() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = new ArrayList<Employee>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM employee where id_user is null";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null) {
			while (rs.next()) {
				Employee e = new Employee();
				e.setDNI(rs.getString("DNI"));
				e.setAddress(rs.getString("address"));
				e.setId(rs.getInt("id_employee"));
				e.setName(rs.getString("name"));
				e.setSurname(rs.getString("surname"));
				e.setPhone(rs.getString("surname"));
				employees.add(e);
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
		return employees;
	}

	public Employee getEmployeeById(int idPerson) throws SQLException {
		Employee e = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from employee where id_employee=?");
		stmt.setInt(1, idPerson);
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			e = new Employee();
			User u = new User();
			u.setId(rs.getInt("id_user"));
			e.setAddress(rs.getString("address"));
			e.setDNI(rs.getString("DNI"));
			e.setName(rs.getString("name"));
			e.setId(rs.getInt("id_employee"));
			e.setPhone(rs.getString("phone"));
			e.setSurname(rs.getString("surname"));
			e.setUser(u);
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

		return e;
	}

	public void addUser(Employee e) throws SQLException {
		PreparedStatement stmt = null;
		stmt = FactoryConexion.getInstancia().getConn()
				.prepareStatement("UPDATE employee SET id_user = ? WHERE id_employee = ?");
		stmt.setInt(1, e.getUser().getId());
		stmt.setInt(2, e.getId());
		stmt.executeUpdate();

		try {
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
