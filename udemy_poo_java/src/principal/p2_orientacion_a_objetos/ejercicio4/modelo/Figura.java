package principal.p2_orientacion_a_objetos.ejercicio4.modelo;

public abstract class Figura {

	@SuppressWarnings("unused")
	private String color;
	
	public Figura(String color) {
		this.color = color;
	}
	
	public abstract double superficie();
}
