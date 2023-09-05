package principal.p3_interfaces.ejercicio1.modelo;

import principal.p2_orientacion_a_objetos.ejercicio5.modelo.Figura;
import principal.p3_interfaces.interfaces.Proceso;

public class Circulo extends Figura implements Proceso {

	private int radio;
	
	public Circulo(String color, int radio) {
		super(color);
		this.radio = radio;
	}

	@Override
	public void imprimir() {
		System.out.println("El radio es " + radio);
		System.out.println("Y la superficie es "+superficie());
	}

	@Override
	public void aumentar(int c) {
		radio+=c;
	}

	@Override
	public double superficie() {
		return Math.PI*radio*radio;
	}

}
