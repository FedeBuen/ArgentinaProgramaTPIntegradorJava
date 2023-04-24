package modelo;

import menus.Menu;
import menus.MenuConfiguracion;

/************************************************************************
 * Clase Main del Trabajo Integrador Final 
 * del curso Argentina Programa 4.0 2023 - Java Inicial
 * ----------------------------------------------------------------------
 * Comision 150 - Grupo 5
 * 
 * Desarrollado en lenguaje Java utlizando el IDE Eclipse
 * Base de datos SQLite
 * 
 * 
 * **********************************************************************
 */

public class Main {

	public static void main(String[] args) {

		Menu menu = new MenuConfiguracion();
		menu.iniciar();

	}

}
