package modelo;

import lombok.Data;

@Data
public class Partido {

	private int nroPartido;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private int golesLocal;
	private int golesVisitante;

	public Partido() {}
	
	public Partido(int nroPartido, Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante)  {
		super();
		this.nroPartido = nroPartido;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	public Resultado getResultado() {
		if (this.golesLocal == this.golesVisitante) {
			return Resultado.EMPATE;
		} else if (this.golesLocal > this.golesVisitante) {
			return Resultado.LOCAL;
		}
		return Resultado.VISITANTE;
	}

}
