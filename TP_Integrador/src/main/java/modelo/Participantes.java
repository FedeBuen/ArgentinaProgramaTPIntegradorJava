package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Participantes {

		//genera un hashmap con la lista de todos los participantes que van a
		//intervenir en el pronostico, la lectura valida si los datos requeridos
		//son validos de lo contrario los ignora, requeido id y nombre
	
		private HashMap<String, Persona> Particiantes = new HashMap<String, Persona>();
		
		public Participantes() {}
		
		public void armarListaParticipantes(String path) {
			
			FileReader NombreArchivo;
			try {
				NombreArchivo = new FileReader(path);
				
				BufferedReader brParticipante = new BufferedReader(NombreArchivo);
				String participante = brParticipante.readLine();
			
				while (participante != null) {
	
					String[] unParticipante = validaFila(participante);
					
					if (!unParticipante[0].isEmpty() && !unParticipante[1].isEmpty()) {
						
						Persona p1 =  new Persona(unParticipante[0],unParticipante[1],unParticipante[2],unParticipante[3]);
						this.Particiantes.put(unParticipante[0], p1);
					
					}else {
						System.out.println("El id y Nombre son datos requeridos");
					}					
					
					participante = brParticipante.readLine();
				}
				brParticipante.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}					

		}
		
			
		public String[] validaFila (String datoValida) {
			//verifica que al menos figuren 2 datos id y nombre
			
			String[] personaValidada = new String[4];
			String[] FilaValidar = datoValida.split(",");
			
			///valido id
			if (FilaValidar.length>0) {
				 if (!FilaValidar[0].isEmpty() && Character.isDigit(FilaValidar[0].charAt(0))) {
					 personaValidada[0]=FilaValidar[0];
				 }else {personaValidada[0] = "";}
				
			}
			
			///valido nombre
			if (FilaValidar.length>1) {
				 if (!FilaValidar[1].isEmpty() && Character.isAlphabetic(FilaValidar[1].charAt(0))) {
					 personaValidada[1]=FilaValidar[1];
				 }else {personaValidada[1] = "";}
				
			}
			
			///valido telefono
			if (FilaValidar.length>2) {
				 if (!FilaValidar[2].isEmpty() && Character.isDigit(FilaValidar[2].charAt(0))) {
					 personaValidada[2]=FilaValidar[2];				 
				 }else {personaValidada[2] = "No Declarado";}
				
			}else {personaValidada[2] = "No Declarado";}
			
			///valido e-mail
			if (FilaValidar.length>3) {
				 if (!FilaValidar[3].isEmpty() && Character.isAlphabetic(FilaValidar[3].charAt(0)) 
						 && FilaValidar[3].contains("@")) {
					 personaValidada[3]=FilaValidar[3];
				 }else {personaValidada[3] = "No Declarado";}
				
			}else {personaValidada[3] = "No Declarado";}			
						
			return personaValidada;
			
		}
		
		public void ListarParticipantes() {
			for (String clave: Particiantes.keySet()) {
				System.out.println("Clave --> " + clave + " " + Particiantes.get(clave).getNombre());
				System.out.println(Particiantes.get(clave).getTelefono() + " " + Particiantes.get(clave).getEMail());
				}
		}
}