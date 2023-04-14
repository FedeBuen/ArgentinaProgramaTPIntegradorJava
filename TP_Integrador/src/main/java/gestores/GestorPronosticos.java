package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.CantidadDatosException;
import modelo.Pronostico;
import modelo.Resultado;
/**
 * Esta clase se va a encargar de leer desde un arhivo csv los pronosticos 
 * y almacenaralos en un ArrayList de Pronostico
 * 
 */
public class GestorPronosticos {

	/**
	 * Metodo que lee el archivo de pronosticos y los carga en un ArrayList de
	 * Pronosticos
	 * 
	 * @return pronosticos
	 */
	public ArrayList<Pronostico> cargarPronosticosDesdeArchivo(String path) {
		ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
		Pronostico pronostico;
		try {
			System.out.println("-------------------------------------");
			System.out.println("Cargando datos del archivo: " + path);
			System.out.println("-------------------------------------");
			FileReader filePronosticos = new FileReader(path);
			BufferedReader brPronosticos = new BufferedReader(filePronosticos);
			String unPronostico = brPronosticos.readLine();
			while (unPronostico != null) {

				pronostico = getDatosPronostico(unPronostico);
				if (pronostico != null) {
					pronosticos.add(pronostico);
				}
				unPronostico = brPronosticos.readLine();
			}
			brPronosticos.close();
		} catch (IOException e) {
			System.out.println("Error al leer un archivo: " + path);
		} 
		return pronosticos;

	}

	/**
	 * Metodo privado de la clase Se pasa por parametro un String que representa
	 * todos los datos de un pronostico, lo convierte y devuelve un Pronostico En
	 * caso de inconsistencia con los datos devuelve un null
	 * 
	 * @param unPronostico
	 * @return Pronostico
	 * 
	 */
	private Pronostico getDatosPronostico(String unPronostico) {
		String[] datosUnPronostico = unPronostico.split(",");
		Pronostico pronostico;
		try {
			
			if (datosUnPronostico.length != 4) {
				throw new CantidadDatosException("Cantidad de datos incorrecto en linea: ");
			}
			int nroRonda = Integer.parseInt(datosUnPronostico[0]);
			int nroPartido = Integer.parseInt(datosUnPronostico[1]);
			String nombre = datosUnPronostico[2];
			Resultado resultado = Resultado.valueOf(datosUnPronostico[3].toUpperCase());

			
			pronostico = new Pronostico(nroRonda, nroPartido, nombre, resultado);

		} catch (CantidadDatosException e) {
			System.out.println(e.getMessage() + unPronostico);
			pronostico = null;
		} catch (NumberFormatException e) {
			System.out.println("Numero de partido incorrecto en la linea: " + unPronostico);
			pronostico = null;
		} catch (IllegalArgumentException e) {
			System.out.println("se esperaba un pronostico valido (LOCAL, EMPATE o VISITANTE) en la linea: " + unPronostico);
			pronostico = null;
		} catch (Exception e) {
			System.out.println("Entrada de datos no valida, error en la linea: " + unPronostico);
			pronostico = null;
		}
		return pronostico;
	}
}
