package data;

import java.sql.*;
import entities.*;

public class UserData {

	public boolean isUserValid(String nombreUsuario, String pw) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "select * from user where username ="+nombreUsuario+" and password =" +pw+" " ;
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null) {
			return true;
		}
		return false;

	}
}
