package principal.p3_interfaces.ejercicio6;
/*
PARTE B: En la aplicación de la agenda de contactos
realizada en el ejercicio 4, incorporar una opción que nos permita
recuperar los contactos cuya edad máxima sea un valor solicitado.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import principal.p1_clases_standar.modelo.Contacto;

public class Test {

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
		System.out.println("5.- Filtrar por edad");
		System.out.println("6.- Salir");
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
					if (!(contacto == null)) {
						System.out.println("Datos del contacto:.\n" + contacto + "\n");

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
				case 5 ->{
					System.out.println("Introduce la edad máxima");
					int edad=Integer.parseInt(sc.nextLine());
					Contacto[] filtro = recuperarXEdadMax(edad);
					for (Contacto c : filtro) {
						System.out.println(c.toString());
					}
				}
				}

			} while (opc != 6);
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
		if (contactos.containsKey(email))
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

	// Nuevo método
	public static Contacto[] recuperarXEdadMax(int edad) {
		ArrayList<Contacto> aux = new ArrayList<Contacto>();
		contactos.forEach((k, v) -> {
			if (v.getEdad() <= edad)
				aux.add(v);
		});
		return aux.toArray(new Contacto[0]);
	}
}
