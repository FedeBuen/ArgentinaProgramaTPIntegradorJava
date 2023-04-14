package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Ronda {

	private HashMap<Integer, ArrayList<Partido>> ronda = new HashMap<Integer, ArrayList<Partido>>();

	public ArrayList<Partido> getListaPartidosDeRonda(Integer nroRonda) {

		return this.ronda.get(nroRonda);
	}

	public HashMap<Integer, ArrayList<Partido>> getRonda() {
		return ronda;
	}

	public void agregarPartidosEnRonda(Integer nroRonda, Partido partido) {

		ArrayList<Partido> partidos = new ArrayList<Partido>();
		if (this.ronda.get(nroRonda) == null) {
			partidos.add(partido);
		} else {
			partidos = this.ronda.get(nroRonda);
			partidos.add(partido);
		}
		this.ronda.put(nroRonda, partidos);
	}

}
