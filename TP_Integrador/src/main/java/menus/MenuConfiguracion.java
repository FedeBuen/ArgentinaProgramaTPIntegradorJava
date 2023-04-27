package menus;

import modelo.Juego;

public class MenuConfiguracion extends Menu{

	/**
	 * instancia del juego 
	 */
	Juego juego = new Juego();
		
	@Override
	public void mostrarPantalla() {
		System.out.println("========================================");
		System.out.println("|        MENU DE CONFIGURACION         |");
		System.out.println("|--------------------------------------|");
		System.out.println("| 1 - Cargar datos desde archivos CSV  |");
		System.out.println("| 2 - Cargar datos desde BASE DE DATOS |");
		System.out.println("| 0 - Salir de la aplicacion           |");
		System.out.println("========================================");
		System.out.println("Presione una de las opciones: ");
	}

	@Override
	public void opcion1() {
		juego.leerDatosDesdeArchivos();
		Menu menuPrincipal = new MenuPrincipal(juego);
		menuPrincipal.iniciar();
		setSalir();
		
	}

	@Override
	public void opcion2() {
		juego.leerDatosDesdeBD();
		Menu menuPrincipal = new MenuPrincipal(juego);
		menuPrincipal.iniciar();
		setSalir();
	}

	@Override
	public void opcion3() {
		mensajeErrorOpcionIngresada();
	}

	@Override
	public void opcion4() {
		mensajeErrorOpcionIngresada();
	}

	@Override
	public void opcion5() {
		mensajeErrorOpcionIngresada();
	}

	@Override
	public void opcion0() {
		System.out.println("ha salido con exito del sistema");
		setSalir();
	}
	@Override
	public void mensajeErrorOpcionIngresada() {
		System.out.println("Ingreso incorrecto, debe elegir una opcion valida");
		
	}
}
