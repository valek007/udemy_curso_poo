package principal.p6_acceso_a_ficheros.ejercicio2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

import principal.p1_clases_standar.modelo.Contacto;

/*
Realizar una nueva versión del ejercicio 6, en el que los datos
de la agenda se guardaban en un fichero, implementando las 
operaciones de busqueda y recuperacion de contactos a traves 
de java.nio.
 */
public class Test {

	private final static String RUTA_ARCHIVO = "src/principal/p6_acceso_a_ficheros/ejercicio2/recursos/agenda";

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
					Contacto contacto = buscarContacto(email, RUTA_ARCHIVO);
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

	private static Contacto buscarContacto(String email, String ruta) {
		Contacto c = null;
		String[] result = leerArchivo(ruta)
				.map(s->s.split("[|]"))
				.filter(d->d[2].equals(email))
				.findFirst()
				.orElseGet(null);
		if(result!=null) {
			c = new Contacto(result[0], result[2], Integer.parseInt(result[1]));
		}
		return c;
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
	private static Stream<String> leerArchivo(String ruta) {
		
		Path pt = Paths.get(ruta);
		Stream<String> cadenas = null;
		try {
			cadenas = Files.lines(pt);
			return cadenas;
		} catch (IOException e) {
			return cadenas;
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
		
		Contacto[] result = leerArchivo(ruta)//leemos el archivo
				.map(s->s.split("[|]"))//separamos los datos
				.map(d->new Contacto(d[0],d[2],Integer.parseInt(d[1])))//creamos el contacto
				.toArray(t->new Contacto[t]);//damos el tamaño al array
		for (Contacto c : result) {
			agenda.put(c.getEmail(), c);
		}
		return agenda;
	}
}
