package gestores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexionDB.ConexionDB;
import exceptions.GolesNegativosException;
import modelo.Equipo;
import modelo.Partido;
import modelo.Ronda;

/**
 * Esta clase se va a encargar de leer desde la base de datos prode la tabla
 * partidos y almacenaralos en una Ronda
 * 
 */
public class GestorPartidosSQL {

	private Ronda ronda = new Ronda();
	private Partido partido;

	/**
	 * Metodo que lee la tabla de partidos y los carga en una Ronda
	 * 
	 * @return ronda
	 */
	public Ronda cargarPartidosDesdeTable() {
		Connection conexion = null;
		Statement consulta = null;

		try {
			// Creando una conexion a la DB
			conexion = ConexionDB.getConexion();
			consulta = conexion.createStatement();
			String consultaSql = "SELECT * FROM partidos ORDER BY nroRonda, nroPartido";
			ResultSet rsPartidos = consulta.executeQuery(consultaSql);
			System.out.println("Actualizando datos de la tabla *partidos*");
			int nroRonda;
			while (rsPartidos.next()) {
				nroRonda = rsPartidos.getInt("nroRonda");

				partido = getDatosPatido(rsPartidos);

				if (partido != null && nroRonda != 0) {

					this.ronda.agregarPartidosEnRonda(nroRonda, partido);

				}
			}
			conexion.close();
			consulta.close();
			rsPartidos.close();
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

		return this.ronda;
	}

	
	/**
	 * Metodo privado de la clase Se pasa por parametro un resulset representa
	 * todos los datos de un partido, lo convierte y devuelve un Partido En caso de
	 * inconsistencia con los datos devuelve un null
	 * 
	 * @param rsPartidos
	 * @return Partido
	 * 
	 */
	
	private Partido getDatosPatido(ResultSet rsPartidos) {
		Partido partido;
		Equipo equipoLocal;
		Equipo equipoVisitante;
		try {
			int nroPartido = rsPartidos.getInt("nroPartido");
			String eqLocal = rsPartidos.getString("equipoLocal");
			String eqVisitante = rsPartidos.getString("equipoVisitante");
			int golesLocal = rsPartidos.getInt("golesLocal");
			int golesVisitante = rsPartidos.getInt("golesVisitante");
			equipoLocal = new Equipo(eqLocal);
			equipoVisitante = new Equipo(eqVisitante);

			if (golesLocal < 0 || golesVisitante < 0) {
				throw new GolesNegativosException("El valor de los goles debe ser mayor o igual a 0. ");
			}

			partido = new Partido(nroPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
		} catch (GolesNegativosException e) {
			System.out.println(e.getMessage());
			partido = null;
		} catch (NumberFormatException e) {
			System.out.println("Formato de datos incorrecto");
			partido = null;
		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea");
			partido = null;
		}

		return partido;
	}

}
