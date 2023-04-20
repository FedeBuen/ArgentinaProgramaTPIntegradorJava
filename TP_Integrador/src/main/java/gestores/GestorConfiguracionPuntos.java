package gestores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import modelo.Puntos;

/**
 * Esta clase se va a encargar de leer desde un arhivo csv la configuracion de puntajes
 * y almacenaralos en un objeto de la clase Puntos
 * 
 */
public class GestorConfiguracionPuntos {

	private Puntos puntos = new Puntos();

	public Puntos cargarPuntosDesdeArchivo(String path) {
		try {
			System.out.println("-------------------------------------");
			System.out.println("Cargando datos del archivo: " + path);
			System.out.println("-------------------------------------");
			FileReader filePuntos = new FileReader(path);
			BufferedReader brPuntos = new BufferedReader(filePuntos);
			/*
			 * Formato de archivo de configuracion de puntos: 
			 * PUNTOS_GANA,(int) = puntos acierto normal 
			 * PUNTOS_EXTRA,(int) = puntos extra 
			 * PUNTOS_RONDA_PERFECTA,(int) = puntos extra por ronda perfecta 
			 * PUNTOS_FASE_PERFECTA,(int) = puntos extra por fase perfecta
			 * 
			 */

			// lee PUNTOS_GANA
			String linea = brPuntos.readLine();
			String[] lineaPuntosGana = linea.split(",");
			puntos.setPuntosGana(Integer.parseInt(lineaPuntosGana[1]));

			// lee PUNTOS_EXTRA
			linea = brPuntos.readLine();
			String[] lineaPuntosExtra = linea.split(",");
			puntos.setPuntosExtra(Integer.parseInt(lineaPuntosExtra[1]));

			// lee PUNTOS_RONDA_PERFECTA
			linea = brPuntos.readLine();
			String[] lineaPuntosRonda = linea.split(",");
			puntos.setPuntosRondaPerfecta(Integer.parseInt(lineaPuntosRonda[1]));

			// lee PUNTOS_FASE_PERFECTA
			linea = brPuntos.readLine();
			String[] lineaPuntosFase = linea.split(",");
			puntos.setPuntosFasePerfecta(Integer.parseInt(lineaPuntosFase[1]));

			brPuntos.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivode configuracion: " + path);
		}
		return this.puntos;
	}

}
