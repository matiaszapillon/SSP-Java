package data;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

public class ActivityData  {

	
	public ArrayList<Activity> getAll() throws SQLException {
		ArrayList<Activity> activities = new ArrayList<Activity>();
		Statement stmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT a.*, s.description as 'description stage', s.name as 'name stage'\n" + 
				" FROM activity a INNER JOIN stage s ON a.id_stage = s.id_stage";
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		// Ejecutar query
		rs = stmt.executeQuery(SqlQuery);
		
		if(rs != null) {
			while(rs.next()) {
				Activity a = new Activity();
				Stage s = new Stage();
				
				a.setId(rs.getInt("id_activity"));
				a.setDescription(rs.getString("description"));
				a.setDuration(rs.getString("duration"));
				
				s.setId(rs.getInt("id_stage"));
				s.setDescription(rs.getString("description stage"));
				s.setName(rs.getString("name stage"));
				a.setStage(s);
				
				activities.add(a);
			}
		}
		
		// Cerrar conexion
		try {
			if(rs != null) 
				rs.close();
			if(stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return activities;
		
	}
	
	public Activity getActivityById(int id) throws SQLException {
		Activity a = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT a.*, s.description as 'description stage', s.name as 'name stage'\n" + 
				" FROM activity a INNER JOIN stage s ON a.id_stage = s.id_stage WHERE a.id_activity = ?";
		
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, id);
		
		// Ejecutar query
		rs = prepStmt.executeQuery();
		
		if(rs != null) {
			while(rs.next()) {
				a = new Activity();
				a.setId(id);
				a.setDescription(rs.getString("description"));
				a.setDuration(rs.getString("duration"));	
			
				Stage s = new Stage();

				s.setId(rs.getInt("id_stage"));
				s.setDescription(rs.getString("description stage"));
				s.setName(rs.getString("name stage"));
				a.setStage(s);
			}
		}
		
		// Cerrar conexion
		try {
			if(rs != null) 
				rs.close();
			if(prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return a;
	}
	
	public Stage getActivityStage(int id) throws SQLException {
		Stage s = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlString = "SELECT s.id_stage, s.name, s.description FROM stage s\r\n" + 
				"INNER JOIN activity a ON s.id_stage = a.id_stage\r\n" + 
				"WHERE a.id_activity = ?";
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlString);
		prepStmt.setInt(1, id);
		
		// Ejectuar query
		rs = prepStmt.executeQuery();
		
		if(rs != null && rs.next()) {
			s = new Stage();			
			s.setId(rs.getInt("id_stage"));
			s.setName(rs.getString("name"));
			s.setDescription(rs.getString("description"));
		}
		
		// Cerrar conexion
		try {
			if(rs != null) 
				rs.close();
			if(prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public void createActivity(Activity a) throws SQLException {
		PreparedStatement prepStmt = null;
		ResultSet keyResultSet = null;
		String SqlQUery = "INSERT INTO activity (description, duration, id_stage) VALUES (?, ?,?)";
		
		// Crear el PreparedStatement de la conexion
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQUery,
				PreparedStatement.RETURN_GENERATED_KEYS);
		prepStmt.setString(1, a.getDescription());
		prepStmt.setString(2, a.getDuration());
		prepStmt.setInt(3, a.getStage().getId());

		// Ejecutar Query
		prepStmt.executeUpdate();

		// Traer claves generadas
		keyResultSet = prepStmt.getGeneratedKeys();
		if (keyResultSet != null && keyResultSet.next()) {
			a.setId(keyResultSet.getInt(1));
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
	
	public void updateActivity(Activity a) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE activity SET description=?, duration=? , id_stage=? WHERE id_activity=?";
		
		// Armar el statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setString(1, a.getDescription());
		prepStmt.setString(2, a.getDuration());
		prepStmt.setInt(3, a.getStage().getId());
		prepStmt.setInt(4, a.getId());
		
		// Ejecutar query
		prepStmt.executeUpdate();
		
		// Cerrar conexion
		try {
			if(prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	// NO TERMINADO, DEBE ELIMINAR LA ACTIVIDAD TAMBIEN DE LA TABLA INTERMEDIA
	public void deleteActivity(int idAct) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "DELETE FROM activity WHERE id_activity = ?";

		// Crear el PreparedStatement de la conexion
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idAct);

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

}
