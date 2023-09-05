package principal.p2_orientacion_a_objetos.ejercicio2;

import java.io.IOException;

import principal.p2_orientacion_a_objetos.ejercicio2.modelo.Lector;


/*
Se pretende crear una subclase de BufferedReader, a la que llamaremos Lector,
que incluya dos nuevos métodos readInt() y readDouble(),que permitirán leer 
como un int y double, respectivamente, el dato de la fuente de entrada.

Dispondrá de dos constructores:

-Lector(Reader in). Permitirá leer desde cualquier objeto Reader
que se le porporcione.

-Lector(). Quedará configurado para leer del teclado.
*/

public class Test {
	
	public static void main(String[] args) {
		
		try {
			arrancar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	private static void arrancar() throws IOException {
		Lector lector = new Lector();
		System.out.println("Nombre: ");
		String nombre = lector.readLine();
		System.out.println("Edad: ");
		String edad = lector.readLine();
		System.out.println("Saldo: ");
		String saldo = lector.readLine();
		
		System.out.println("Te llamas "+nombre+" tienes "+edad+" años y tu saldo es "+saldo+"€");
	}
}
