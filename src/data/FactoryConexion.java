package data;

import java.sql.*;
import util.customizedEceptions.*;

public class FactoryConexion {
	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="root";
	private String db="ssp_db";
	
	private static FactoryConexion instancia;
		
	private FactoryConexion(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			AppDataException customizedException = new AppDataException(e,"No se ha encontrado el driver correspondiente");
			customizedException.getInnerException();
			customizedException.getMessage();
		}
		
	}
	
	public static FactoryConexion getInstancia(){
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	
	private Connection conn;
	private int cantConn=0;
	
	public Connection getConn() throws SQLException {
		try{
			if(conn==null || conn.isClosed()){	
				conn = DriverManager.getConnection(
			        "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				     //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
			}
		} catch(SQLException e) {			
			AppDataException customizedException = new AppDataException(e, "Error al conectarse a la DB");
			System.out.println("Customized: " + customizedException.getMessage());
		}
		cantConn++;
		return conn;
	}
	
	
	public void releaseConn() throws SQLException{
		try { 
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			// AppDataException customizedException = new AppDataException(e, "Error al cerrar la conexion a DB. \nSe lanzara un error");
			// System.out.println("Customized: " + customizedException.getMessage());
			throw e;
		}
	}

	
}
