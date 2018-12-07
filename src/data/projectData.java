package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Client;
import entities.Employee;
import entities.Project;
import entities.Stage;
import entities.User;

public class projectData {

	public Project getProjectById(int idProject) throws SQLException {
		// TODO Auto-generated method stub
		Project p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT p.id_project, p.description, p.name as 'name_project' , c.*, s.description as 'description_stage', s.id_stage, s.name as 'name_stage', ps.state\n" + 
				"FROM project p INNER JOIN client c\n" + 
				"ON p.id_client = c.id_client INNER JOIN project_stage ps \n" + 
				"ON p.id_project = ps.id_project INNER JOIN stage s \n" + 
				"ON ps.id_stage = s.id_stage\n" + 
				"WHERE p.id_project = ? ");
		stmt.setInt(1, idProject);
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			p = new Project();
			p.setDescription(rs.getString("description"));
			p.setId(rs.getInt("id_project"));	
			p.setName(rs.getString("name_project"));
			Client c = new Client();
			c.setAddress(rs.getString("address"));
			c.setBusiness_name(rs.getString("business_name"));
			c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
			c.setEmail(rs.getString("email"));
			c.setId(rs.getInt("id_client"));
			p.setClient(c);
			do {
				Stage stage = new Stage();
				stage.setDescription(rs.getString("description_stage"));
				stage.setId(rs.getInt("id_stage"));
				stage.setName(rs.getString("name_stage"));
				stage.setState(rs.getInt("state"));
				p.getStages().add(stage);
			} while (rs.next());
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

		return p;
	}

	public ArrayList<Project> getAll() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Project> projects = new ArrayList<Project>();
		Statement stmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * FROM project";
		stmt = FactoryConexion.getInstancia().getConn().createStatement();
		rs = stmt.executeQuery(SQLQuery);
		if (rs != null) {
			while (rs.next()) {
				Project p = new Project();
				p.setDescription(rs.getString("description"));
				p.setName(rs.getString("name"));
				p.setId(rs.getInt("id_project"));				
				projects.add(p);
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
		return projects;
	}

}
