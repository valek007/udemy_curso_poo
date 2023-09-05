package principal.p1_clases_standar;

import java.util.Scanner;

/*
 Realizar un programa que genere una cadena de texto y la muestre invertida.
 */

public final class Ejercicio2 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce una cadena:");
		StringBuilder cadena = new StringBuilder(sc.nextLine());
		//-----------------------------------------------------
		String aux = "";
		for (int i = cadena.length()-1; i >= 0 ; i--) {
			aux += cadena.charAt(i);
		}
		System.out.println(aux);
		//-----------------------------------------------------
		System.out.println(cadena.reverse());
	}
}
