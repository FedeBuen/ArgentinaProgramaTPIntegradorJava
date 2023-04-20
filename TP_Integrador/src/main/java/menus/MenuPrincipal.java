package menus;

import modelo.Juego;

public class MenuPrincipal extends Menu {

	/**
	 * instancia del juego 
	 */
	Juego juego = new Juego();

	public MenuPrincipal(Juego juego) {
		this.juego = juego;
	}

	@Override
	public void mostrarPantalla() {
		System.out.println("====================================");
		System.out.println("|          MENU PRINCIPAL          |");
		System.out.println("|----------------------------------|");
		System.out.println("| 1 - Calcular Puntajes            |");
		System.out.println("| 2 - Imprimir Ganadores           |");
		System.out.println("| 3 - Imprimir Rondas              |");
		System.out.println("| 4 - Imprimir Participantes  	   |");
		System.out.println("| 0 - Salir de la aplicacion  	   |");
		System.out.println("====================================");
		System.out.println("Presione una de las opciones: ");
	}


	@Override
	public void opcion1() {
		juego.resolverJuego();
		mostrarPantalla();
	}

	@Override
	public void opcion2() {
		juego.ImprimirResultados();
		mostrarPantalla();
	}

	@Override
	public void opcion3() {
		juego.listarRondas();
		mostrarPantalla();
	}

	@Override
	public void opcion4() {
		juego.mostrarParticipantes();
		mostrarPantalla();
	}
	
	@Override
	public void opcion5() {
		mensajeErrorOpcionIngresada();
	}

	@Override
	public void opcion0() {
		System.out.println("--------------------------------------------------");
		System.out.println("ha salido con exito del sistema");
		System.out.println("--------------------------------------------------");
		setSalir();
	}
	@Override
	public void mensajeErrorOpcionIngresada() {
		System.out.println("Ingreso incorrecto, debe ser un numero del 1 al 5");
		
	}

}
