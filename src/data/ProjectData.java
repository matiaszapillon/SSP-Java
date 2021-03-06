package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.*;
import entities.Client;
import entities.Employee;
import entities.Project;
import entities.Stage;

public class ProjectData {

	public Project getProjectById(int idProject) throws SQLException {
		Project p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery = 
				"SELECT p.id_project, p.description, p.name as 'name_project' , c.*, s.description as 'description_stage', \r\n"
				+ "	s.id_stage, s.name as 'name_stage', ps.state\r\n" + "FROM project p \r\n"
				+ "LEFT JOIN client c ON p.id_client = c.id_client \r\n"
				+ "LEFT JOIN project_stage ps ON p.id_project = ps.id_project \r\n"
				+ "LEFT JOIN stage s ON ps.id_stage = s.id_stage  \r\n" + "WHERE p.id_project = ?";
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement(sqlQuery);
		stmt.setInt(1, idProject);
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			p = new Project();
			p.setDescription(rs.getString("description"));
			p.setId(rs.getInt("id_project"));
			p.setName(rs.getString("name_project"));
			if (rs.getInt("id_client") != 0) {
				Client c = new Client();
				c.setAddress(rs.getString("address"));
				c.setBusiness_name(rs.getString("business_name"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setEmail(rs.getString("email"));
				c.setId(rs.getInt("id_client"));
				p.setClient(c);
			}
			if (rs.getInt("s.id_stage") != 0) {
				do {
					Stage stage = new Stage();
					stage.setDescription(rs.getString("description_stage"));
					stage.setId(rs.getInt("id_stage"));
					stage.setName(rs.getString("name_stage"));
					stage.setState(rs.getInt("state"));
					p.getStages().add(stage);
				} while (rs.next());
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
			String msjError = "No se ha podido traer el proyecto solicitado";
			System.out.println(msjError);
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

	public void addSupplyToProject(int idProject, int idSupply, int idProvider, int quantity) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "INSERT INTO project_supply (id_project, id_supply, quantity, id_provider) values (?,?,?,?)";

		// Crear el PreparedStatement de la conexi�n
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		prepStmt.setInt(2, idSupply);
		prepStmt.setFloat(3, quantity);
		prepStmt.setInt(4, idProvider);

		// Ejecutar Query
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

	public void addStageToProject(int idProject, int idStage) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "INSERT INTO project_stage (id_project, id_stage, state) values (?,?,?)";

		// Crear el PreparedStatement de la conexi�n
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		prepStmt.setInt(2, idStage);
		prepStmt.setInt(3, 1);

		// Ejecutar Query
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

	public Project getProjectWithStages(int idProject) throws SQLException {
		Project proj = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT ps.id_project_stage ,p.id_project,p.start_date,p.end_date, s.id_stage, s.name as 'etapa', s.description,\n"
				+ "ps.state as 'estado', e.id_employee, e.name, e.surname, p.name as 'name_project',\n"
				+ "p.description as 'description_project'\n"
				+ "FROM stage s\n"
				+ "INNER JOIN project_stage ps ON ps.id_stage = s.id_stage\n"
				+ "INNER JOIN project p ON p.id_project = ps.id_project\n"
				+ "LEFT JOIN employee e ON ps.id_employee = e.id_employee\n"
				+ "WHERE p.id_project = ?";
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		// Ejecutar query
		rs = prepStmt.executeQuery();
		if (rs != null && rs.next()) {
			proj = new Project();
			proj.setId(rs.getInt("p.id_project"));
			proj.setName(rs.getString("p.name_project"));
			proj.setDescription(rs.getString("p.description_project"));
			proj.setEndDate(rs.getDate("end_date"));
			proj.setStartDate(rs.getDate("start_date"));
			do {
				Employee e = new Employee();
				e.setId(rs.getInt("e.id_employee"));
				e.setName(rs.getString("e.name"));
				e.setSurname(rs.getString("e.surname"));
				Stage s = new Stage();
				s.setId(rs.getInt("s.id_stage"));
				s.setName(rs.getString("etapa"));
				s.setDescription(rs.getString("s.description"));
				s.setState(rs.getInt("estado"));
				s.setEmployee(e);
				proj.getStages().add(s);
			} while (rs.next());

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
			String msjError = "No se ha podido realizar la consulta";
			System.out.println(msjError);
		}

		return proj;
	}
	
	public ArrayList<Stage> getProjecStages(int idProject) throws SQLException {
		ArrayList<Stage> projectStages = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT ps.id_project_stage, s.id_stage, s.name as 'etapa', s.description,\r\n"
				+ "ps.state as 'estado', e.id_employee, e.name, e.surname\r\n"
				+ "FROM project p \r\n"
				+ "LEFT JOIN project_stage ps ON p.id_project = ps.id_project \r\n"
				+ "LEFT JOIN stage s ON ps.id_stage = s.id_stage  \r\n"
				+ "LEFT JOIN employee e ON ps.id_employee = e.id_employee\r\n"
				+ "WHERE p.id_project = ?";
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		// Ejecutar query
		rs = prepStmt.executeQuery();
		if (rs != null && rs.next()) {
			projectStages = new ArrayList<Stage>();
			do {
				Employee e = new Employee();
				e.setId(rs.getInt("e.id_employee"));
				e.setName(rs.getString("e.name"));
				e.setSurname(rs.getString("e.surname"));
				Stage s = new Stage();
				s.setId(rs.getInt("s.id_stage"));
				s.setName(rs.getString("etapa"));
				s.setDescription(rs.getString("s.description"));
				s.setState(rs.getInt("estado"));
				s.setEmployee(e);
				projectStages.add(s);
			} while (rs.next());
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
			String msjError = "No se ha podido realizar la consulta";
			System.out.println(msjError);
		}
		return projectStages;
	}

	public Stage getProjectSage(int idProject, int idStage) throws SQLException {
		Stage s = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT s.name, s.description, ps.state, ps.id_employee, e.name, e.surname\r\n"
				+ "FROM project_stage ps\r\n" + "INNER JOIN stage s ON ps.id_stage = s.id_stage\r\n"
				+ "LEFT JOIN employee e ON ps.id_employee = e.id_employee\r\n"
				+ "WHERE ps.id_project = ? AND ps.id_stage = ?";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		prepStmt.setInt(2, idStage);

		// Ejecutar query
		rs = prepStmt.executeQuery();

		if (rs != null && rs.next()) {
			s = new Stage();
			s.setId(idStage);
			s.setName(rs.getString("s.name"));
			s.setDescription(rs.getString("s.description"));
			s.setState(rs.getInt("ps.state"));

			Employee e = new Employee();
			e.setId(rs.getInt("ps.id_employee"));
			e.setName(rs.getString("e.name"));
			e.setSurname(rs.getString("e.surname"));

			s.setEmployee(e);
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
			String msjError = "No se ha podido realizar la consulta";
			System.out.println(msjError);
		}

		return s;
	}

	// Consulta que devuelve las etapas que no se encuentran en un proyecto
	// especifico
	public ArrayList<Stage> getStagesOutOfProject(int idProject) throws SQLException {
		ArrayList<Stage> stagesOutOfProject = new ArrayList<Stage>();
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT id_stage, name, description \r\n" + "FROM stage\r\n" + "WHERE id_stage NOT IN (\r\n"
				+ "SELECT ps.id_stage \r\n" 
				+ "FROM project p \r\n"
				+ "INNER JOIN project_stage ps ON p.id_project = ps.id_project\r\n"
				+ "WHERE p.id_project = ?";
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);
		// Ejecutar query
		rs = prepStmt.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				Stage s = new Stage();

				s.setId(rs.getInt("id_stage"));
				s.setName(rs.getString("name"));
				s.setDescription(rs.getString("description"));

				stagesOutOfProject.add(s);
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
			String msjError = "No se ha podido realizar la consulta";
			System.out.println(msjError);
		}
		return stagesOutOfProject;
	}

	// Update que modifica la etapa de un proyecto
	public void updateProjectStage(int idProject, int idStage, int state, int idEmployee) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE project_stage SET state = ?, id_employee = ? \r\n"
				+ "WHERE id_project = ? AND id_stage = ?";
		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		switch (state) {
		case 1:
			prepStmt.setInt(1, Stage.NO_INICIADA);
			break;
		case 2:
			prepStmt.setInt(1, Stage.EN_CURSO);
			break;
		case 3:
			prepStmt.setInt(1, Stage.FINALIZADA);
			break;
		}
		prepStmt.setInt(2, idEmployee);
		prepStmt.setInt(3, idProject);
		prepStmt.setInt(4, idStage);
		// Ejecutar query
		prepStmt.executeUpdate();
		// Cerrar conexion
		try {
			if (prepStmt != null) {
				prepStmt.close();
			}
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
			String msjError = "No se ha podido modificar la etapa";
			System.out.println(msjError);
		}
	}

	public ArrayList<Project> getProjectsByClient(int id) throws SQLException {

		ArrayList<Project> projects = new ArrayList<Project>();
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SQLQuery = "SELECT * \n" + 
				"FROM project p \n" + 
				"INNER JOIN client c ON p.id_client = c.id_client \n" +
				"WHERE c.id_client = ?";

		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SQLQuery);
		prepStmt.setInt(1, id);

		// Ejecutar query
		rs = prepStmt.executeQuery();

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
			if (prepStmt != null)
				prepStmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;

	}

	public void createProject(Project p) throws SQLException {
		PreparedStatement prepStmt = null;
		ResultSet keyResultSet = null;
		String SqlQuery = "INSERT INTO project (name, description, id_client, start_date) VALUES (?,?,?,current_date())";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setString(1, p.getName());
		prepStmt.setString(2, p.getDescription());
		prepStmt.setInt(3, p.getClient().getId());

		// Ejecutar query
		prepStmt.executeUpdate();

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

	// Eliminar etapa de un proyecto especifico
	public void deleteStageFromProject(int idStage, int idProject) throws SQLException {
		PreparedStatement prepStmt = null;
		String SqlQuery = "DELETE FROM project_stage WHERE id_project = ? AND id_stage = ?";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);

		prepStmt.setInt(1, idProject);
		prepStmt.setInt(2, idStage);

		// Ejecutar query
		prepStmt.executeUpdate();

		// Cerrar conexion
		try {
			if (prepStmt != null) {
				prepStmt.close();
			}
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Eliminar proyecto con todas sus dependencias
	public void deleteProject(int idProject) throws SQLException {
		PreparedStatement deleteSupplies = null;
		PreparedStatement deleteStages = null;
		PreparedStatement deleteProject = null;
		String deleteSuppliesQuery = "DELETE FROM project_stage WHERE id_project = ?";
		String deleteStagesQuery = "DELETE FROM project_supply WHERE id_project = ?";
		String deleteProjectQuery = "DELETE FROM project WHERE id_project = ?";

		Connection conecction = FactoryConexion.getInstancia().getConn();
		try {
			conecction.setAutoCommit(false);
			// Eliminar insumos

			deleteSupplies = conecction.prepareStatement(deleteSuppliesQuery);
			deleteSupplies.setInt(1, idProject);
			deleteSupplies.executeUpdate();
			// Eliminar etapas

			deleteStages = conecction.prepareStatement(deleteStagesQuery);
			deleteStages.setInt(1, idProject);
			deleteStages.executeUpdate();

			// Eliminar Proyecto
			deleteProject = conecction.prepareStatement(deleteProjectQuery);
			deleteProject.setInt(1, idProject);
			deleteProject.executeUpdate();

			conecction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (conecction != null) {
				try {
					conecction.rollback();
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
		} finally {
			if (deleteSupplies != null) {
				deleteSupplies.close();
			}
			if (deleteStages != null) {
				deleteStages.close();
			}
			if (deleteProject != null) {
				deleteProject.close();
			}
			conecction.setAutoCommit(true);
		}

	}

	public Client getClientByIdProject(int id) throws SQLException {
		Client c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select c.*\n"
				+ "from project p inner join client c on c.id_client = p.id_client\n" + "where p.id_project = ?");
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs != null && rs.next()) {
			if (rs.getInt("id_client") != 0) {
				c = new Client();
				c.setAddress(rs.getString("address"));
				c.setBusiness_name(rs.getString("business_name"));
				c.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
				c.setEmail(rs.getString("email"));
				c.setId(rs.getInt("id_client"));
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

		return c;

	}

	public void updateEndDate(int idProject) throws SQLException {
		// TODO Auto-generated method
		PreparedStatement prepStmt = null;
		String SqlQuery = "UPDATE project SET end_date = current_date() \r\n" + "WHERE id_project = ? ";

		// Armar statement
		prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
		prepStmt.setInt(1, idProject);

		// Ejecutar query
		prepStmt.executeUpdate();

		// Cerrar conexion
		try {
			if (prepStmt != null) {
				prepStmt.close();
			}
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Project> getProjectsByFilter(Client c, int startDate, int endDate) throws SQLException {
		ArrayList<Project> projects = new ArrayList<Project>();
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String SqlQuery = "SELECT * FROM project p";
		if (c != null) {
			SqlQuery = SqlQuery + " INNER JOIN client c ON c.id_client = p.id_client WHERE c.id_client = ?";
			if (startDate != 0) {
				if (endDate != 0) {
					SqlQuery = SqlQuery + " AND year(p.start_date) = ? AND year(p.end_date) = ?";
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
					prepStmt.setInt(1, c.getId());
					prepStmt.setInt(2, startDate);
					prepStmt.setInt(3, endDate);
				} else {
					SqlQuery = SqlQuery + " AND year(p.start_date) = ? ";
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
					prepStmt.setInt(1, c.getId());
					prepStmt.setInt(2, startDate);
				}

			} else {
				if (endDate != 0) {
					SqlQuery = SqlQuery + " AND year(p.end_date) = ? ";
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
					prepStmt.setInt(1, c.getId());
					prepStmt.setInt(2, endDate);
				} else {
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
					prepStmt.setInt(1, c.getId());
				}

			}
		} else {
			if (startDate != 0) {
				if (endDate != 0) {
					SqlQuery = SqlQuery + " WHERE year(p.start_date) = ? AND year(p.end_date) = ?";
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
					prepStmt.setInt(1, startDate);
					prepStmt.setInt(2, endDate);
				}
				SqlQuery = SqlQuery + " WHERE year(p.start_date) = ? ";
				prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
				prepStmt.setInt(1, startDate);
			} else {
				if (endDate != 0) {
					SqlQuery = SqlQuery + " WHERE year(p.end_date) = ? ";
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);
					prepStmt.setInt(1, endDate);
				} else {
					prepStmt = FactoryConexion.getInstancia().getConn().prepareStatement(SqlQuery);

				}
			}
		}
		// Ejecutar query
		rs = prepStmt.executeQuery();

		if (rs != null) {
			while (rs.next()) {
				Project p = new Project();
				p.setId(rs.getInt("id_project"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setStartDate(rs.getDate("start_date"));
				p.setEndDate(rs.getDate("end_date"));
				if (c != null) {
					Client client = new Client();
					client.setId(rs.getInt("id_client"));
					client.setBusiness_name(rs.getString("business_name"));
					client.setCUIT_CUIL(rs.getString("CUIT_CUIL"));
					p.setClient(client);
				}
				projects.add(p);
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

		return projects;
	}
}
