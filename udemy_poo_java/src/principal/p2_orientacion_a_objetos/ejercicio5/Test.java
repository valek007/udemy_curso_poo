package principal.p2_orientacion_a_objetos.ejercicio5;

import principal.p2_orientacion_a_objetos.ejercicio5.modelo.Circulo;
import principal.p2_orientacion_a_objetos.ejercicio5.modelo.Figura;
import principal.p2_orientacion_a_objetos.ejercicio5.modelo.Triangulo;

/*
Aplicar el polimorfismo para hacer un programa que muestre
los datos de dos figuras diferentes(Triangulo y Circulo).
*/



public class Test {
	
	public static void main(String[] args) {
		
		mostrarDatosFiguras(new Triangulo("Amarillo", 4, 8));
		mostrarDatosFiguras(new Circulo("Rojo", 3));
	}
	
	private static void mostrarDatosFiguras(Figura f) {
		System.out.println("Color "+f.getColor());
		System.out.println("Superficie "+f.superficie());
	}
}
