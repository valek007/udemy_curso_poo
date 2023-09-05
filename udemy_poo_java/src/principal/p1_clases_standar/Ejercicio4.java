package principal.p1_clases_standar;

import java.util.HashMap;
import java.util.Scanner;

import principal.p1_clases_standar.modelo.Contacto;


/*
Realizar un programa para la gestión de una agenda de contactos que al iniciarse muestre el siguiente menú:

1.- Añadir Contacto
2.- Buscar Contacto
3.- Eliminar Contacto
4.- Mostrar Contactos
5.- Salir

Al elegir la opción 1, se solicitara la introducción de un nombre, email, edad y se guardará,
(el email será el campo clave del contacto, no pueden haber dos iguales) volviendo a mostrar el menú.
Al elegir la opción 2, se solicitara el email del contacto para proceder con la busqueda.
Si se encuentra mostrara todos los datos.
Al elegir la opción 3, se solicitara el email y se eliminara el contacto de la agenda.
Al elegir la opción 4, se mostrarán todos los contactos y sus datos.
Al opción 5 se eligira para finalizar el programa.
*/

public final class Ejercicio4 {

	private static HashMap<String, Contacto> contactos = new HashMap<>();

	public static void main(String[] args) {
		arrancarPrograma();
	}

	// ---CAPA PRESENTACIÓN---
	private static void mostrarMenu() {
		System.out.println("1.- Añadir Contacto");
		System.out.println("2.- Buscar Contacto");
		System.out.println("3.- Eliminar Contacto");
		System.out.println("4.- Mostrar Contactos");
		System.out.println("5.- Salir");
	}

	private static void arrancarPrograma() {

		try (Scanner sc = new Scanner(System.in)) {
			int opc = 0;
			do {
				mostrarMenu();
				opc = Integer.parseInt(sc.nextLine());

				switch (opc) {
				case 1 -> {
					System.out.println("Introduce el nombre del contacto");
					String nombre = sc.nextLine();
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					System.out.println("Introduce la edad del contacto");
					int edad = Integer.parseInt(sc.nextLine());
					if (agregarContacto(nombre, email, edad)) {
						System.out.println("El contacto se ha agregado correctamente.\n");
					} else {
						System.out.println("El contacto no se ha podido agregar.\n");
					}
				}
				case 2 -> {
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					Contacto contacto = buscarContacto(email);
					if (!(contacto==null)) {
						System.out.println("Datos del contacto:.\n"+contacto+"\n");
						
					} else {
						System.out.println("El contacto no se encuentra en la agenda.\n");
					}
				}
				case 3 -> {
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					if (eliminarContacto(email))
						System.out.println("El contacto se ha eliminado correctamente");
					else
						System.out.println("El contacto no se ha podido eliminar");
				}
				case 4 -> {
					System.out.println("Todos los contactos:");
					mostrarContactos();
					System.out.println();
				}
				}

			} while (opc != 5);
			System.out.println("Programa ha finalizado");
		} catch (Exception e) {
			System.out.println("Opción no valida");
			e.printStackTrace();
		}
	}

	// ---CAPA LÓGICA---
	private static boolean agregarContacto(String nombre, String email, int edad) {

		if (!contactos.containsKey(email)) {
			contactos.put(email, new Contacto(nombre, email, edad));
			return true;
		}
		return false;
	}

	private static Contacto buscarContacto(String email) {
		if(contactos.containsKey(email))
		return contactos.get(email);
		else
			return null;
	}

	private static boolean eliminarContacto(String email) {
		if (contactos.containsKey(email)) {
			contactos.remove(email);
			return true;
		}
		return false;
	}

	private static void mostrarContactos() {
		for (Contacto x : contactos.values()) {
			System.out.println(x.toString());
		}
	}
}
