package menus;

import java.util.Scanner;

public abstract class Menu {

	private String opcion;
	private boolean salir;
	
	Scanner scn = new Scanner(System.in);
	public void iniciar() {

		mostrarPantalla();

		salir = false;
		while (!salir) {
			opcion = scn.nextLine();
			switch (opcion) {
			case "1":
				opcion1();
				break;
			case "2":
				opcion2();
				break;
			case "3":
				opcion3();
				break;
			case "4":
				opcion4();
				break;
			case "5":
				opcion5();
                break;
			case "0":
				opcion0();
                break;
             default:
                mensajeErrorOpcionIngresada();
			}
		}
	}
	
	public void setSalir() {
		this.salir = true;
	}
	
	public abstract void mostrarPantalla();
	public abstract void mensajeErrorOpcionIngresada();
	public abstract void opcion1();
	public abstract void opcion2();
	public abstract void opcion3();
	public abstract void opcion4();
	public abstract void opcion5();
	public abstract void opcion0();
	
}
