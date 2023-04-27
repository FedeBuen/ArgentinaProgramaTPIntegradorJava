package gestores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexionDB.ConexionDB;
import modelo.Participantes;
import modelo.Persona;
import modelo.Puntos;

public class GestorParticipantesSQL {
	
	private Participantes participantes = new Participantes();
	private Connection conexion = null;
	private Statement consulta = null;
	
	public GestorParticipantesSQL(){}
	
	
	
	/**
	 * Metodo que lee el archivo de participantes y lo carta en participantes
	 * 
	 * @return participante
	 */
	
	public Participantes armarListaParticipantes(Puntos puntosGanados) {

		try {
			
			conexion = ConexionDB.getConexion();
			consulta = conexion.createStatement();
			
			String consultaSql = "SELECT * FROM personas ORDER BY nombre";
			ResultSet rsParticipantes = consulta.executeQuery(consultaSql);
			System.out.println("Actualizando datos de la tabla *personas*");
			
			
			while (rsParticipantes.next()) {
				String id = rsParticipantes.getString("id");
				String nombre = rsParticipantes.getString("nombre");
				String telefono = rsParticipantes.getString("telefono");
				String email = rsParticipantes.getString("email");
				
				if (this.validaid(id) && this.validaNombre(nombre) && this.validaTelefono(telefono) && 
					this.validaEmail(email)) {
					
					Persona p1 =  new Persona(id,nombre,telefono,email, puntosGanados);
					this.participantes.setParticipantes(id,p1);
				
				}else {
					System.out.println("El id y Nombre son datos requeridos");
				}					
				
			}
			conexion.close();
			consulta.close();
			rsParticipantes.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally{
			try {
				if (consulta != null)
					consulta.close();
			} catch (SQLException se2) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}			
		}
		return participantes;

	}
	
		
	public Boolean validaid (String datoValida) {
		//verifica que al menos figuren 2 datos id y nombre
		
		///valido id
		 if (!datoValida.isEmpty() && Character.isDigit(datoValida.charAt(0))) {
				return true;
			 }else {return false;}
			
		}
		
		///valido nombre
	public Boolean validaNombre (String datoValida) {
	 if (!datoValida.isEmpty() && Character.isAlphabetic(datoValida.charAt(0))) {
			return true;
		 }else {return false;}
		
	}	
		///valido telefono
	public Boolean validaTelefono (String datoValida) {
		 if (!datoValida.isEmpty() && Character.isDigit(datoValida.charAt(0))) {
				return true;
			 }else {return false;}
			
	}	
	public Boolean validaEmail (String datoValida) {
		 if (!datoValida.isEmpty() && Character.isAlphabetic(datoValida.charAt(0))) {
				return true;
			 }else {return false;}			
	}		
	


}
