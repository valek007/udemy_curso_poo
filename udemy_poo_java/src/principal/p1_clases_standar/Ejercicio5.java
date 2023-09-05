package principal.p1_clases_standar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import principal.p1_clases_standar.modelo.Pieza;

/*
 Realizar un programa para gestión de piezas que al iniciarse muestre el siguiente menú:
 
 1.- Añadir pieza
 2.- Piezas antiguas
 3.- Salir
 
 Cada pieza definida por su nombre y una fecha de fabricación.
 
 Al elegir la opción 1, se le solicitara el nombre de la pieza y los datos de la fecha.
 después la pieza será almacenada.
 
 Al elegir la opción 2, se solicitara la fecha y se mostrara las piezas más antiguas 
 a esta fecha.
 */

public final class Ejercicio5 {

	private static HashSet<Pieza> piezas = new HashSet<Pieza>();

	public static void main(String[] args) {
		arrancarPrograma();
	}

	// ---CAPA PRESENTACIÓN---
	private static void mostrarMenu() {
		System.out.println("1.- Añadir pieza");
		System.out.println("2.- Piezas antiguas");
		System.out.println("3.- Salir");
	}

	private static void arrancarPrograma() {

		try (Scanner sc = new Scanner(System.in)) {
			int opc = 0;
			do {
				mostrarMenu();
				opc = Integer.parseInt(sc.nextLine());

				switch (opc) {
				case 1 -> {
					System.out.println("Introduce la pieza");
					String pieza = sc.nextLine();
					System.out.println("Introduce la fecha con el formato 'dd/MM/yyyy'");
					String cadena = sc.nextLine();

					//USO DE DATE FORMAT DÍA/MES/AÑO
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date fecha = sdf.parse(cadena);
					if(agregarPieza(pieza, fecha))
						System.out.println("La pieza se ha registrado correctamente");
					else
						System.out.println("No se ha podido registrar la pieza");
				}
				case 2 -> {
					System.out.println("Introduce la fecha con el formato 'dd/mm/yyyy'");
					String cadena = sc.nextLine();
					
					//USO DE CALENDAR AÑO/MES/DÍA
					Calendar calendar = Calendar.getInstance();
					String[] data = cadena.split("/");
					int[] data2 = {Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])};
					calendar.set(data2[2], data2[1]-1, data2[0]);

					DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("ES","es"));
					for (Pieza pieza : obtenerPiezasAntiguas(calendar.getTime())) {
						System.out.println(pieza.getNombre()+": "+df.format(pieza.getFecha()));
					}
					System.out.println();
				}
				}

			} while (opc != 3);
			System.out.println("Programa ha finalizado");
		} catch (Exception e) {
			System.out.println("Opción no valida");
			e.printStackTrace();
		}
	}

	// ---CAPA LÓGICA---
	private static boolean agregarPieza(String nombre, Date fecha) {
		if (!piezas.contains(new Pieza(nombre, fecha))) {
			piezas.add(new Pieza(nombre, fecha));
			return true;
		}
		return false;
	}

	private static List<Pieza> obtenerPiezasAntiguas(Date fecha) {
		ArrayList<Pieza> aux = new ArrayList<>();
		for (Pieza pieza : piezas) {
			if (pieza.getFecha().before(fecha)) {
				aux.add(pieza);
			}
		}
		return aux;
	}
}
