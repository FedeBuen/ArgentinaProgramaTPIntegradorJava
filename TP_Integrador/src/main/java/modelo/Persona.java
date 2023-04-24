package modelo;

import java.util.HashMap;

import gestores.GestorConfiguracionPuntos;
import lombok.Data;

@Data
public class Persona {

	private String Id;
	private String Nombre;
	private String Telefono;
	private String eMail;
	private Puntos puntosGanados = new Puntos();
	private HashMap<Integer,Integer> puntajes = new HashMap<Integer,Integer>();
	private Integer puntosTotales = 0;
	private Integer totalRondasPerfectas = 0 ;

	public Persona(String id, String nombre, String telefono, String eMail) {
		this.Id = id;
		this.Nombre = nombre;
		this.Telefono = telefono;
		this.eMail = eMail;
		
		String pathConfiguracionPuntos = "src/main/resources/archivos/configuracion.csv";
		GestorConfiguracionPuntos gsPuntos = new GestorConfiguracionPuntos();
		puntosGanados = gsPuntos.cargarPuntosDesdeArchivo(pathConfiguracionPuntos);

	}
	
	public void setPuntajes(Integer ronda) {
		if (!this.puntajes.containsKey(ronda)) {
			this.puntajes.get(ronda);
			this.puntajes.put(ronda, 0);
		}
				
		this.puntajes.put(ronda, this.puntajes.get(ronda) + puntosGanados.getPuntosGana());

	}
	
	public void listPuntos(Ronda torneo) {
		for (Integer clave: puntajes.keySet()){
			int cantidadAciertos = (this.puntajes.get(clave)/puntosGanados.getPuntosGana());
			System.out.println("Ronda: " + clave + " Aciertos :" + cantidadAciertos + "/" + torneo.getRonda().get(clave).size() + " Puntos: " + puntajes.get(clave));
			puntosTotales = puntosTotales + puntajes.get(clave);
		}
	}	
	public void calcularRondasPerfectas(Ronda torneo) {
		for (int m : this.puntajes.keySet()) {
			//verifico si la cantidad de puntos es igual a los partidos 
			//almacenados en la ronda
			//en el array tengo el todal de partidos comparados con los puntos 
			//me determina si hubo aciertos en todos se divide por si el valor por acierto es distinto de 1
			//de ser asi no va a coincidir con los partidos de la ronda
					
			if((this.puntajes.get(m)/puntosGanados.getPuntosGana())==torneo.getRonda().get(m).size()) {
				System.out.println("Puntos extras por ronda" + "(" + m + ") " + "perfecta ---> " + puntosGanados.getPuntosRondaPerfecta()  );
				puntosTotales = puntosTotales + puntosGanados.getPuntosRondaPerfecta();
				totalRondasPerfectas = totalRondasPerfectas + 1;
			}
		}		
	}
	public void calcularFasesPerfectas(Ronda torneo) {
		if (torneo.getRonda().size() == totalRondasPerfectas) {
			System.out.println("Puntos extras por Fase perfecta ---> " + puntosGanados.getPuntosFasePerfecta());
			puntosTotales = puntosTotales + puntosGanados.getPuntosFasePerfecta();			
		}
	}
	public void listPuntosTotales() {
		System.out.println(" **** Puntos Totales Acumulados : " + this.puntosTotales + "  ****");
	}
	

}
