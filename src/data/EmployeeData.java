package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Client;
import entities.Employee;

public class EmployeeData {

	public Employee getEmployeeByIdUser(int id) {
		Employee e = null;
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "select * from employee where id_user = '" + id + "' ";
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(SQLQuery);
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
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
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

}
