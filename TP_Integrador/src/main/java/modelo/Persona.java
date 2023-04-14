package modelo;

import lombok.Data;

@Data
public class Persona {

	private String Id;
	private String Nombre;
	private String Telefono;
	private String eMail;

	public Persona(String id, String nombre, String telefono, String eMail) {
		this.Id = id;
		this.Nombre = nombre;
		this.Telefono = telefono;
		this.eMail = eMail;

	}

}
