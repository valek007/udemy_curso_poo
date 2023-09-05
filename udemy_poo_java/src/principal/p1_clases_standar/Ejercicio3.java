package principal.p1_clases_standar;

import java.util.ArrayList;
import java.util.Scanner;

/*
 Realizar un programa para la gestión de notas que al iniciarse muestre el siguiente menú:
 
 1.-Agregar nota
 2.-Ver nota media
 3.-Ver aprobados
 4.-Salir
 
 Al elegir la opción 1, se solicitara la introducción de una nota y se guardará, volviendo a mostrar el menú.
 Al elegir la opción 2 y 3 se mostrará, respectivamente, la nota media registrada hasta el momento y el número de aprobados.
 al elegir la opción 4, se saldrá del programa. 
 */

public final class Ejercicio3 {

	private static ArrayList<Double> notas = new ArrayList<Double>();
	private final static int ALUMNOS = 3;

	public static void main(String[] args) {
		arrancarPrograma();
	}

	// ---CAPA PRESENTACIÓN DEL NEGOCIO----
	private static void mostrarMenu() {
		System.out.println("1.-Agregar nota");
		System.out.println("2.-Ver nota media");
		System.out.println("3.-Ver aprobados");
		System.out.println("4.-Salir");
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
						System.out.println("La nota se ha introducido correctamente.\n");
					} else {
						if (!(notas.size() >= ALUMNOS)) {
							System.out.println("Las notas pueden oscilar entre 0 y 10");
						} else {
							System.out.println("Se ha alcanzado el número máximo de alumnos.\n");
						}
					}
				}
				case 2 -> System.out.println("La nota media es: " + obtenerNotaMedia() + "\n");
				case 3 -> System.out.println("Total alumnos que han aprobado: " + obtenerAprobados() + "\n");
				}

			} while (opc != 4);
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
}
