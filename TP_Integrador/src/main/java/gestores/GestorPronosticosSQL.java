package gestores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionDB.ConexionDB;
import modelo.Pronostico;
import modelo.Resultado;

/**
 * Esta clase se va a encargar de leer desde la base de datos prode la tabla
 * pronosticos y almacenaralos en un ArrayList de Pronostico
 * 
 */
public class GestorPronosticosSQL {

	/**
	 * Metodo que lee el la tabla pronosticos y los carga en un ArrayList de
	 * Pronosticos
	 * 
	 * @return pronosticos
	 */
	public ArrayList<Pronostico> cargarPronosticosDesdeTable() {
		ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
		Pronostico pronostico;
		Connection conexion = null;
		Statement consulta = null;

		try {
			// Creando una conexion a la DB
			conexion = ConexionDB.getConexion();
			consulta = conexion.createStatement();
			String consultaSql = "SELECT * FROM pronosticos ORDER BY nombre, nroRonda, nroPartido";
			ResultSet rsPronosticos = consulta.executeQuery(consultaSql);
			System.out.println("Actualizando datos de la tabla *pronosticos*");
			while (rsPronosticos.next()) {

				pronostico = getDatosPronostico(rsPronosticos);
				if (pronostico != null) {
					pronosticos.add(pronostico);
				}

			}
			conexion.close();
			consulta.close();
			rsPronosticos.close();
		} catch (Exception e) {
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		} finally {
			// Esta sentencia es para que ante un problema con la base igual se cierren las
			// conexiones
			try {
				if (consulta != null)
					consulta.close();
			} catch (SQLException se2) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return pronosticos;
	}

	/**
	 * Metodo privado de la clase Se pasa por parametro un resulset que representa
	 * todos los datos de un pronostico, lo convierte y devuelve un Pronostico En
	 * caso de inconsistencia con los datos devuelve un null
	 * 
	 * @param rsPronosticos
	 * @return Pronostico
	 * 
	 */
	private Pronostico getDatosPronostico(ResultSet rsPronosticos) {
		Pronostico pronostico;
		try {

			int nroRonda = rsPronosticos.getInt("nroRonda");
			int nroPartido = rsPronosticos.getInt("nroPartido");
			String nombre = rsPronosticos.getString("nombre");
			Resultado resultado = Resultado.valueOf(rsPronosticos.getString("resultado"));

			pronostico = new Pronostico(nroRonda, nroPartido, nombre, resultado);

		} catch (IllegalArgumentException e) {
			System.out.println("se esperaba un pronostico valido (LOCAL, EMPATE o VISITANTE)");
			pronostico = null;
		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea: ");
			pronostico = null;
		}
		return pronostico;
	}

}
