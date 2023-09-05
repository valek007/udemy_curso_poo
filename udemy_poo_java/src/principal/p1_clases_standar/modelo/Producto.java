package principal.p1_clases_standar.modelo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {
	
	private String nombre;
	private double precio;
	private int unidades;
	private Date fecha;
	
	@Override
	public String toString() {
		return this.nombre+": precio("+this.precio+"€) unidades("+this.unidades+") fecha almacenamiento("+this.fecha+")";
	}
}
