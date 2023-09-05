package principal.p2_orientacion_a_objetos.ejercicio7.modelo;

import lombok.Getter;
import lombok.Setter;

/*
 Incluye un atributo llamado dias, que es el número de días que faltan 
para que la oferta finalice.

Si es ofertado, independientemente del número de unidades: 
o Si le queda 1 día a la oferta aplicará un 20% de descuento.
o Si le quedan 2 o 3 a la oferta, se aplicará un 15% de descuento.
o Si le quedan más de 3 días se aplicará un 10% de descuento.
 */

@Getter
@Setter
public class ProductoOfertado extends Producto {

	private int dias;

	public ProductoOfertado(String nombre, double precio, int dias) {
		super(nombre, precio);
		this.dias = dias;
	}

	@Override
	public double obtenerPrecioPedido(int unidades) {

		double precio = unidades * this.getPrecio();

		if (dias == 1)
			return this.aplicarDescuento(precio, 20);
		else if (dias == 2 || dias == 3)
			return this.aplicarDescuento(precio, 15);
		else
			return this.aplicarDescuento(precio, 10);
	}

}
