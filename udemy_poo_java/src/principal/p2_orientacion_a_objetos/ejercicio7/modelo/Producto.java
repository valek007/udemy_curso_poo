package principal.p2_orientacion_a_objetos.ejercicio7.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Producto {
	private String nombre;
	private double precio;
	
	public abstract double obtenerPrecioPedido(int unidades);
	
	public double aplicarDescuento(double precio, double porcentaje) {
	  double descuento = precio * porcentaje / 100;
	  return precio - descuento;
	}
}
