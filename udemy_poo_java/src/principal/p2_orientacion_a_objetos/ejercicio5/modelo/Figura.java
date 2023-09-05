package principal.p2_orientacion_a_objetos.ejercicio5.modelo;

public abstract class Figura {

	private String color;
	
	public Figura(String color) {
		this.color = color;
	}
	
	public abstract double superficie();

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
