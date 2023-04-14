package modelo;

import lombok.Data;

@Data
public class Equipo {
	
	private String nombre;
	private String descripcion;
	
	public Equipo(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
		this.descripcion = "";
	}
	

}
