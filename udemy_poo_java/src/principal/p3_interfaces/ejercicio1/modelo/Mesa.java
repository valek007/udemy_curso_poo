package principal.p3_interfaces.ejercicio1.modelo;

import lombok.AllArgsConstructor;
import principal.p3_interfaces.interfaces.Proceso;

@AllArgsConstructor
public class Mesa implements Proceso{
	
	private int ancho, largo;

	@Override
	public void imprimir() {
		System.out.println("El ancho de la mesa es "+ancho);
		System.out.println("El largo de la mesa es "+largo);
	}

	@Override
	public void aumentar(int c) {
		ancho+=c;
		largo+=c;
	}
	
	

}
