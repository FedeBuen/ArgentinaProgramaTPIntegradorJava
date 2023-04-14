package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import comparators.PuntajeComparator;
//import gestores.GestorPartidos;
import gestores.GestorPartidosSQL;
import gestores.GestorPronosticos;

public class Juego {

	private ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
	private HashMap<String, Integer> puntuacion = new HashMap<String, Integer>();
	private Participantes participantes = new Participantes();
	private Ronda rondas = new Ronda();

	/**
	 * Lee los archivos csv y guarda los datos parseados en las colecciones
	 * 
	 */
	public void leerDatosDesdeArchivos() {
		//GestorPartidos gsPartidos = new GestorPartidos();
		GestorPronosticos gsPronosticos = new GestorPronosticos();
		GestorPartidosSQL gsPartidosSQL = new GestorPartidosSQL();
		
		String pathParticipantes = "src/main/resources/archivos/participantes.csv";
		//String pathPartidos = "src/main/resources/archivos/partidos.csv";
		String pathPronosticos = "src/main/resources/archivos/pronosticos.csv";

		participantes.armarListaParticipantes(pathParticipantes);
		rondas = gsPartidosSQL.cargarPartidosDesdeTable();
		//rondas = gsPartidos.cargarPartidosDesdeArchivo(pathPartidos);
		pronosticos = gsPronosticos.cargarPronosticosDesdeArchivo(pathPronosticos);

	}

	/**
	 * Metodos que recorre las colecciones, realiza las comparaciones y guarda los
	 * puntos de los participantes en el HashMap de puntuacion
	 */
	public void resolverJuego() {
		ArrayList<Partido> partidosPorRonda = new ArrayList<Partido>();
		HashMap<Integer, Partido> partidos = new HashMap<Integer, Partido>();
		Partido partido = new Partido();
		int rondaActual = 0;
		int puntos = 0;
		try {

			// Recorre el arraylist de pronostico comparando
			for (Pronostico pronostico : pronosticos) {

				// Si en la ArrayList de pronostico cambia el numero de ronda
				// actualiza la lista de partidosPorRonda
				// y el hashmap de partidos para optimizar la busqueda
				// de esta forma solo se recorre estas coleciones cuando
				// cambia la ronda
				if (rondaActual != pronostico.getNroRonda()) {
					partidosPorRonda = rondas.getListaPartidosDeRonda(pronostico.getNroRonda());
					partidos = getMapPartidos(partidosPorRonda);
				}

				rondaActual = pronostico.getNroRonda();

				partido = partidos.get(pronostico.getNroPartido());;
				
				if (partido != null && partido.getResultado().equals(pronostico.getResultado())) {

					// Si ha coincidencia entre el pronostico y el resultado del partido
					// Si el nombre ya existe como key en el HashMap lee value (puntos)
					// le suma uno y actualiza el HashMap

					if (puntuacion.containsKey(pronostico.getNombre())) {

						puntos = puntuacion.get(pronostico.getNombre());
						puntos++;
						puntuacion.put(pronostico.getNombre(), puntos);

						// Si es la primera vez que aparece el nombre, lo agrega como
						// key y al value (puntos) lo inicializa con 1 punto
					} else {
						puntuacion.put(pronostico.getNombre(), 1);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------------------------");
		System.out.println(" Se realizo el calculo de los puntajes exitosamente");
		System.out.println("-------------------------------------------------------");
	}

	/**
	 * Metodo privado que convierte un ArrayList de Partido a un HashMap key : nro
	 * de partido value : partido
	 * 
	 * @param partidosPorRonda
	 * @return
	 */
	private HashMap<Integer, Partido> getMapPartidos(ArrayList<Partido> partidosPorRonda) {
		HashMap<Integer, Partido> partidos = new HashMap<Integer, Partido>();
		for (Partido partido : partidosPorRonda) {
			partidos.put(partido.getNroPartido(), partido);
		}
		return partidos;
	}

	/**
	 * Metodo que ordena el Hashmap puntuacion por la cantidad de puntos de los
	 * participantes y los muestra por pantalla
	 */
	public void ImprimirResultados() {

		PuntajeComparator comparator = new PuntajeComparator(puntuacion);
		Map<String, Integer> result = new TreeMap<String, Integer>(comparator);
		result.putAll(puntuacion);

		// recorre y muestra por pantalla los participantes y sus puntajes
		System.out.println("-------------------------------------------------------");
		System.out.println("         Puntajes");
		System.out.println("-------------------------------------------------------");
		result.forEach((k, v) -> System.out.println("El participante " + k + " obtubo un puntaje de: " + v));
		System.out.println("-------------------------------------------------------");

	}

	/**
	 * Metodo que lista por pantalla los resultados de los partidos por cada ronda
	 */
	public void listarRondas() {
		ArrayList<Partido> listaPartidosPorRonda = new ArrayList<Partido>();
		Iterator<Entry<Integer, ArrayList<Partido>>> itRonda = rondas.getRonda().entrySet().iterator();
		System.out.println("-------------------------------------------------------");
		System.out.println("Listado de partidos por rondas ");
		while (itRonda.hasNext()) {
			Map.Entry<Integer, ArrayList<Partido>> e = (Map.Entry<Integer, ArrayList<Partido>>) itRonda.next();

			listaPartidosPorRonda = e.getValue();
			System.out.println("-------------------------------------------------------");
			System.out.println(" Ronda : " + e.getKey());
			for (Partido p : listaPartidosPorRonda) {
				System.out.println(p.getNroPartido() + "- " 
						+ p.getEquipoLocal().getNombre() 
						+ "(" + p.getGolesLocal()
						+ ")" + " VS " + p.getEquipoVisitante().getNombre() 
						+ "(" + p.getGolesVisitante() + ")"
						+ " Resultado : " + p.getResultado());
			}
		}
	}

	public void mostrarParticipantes() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Listado de participantes ");
		System.out.println("-------------------------------------------------------");
		participantes.ListarParticipantes();
	}
}
