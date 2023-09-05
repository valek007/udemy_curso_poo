package principal.p4_streams.ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import principal.p1_clases_standar.modelo.Contacto;

/*
 Incluir varios metodos en la logica de aplicacion del ejercicio de la agenda,
 que realicen calculos sobre el conjunto de Contactos, como edad media de los
 contactos, buscar contacto por nombre, devolucion de lista de nombres, etc.
 */
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
		
		System.out.println("5.- Mostrar Por Edad Maxima");
		System.out.println("6.- Comprobar la existencia");
		System.out.println("7.- Mostrar Contacto Mayor");
		System.out.println("8.- Buscar Por Nombre");
		System.out.println("9.- Mostrar Nombres");
		System.out.println("10.-Mostrar Edad Media");
		System.out.println("11.- Salir");
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
				case 5 -> {
					System.out.println("Introduce la edad");
					int edad = Integer.parseInt(sc.nextLine());
					for (Contacto c : recuperarPorEdadMaxima(edad)) {
						System.out.println(c.toString());
					}
				}
				case 6 -> {
					System.out.println("Introduce el nombre del contacto");
					String nombre = sc.nextLine();
					if(existeContacto(nombre))
						System.out.println("Contacto existe");
					else 
						System.out.println("Contacto no existe");
				}
				case 7 -> {
					System.out.println(contactoMayor().toString());
				}
				case 8 -> {
					System.out.println("Introduce el nombre del contacto");
					String nombre = sc.nextLine();
					System.out.println(buscarXNombre(nombre).toString());
				}
				case 9 -> {
					for (String contacto : recuperarNombres()) {
						System.out.println(contacto);
					}
				}
				case 10 -> {
					System.out.println("La edad media es "+edadMedia());
				}
				}

			} while (opc != 11);
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
	// -------NUEVOS METODOS IMPLEMENTANDO STREAMS--------

	// recuperar contactos por edad maxima
	private static Contacto[] recuperarPorEdadMaxima(int edad) {

		Stream<Contacto> st = contactos.values().stream();
		return st.filter(c -> c.getEdad() <= edad).toArray(t -> new Contacto[t]);
	}

	// indica si existe un contacto con este nombre
	private static boolean existeContacto(String nombre) {

		Stream<Contacto> st = contactos.values().stream();
		return st.anyMatch(c -> c.getNombre().equals(nombre));
	}

	// devuelve el contacto con mas edad
	private static Contacto contactoMayor() {

		Stream<Contacto> st = contactos.values().stream();
		return st.max((c1, c2) -> c1.getEdad() - c2.getEdad()).get();
	}

	// devuelve el primer contacto que coincida con el nombre
	private static Contacto buscarXNombre(String nombre) {

		Stream<Contacto> st = contactos.values().stream();
		return st.filter(c -> c.getNombre().equals(nombre)).findFirst().get();
	}

	// devuelve las lista de nombres
	private static List<String> recuperarNombres() {

		Stream<Contacto> st = contactos.values().stream();
		return st.map(c -> c.getNombre()).collect(Collectors.toList());
	}

	// devuelve la edad media de todos los contactos
	private static double edadMedia() {

		Stream<Contacto> st = contactos.values().stream();
		return st.mapToInt(c -> c.getEdad()).average().getAsDouble();
	}
}
