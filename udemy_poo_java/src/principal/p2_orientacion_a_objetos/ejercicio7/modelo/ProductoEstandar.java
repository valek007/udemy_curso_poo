package principal.p2_orientacion_a_objetos.ejercicio7.modelo;

import lombok.Getter;
import lombok.Setter;

/*
 Incluye un atributo adicional llamado sección
 
 obtenerPrecioPedido() devolverá el número de unidades por el precio, aplicando un 10%
de descuento en caso de que el número de unidades sea igual o superior a 5.
 */

@Getter
@Setter
public class ProductoEstandar extends Producto {

	private String seccion;

	public ProductoEstandar(String nombre, double precio, String seccion) {
		super(nombre, precio);
		this.seccion = seccion;
	}

	@Override
	public double obtenerPrecioPedido(int unidades) {

		double precio = unidades * this.getPrecio();
		if (unidades >= 5)
			return this.aplicarDescuento(precio, 10);
		else
			return precio;
	}

}
