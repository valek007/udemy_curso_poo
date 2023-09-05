package principal.p1_clases_standar;

/*
Realizar un programa que muestre los resultados de la combinación
de la lotería primitiva, ordenados de menor a mayor.
*/

public final class Ejercicio1 {

	public static void main(String[] args) {

		int[] numeros = obtenerNumeros();
		mostrarNumeros(numeros);
		mostrarNumeros(ordenarNumeros(numeros));
	}

	//MOSTRAR EL ARRAY DE NÚMEROS POR CONSOLA
	private static void mostrarNumeros(int[] numeros) {
		System.out.println("Los números de la lotería són:");
		for (int i : numeros) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	//ORDENAR LOS NÚMEROS DEL ARRAY DE MENOR A MAYOR
	private static int[] ordenarNumeros(int[] numeros) {
		int temp;
		for (int i = 0; i < numeros.length - 1; i++) {
			for (int j = i+1; j < numeros.length; j++) {
				if (numeros[j] < numeros[i]) {
					temp = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = temp;
				}
			}
		}
		return numeros;
	}

	//GENERAR NÚMEROS ALEATORIOS Y PASARLOS POR UN ARRAY
	private static int[] obtenerNumeros() {
		int generado, generados = 0;
		int[] numeros = new int[6];
		for (int i = 0; i < numeros.length; i++) {
			generado = (int) (Math.random() * 49 + 1);
			if (!comprobarRepetidos(generado, numeros, generados)) {
				numeros[i] = generado;
				generados++;
			}
		}
		return numeros;
	}

	//COMPROBAR QUE HAY NÚMEROS REPETIDOS EN EL ARRAY
	private static boolean comprobarRepetidos(int generado, int[] numeros, int generados) {
		for (int i = 0; i < generados; i++) {
			if(generado == numeros[i]) {
				return true;
			}
		}
		return false;
	}
}
