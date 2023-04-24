package modelo;

import java.util.HashMap;


public class Participantes {

		//genera un hashmap con la lista de todos los participantes que van a
		//intervenir en el pronostico, la lectura valida si los datos requeridos
		//son validos de lo contrario los ignora, requeido id y nombre
	
		private HashMap<String, Persona> Participantes = new HashMap<String, Persona>();
		
		public Participantes() {}
		
			
		public HashMap<String, Persona> getParticipantes() {
			return Participantes;
		}

		public void setParticipantes(String id, Persona persona) {
			this.Participantes.put(id, persona);
		}
		
		public void ListarParticipantes() {
			for (String clave: Participantes.keySet()) {
				System.out.println("Clave --> " + clave + " Nombre: " + Participantes.get(clave).getNombre());
				System.out.println(" TE: " + Participantes.get(clave).getTelefono() + " E-mail: " + Participantes.get(clave).getEMail());
				}
		}
		
		public String getNombre(String Id) {	
			//agregado Ariel
			//return Particiantes.get(Id).getId() +"--> " +Particiantes.get(Id).getNombre();
			return Participantes.get(Id).getNombre();
			
		}		
		
		public void setPuntosPersonas(String keyParticipante,Integer ronda) {
			if (this.Participantes.containsKey(keyParticipante)) {
				this.Participantes.get(keyParticipante).setPuntajes(ronda);
			}
		}
		public void listarPuntosPorParticipantes(Ronda torneo) {
			
			for (String clave: Participantes.keySet()){
				System.out.println("#--->  " + Participantes.get(clave).getNombre());
				Participantes.get(clave).listPuntos(torneo);
				Participantes.get(clave).calcularRondasPerfectas(torneo);
				Participantes.get(clave).calcularFasesPerfectas(torneo);
				Participantes.get(clave).listPuntosTotales();
			}
		
		}
}