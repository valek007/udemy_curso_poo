package principal.p2_orientacion_a_objetos.ejercicio7;

import principal.p2_orientacion_a_objetos.ejercicio7.modelo.Producto;
import principal.p2_orientacion_a_objetos.ejercicio7.modelo.ProductoEstandar;
import principal.p2_orientacion_a_objetos.ejercicio7.modelo.ProductoOfertado;

/*
     Ejercicio Jerarquía de productos
     
Tenemos que realizar un programa para la gestión de productos.
Los productos tienen los siguientes atributos:
 Nombre
 Precio

Se distinguen dos tipos de productos:
 estándar. Incluye un atributo adicional llamado sección
 ofertado. Incluye un atributo llamado dias, que es el número de días que faltan 
para que la oferta finalice.

Además de constructores, getters y setters, tendrán un método obtenerPrecioPedido() 
que recibe como parámetro las unidades pedidas del producto y devuelve el precio final.
Esté método funcionará de forma diferente si es un producto estándar u ofertado

 Si es estándar, devolverá el número de unidades por el precio, aplicando un 10%
de descuento en caso de que el número de unidades sea igual o superior a 5.
 Si es ofertado, independientemente del número de unidades: 
o Si le queda 1 día a la oferta aplicará un 20% de descuento.
o Si le quedan 2 o 3 a la oferta, se aplicará un 15% de descuento.
o Si le quedan más de 3 días se aplicará un 10% de descuento.
Después, se creará un programa principal y se crearán dos pedidos de productos 
estándar y 3 de productos ofertados con ofertas que caducan en 2 días, asignando un 
precio cualquiera a cada uno. Después mostrar el total del pedido teniendo en cuenta 
que se compran 5 unidades de cada uno.
*/

public class Test {
	
	public static void main(String[] args) {
		
		Producto p1 = new ProductoEstandar("Martillo",10.5,"Herramientas");
		Producto p2 = new ProductoEstandar("Sierra", 15.99, "Herramientas");
		Producto p3 = new ProductoOfertado("Alicates", 6, 1);
		Producto p4 = new ProductoOfertado("Destornillador", 4.5, 2);
		Producto p5 = new ProductoOfertado("Metro", 2, 4);
		
		Producto[] productos = {p1,p2,p3,p4,p5};
		
		for (Producto p : productos) {
			System.out.println( p.getNombre()+" "+p.obtenerPrecioPedido(5));
		}
	}
}
