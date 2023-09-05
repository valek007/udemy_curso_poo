package principal.p3_interfaces.ejercicio1;

import principal.p3_interfaces.ejercicio1.modelo.Circulo;
import principal.p3_interfaces.ejercicio1.modelo.Mesa;
import principal.p3_interfaces.interfaces.Proceso;

/*
Crear una interfaz llamadas Proceso que incluya dos métodos:

-imprimir().Mostrará los valores de los atributos del objeto que la implemente.

-aumentar(int c). Aumentará los valores de los atributos del objeto que la
implemente en la cantidad recibida.

Posteriormente, está interfaz será implementada por Circulo y por una nueva
clase Mesa que crearemos.
*/



public class Test {
	
	public static void main(String[] args) {
		operarObj((Proceso) new Mesa(10,20));
		operarObj((Proceso) new Circulo("Rojo", 5));
		new Mesa(10,20).firmar();
	}
	
	static void operarObj(Proceso p) {
		p.aumentar(10);
		p.imprimir();
	}
}
