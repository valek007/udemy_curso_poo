package principal.p3_interfaces.ejercicio5;
/*
PARTE A: En la aplicación de la gestión de notas,
realizada en el ejercicio 13, incorporar una opción que
permita eliminar los suspensos.
*/

import java.util.ArrayList;
import java.util.Scanner;

import principal.utiles.Herramientas;

public class Test {
	
	private static ArrayList<Double> notas = new ArrayList<Double>();
	private final static int ALUMNOS = 3;

	public static void main(String[] args) {
		arrancarPrograma();
	}

	// ---CAPA PRESENTACIÓN DEL NEGOCIO----
	private static void mostrarMenu() {
		System.out.println("\n1.-Agregar nota");
		System.out.println("2.-Ver nota media");
		System.out.println("3.-Ver aprobados");
		System.out.println("4.-Mostrar notas ordenadas");
		System.out.println("5.-Eliminar suspensos");
		System.out.println("6.-Salir");
	}

	private static void arrancarPrograma() {

		try (Scanner sc = new Scanner(System.in)) {
			int opc = 0;
			do {
				mostrarMenu();
				opc = Integer.parseInt(sc.nextLine());

				switch (opc) {
				case 1 -> {
					System.out.println("Introduce la nota");
					Double nota = Double.parseDouble(sc.nextLine());
					if (introducirNota(nota, ALUMNOS)) {
						System.out.println("La nota se ha introducido correctamente.");
					} else {
						if (!(notas.size() >= ALUMNOS)) {
							System.out.println("Las notas pueden oscilar entre 0 y 10");
						} else {
							System.out.println("Se ha alcanzado el número máximo de alumnos.");
						}
					}
				}
				case 2 -> System.out.println("La nota media es: " + Herramientas.redondearNumero(obtenerNotaMedia()) );
				case 3 -> System.out.println("Total alumnos que han aprobado: " + obtenerAprobados());
				case 4 -> {
					System.out.println("Notas ordenadas de menor a mayor:");
					for (Double n : recuperarNotasOrdenadas()) {
						System.out.print(n + " ");
					}
				}
				case 5->{ eliminarSuspensos(); System.out.println("Suspensos eliminados"); }
				}
				;

			} while (opc != 6);
			System.out.println("Programa ha finalizado");
		} catch (Exception e) {
			System.out.println("Opción no valida");
			e.printStackTrace();
		}
	}

	// ---CAPA LÓGICA DEL NEGOCIO----
	private static boolean introducirNota(double nota, int alumnos) {
		if (notas.size() < alumnos) {
			if (nota >= 0 && nota <= 10) {
				notas.add(nota);
				return true;
			}
		}
		return false;
	}

	private static Double obtenerNotaMedia() {

		Double sumaNotas = 0.0;
		for (Double nota : notas) {
			sumaNotas += nota;
		}
		if (sumaNotas != 0.0)
			return (Double) sumaNotas / notas.size();
		else
			return sumaNotas;
	}

	private static int obtenerAprobados() {

		int aprobados = 0;
		for (Double nota : notas) {
			if (nota >= 5) {
				aprobados++;
			}
		}
		return aprobados;
	}

	public static Double[] recuperarNotasOrdenadas() {
		notas.sort((a, b) -> (int) (a - b));
		return notas.toArray(new Double[0]);
	}
	
	// Método nuevo
	public static void eliminarSuspensos() {
		notas.removeIf(n->n<5);
	}
}
