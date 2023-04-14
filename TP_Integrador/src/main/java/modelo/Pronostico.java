package modelo;

import lombok.Data;

@Data
public class Pronostico {

	private int nroRonda;
	private int nroPartido;
	private String nombre;
	private Resultado resultado;

	public Pronostico(int nroRonda, int nroPartido, String nombre, Resultado resultado) {
		super();
		this.nroRonda = nroRonda;
		this.nroPartido = nroPartido;
		this.nombre = nombre;
		this.resultado = resultado;
	}


}
