package principal.p2_orientacion_a_objetos.ejercicio4;

import principal.p2_orientacion_a_objetos.ejercicio4.modelo.Circulo;
import principal.p2_orientacion_a_objetos.ejercicio4.modelo.Figura;
import principal.p2_orientacion_a_objetos.ejercicio4.modelo.Triangulo;

/*
Crear la jerarquía completa de figuras geométricas, con Figura
como clase abstracta y Triangulo y Circulo como subclases.

En el caso de Triangulo, contará con dos atributos: base y altura.
*/

public class Test {
	
	public static void main(String[] args) {
		
		Object objeto = new String("Hola Mundo");
		System.out.println(objeto.toString());
		
		Figura figura1 = new Triangulo("azul", 10, 20);
		Figura figura2 = new Circulo("rojo", 15);
		System.out.println(figura1.superficie());
		System.out.println(figura2.superficie());
		
	}
}
