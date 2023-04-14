package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

	public static final String DB_URL = "jdbc:sqlite:src/main/resources/prode";
	
	public static Connection getConexion() {
		Connection miConexion = null;
		try {
			// crear conexion
			miConexion = DriverManager.getConnection(DB_URL);
			System.out.println("conexion ok");

		} catch (Exception e) {
			System.out.println("No se puede conectar a la base de datos: " + DB_URL);
		}
		return miConexion;
		
	}
}
