package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exceptions.CantidadDatosException;
import exceptions.GolesNegativosException;
import exceptions.RondaNegativaException;
import modelo.Equipo;
import modelo.Partido;
import modelo.Ronda;

/**
 * Esta clase se va a encargar de leer desde un arhivo csv los partidos y
 * almacenaralos en una Ronda
 * 
 */
public class GestorPartidos {

	private Ronda ronda = new Ronda();
	private Partido partido;

	/**
	 * Metodo que lee el archivo de partidos y los carga en una Ronda
	 * 
	 * @return ronda
	 */
	public Ronda cargarPartidosDesdeArchivo(String path) {

		try {
			System.out.println("-------------------------------------");
			System.out.println("Cargando datos del archivo: " + path);
			System.out.println("-------------------------------------");
			FileReader filePartidos = new FileReader(path);
			BufferedReader brPartidos = new BufferedReader(filePartidos);
			String unPartido = brPartidos.readLine();

			Integer nroRonda;

			while (unPartido != null) {

				nroRonda = getNroRonda(unPartido);

				partido = getDatosPartido(unPartido);

				if (partido != null && nroRonda != 0) {

					this.ronda.agregarPartidosEnRonda(nroRonda, partido);

				}
				unPartido = brPartidos.readLine();
			}
			brPartidos.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + path);
		}
		return this.ronda;
	}

	/**
	 * Metodo privado de la clase Se pasa por parametro un String que representa
	 * todos los datos de un partido, lo convierte y devuelve un Partido En caso de
	 * inconsistencia con los datos devuelve un null
	 * 
	 * @param unPartido
	 * @return Partido
	 * 
	 */
	private Partido getDatosPartido(String unPartido) {
		String[] datosUnPartido = unPartido.split(",");
		Partido partido;
		Equipo equipoLocal;
		Equipo equipoVisitante;
		try {

			if (datosUnPartido.length != 6) {
				throw new CantidadDatosException("Cantidad de datos incorrecto en linea: ");
			}

			int nroPartido = Integer.parseInt(datosUnPartido[1]);
			String eqLocal = datosUnPartido[2];
			String eqVisitante = datosUnPartido[3];
			int golesLocal = Integer.parseInt(datosUnPartido[4]);
			int golesVisitante = Integer.parseInt(datosUnPartido[5]);
			equipoLocal = new Equipo(eqLocal);
			equipoVisitante = new Equipo(eqVisitante);

			if (golesLocal < 0 || golesVisitante < 0) {
				throw new GolesNegativosException("El valor de los goles debe ser mayor o igual a 0. ");
			}

			partido = new Partido(nroPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);

		} catch (CantidadDatosException e) {
			System.out.println(e.getMessage() + unPartido);
			partido = null;
		} catch (GolesNegativosException e) {
			System.out.println(e.getMessage() + "Error en la linea: " + unPartido);
			partido = null;
		} catch (NumberFormatException e) {
			System.out.println("Formato de datos incorrecto en la linea: " + unPartido);
			partido = null;
		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea: " + unPartido);
			partido = null;
		}

		return partido;
	}

	private Integer getNroRonda(String unPartido) {
		String[] datosUnPartido = unPartido.split(",");
		Integer nroRonda;
		try {
			nroRonda = Integer.parseInt(datosUnPartido[0]);
			if (nroRonda <= 0) {
				throw new RondaNegativaException("El valor de la ronda debe ser mayor a 0. ");
			}
		} catch (RondaNegativaException e) {
			System.out.println(e.getMessage() + "Error en la linea: " + unPartido);
			nroRonda = 0;
		} catch (NumberFormatException e) {
			System.out.println("Formato de datos incorrecto en la linea: " + unPartido);
			nroRonda = 0;
		}
		return nroRonda;
	}

}
