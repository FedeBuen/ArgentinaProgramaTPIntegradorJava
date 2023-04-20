package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

	public static final String DB = "prode";
	public static final String DB_URL = "jdbc:sqlite:src/main/resources/" + DB;
	
	public static Connection getConexion() {
		Connection miConexion = null;
		try {
			// crear conexion
			miConexion = DriverManager.getConnection(DB_URL);
			System.out.println();
			System.out.println("Conexion con la base de datos *" + DB + "* realizada con exito");

		} catch (Exception e) {
			System.out.println();
			System.out.println("No se puede conectar a la base de datos: " + DB);
		}
		return miConexion;
	}
	
}
