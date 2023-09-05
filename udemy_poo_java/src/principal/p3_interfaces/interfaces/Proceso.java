package principal.p3_interfaces.interfaces;
/*
 Crear una interfaz llamadas Proceso que incluya dos métodos:

-imprimir().Mostrará los valores de los atributos del objeto que la implemente.

-aumentar(int c). Aumentará los valores de los atributos del objeto que la
implemente en la cantidad recibida.
 */
public interface Proceso {

	public void imprimir();
	
	public void aumentar(int c);
	
	default void firmar() {
		System.out.println(this.toString());
	}
}
