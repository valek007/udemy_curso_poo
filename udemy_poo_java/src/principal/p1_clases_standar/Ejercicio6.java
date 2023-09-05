package principal.p1_clases_standar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import principal.p1_clases_standar.modelo.Contacto;

/*
 Realizar una nueva versión del programa para la gestión de una agenda de contactos.
 La funcionalidad será la misma que la del anterior, pero en este caso, 
 los contactos se almacenarán en un fichero de texto, cada contacto en una
 línea siguiendo el formato: nombre,edad,email.
 */
public final class Ejercicio6 {

	private final static String RUTA_ARCHIVO = "src/principal/parte1/recursos/agenda";

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

		HashMap<String, Contacto> agenda = recuperarAgenda(RUTA_ARCHIVO);
		
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
					if (agregarContacto(nombre, email, edad, agenda)) {
						System.out.println("El contacto se ha agregado correctamente.\n");
					} else {
						System.out.println("El contacto no se ha podido agregar.\n");
					}
				}
				case 2 -> {
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					Contacto contacto = buscarContacto(email, agenda);
					if (!(contacto == null)) {
						System.out.println("Datos del contacto:.\n" + contacto + "\n");

					} else {
						System.out.println("El contacto no se encuentra en la agenda.\n");
					}
				}
				case 3 -> {
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					if (eliminarContacto(email, RUTA_ARCHIVO, agenda))
						System.out.println("El contacto se ha eliminado correctamente\n");
					else
						System.out.println("El contacto no se ha podido eliminar\n");
				}
				case 4 -> {
					System.out.println("Todos los contactos:");
					mostrarContactos(agenda);
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
	private static boolean agregarContacto(String nombre, String email, int edad, HashMap<String, Contacto> agenda) {

		if (!agenda.containsKey(email)) {
			agenda.put(email, new Contacto(nombre, email, edad));
			escribirArchivo(RUTA_ARCHIVO, nombre+"|"+edad+"|"+email);
			return true;
		}
		return false;
	}

	private static Contacto buscarContacto(String email, HashMap<String, Contacto> agenda) {
		if (agenda.containsKey(email))
			return agenda.get(email);
		else
			return null;
	}
	
	private static boolean eliminarContacto(String email, String ruta, HashMap<String, Contacto> agenda) {
		try (FileOutputStream fos = new FileOutputStream(ruta)){
			if (agenda.containsKey(email)) {
				agenda.remove(email);
				fos.write(new byte[0]);
				for (Contacto c : agenda.values()) {
					escribirArchivo(ruta, c.getNombre()+"|"+c.getEdad()+"|"+c.getEmail());
				}
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private static void mostrarContactos(HashMap<String, Contacto> agenda) {
		for (Contacto x : agenda.values()) {
			System.out.println(x.toString());
		}
	}

	// ---MANEJO ARCHIVOS---
	private static List<String> leerArchivo(String ruta) {
		String cadena = null;
		List<String> campos = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)))){
			while ((cadena = br.readLine()) != null) {
			   campos.add(cadena);
			}
			return campos;
		} catch (Exception e) {
			return campos;
		}

	}

	private static boolean escribirArchivo(String ruta, String texto) {

		try (PrintWriter archivo = new PrintWriter(new FileOutputStream(ruta, true))) {
			archivo.println(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static HashMap<String, Contacto> recuperarAgenda(String ruta) {

		HashMap<String, Contacto> agenda = new HashMap<>();

		for (String cadena : leerArchivo(ruta)) {
			String[] contacto = cadena.split("[|]");
			if (!agenda.containsKey(contacto[2])) {
				agenda.put(contacto[2], new Contacto(contacto[0], contacto[2], Integer.parseInt(contacto[1])));
			}

		}
		return agenda;
	}
}
