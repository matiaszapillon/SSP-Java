package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Activity;
import entities.Client;
import entities.Project;
import entities.Stage;


public class StageData {

	public ArrayList<Stage> getAll()  throws SQLException {
		ArrayList<Stage> stages = new ArrayList<Stage>();
		Statement stmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT * FROM stage";
		// Armar statement
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		// Ejecutar query
		rs = stmt.executeQuery(SqlQuery);
		
		if(rs != null) {
			while(rs.next()) {
				Stage s = new Stage();
				
				s.setId(rs.getInt("id_stage"));
				s.setDescription(rs.getString("description"));
				s.setName(rs.getString("name"));
				
				stages.add(s);
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
				
		return stages;
	}

	public Stage getStageByName(String nameStage) throws SQLException {
		Stage s = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM stage WHERE name = ?");
		stmt.setString(1, nameStage);
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			s = new Stage();
			s.setDescription(rs.getString("description"));
			s.setId(rs.getInt("id_stage"));
			s.setName(rs.getString("name"));

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

		return s;
		
	}

}
