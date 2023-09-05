package principal.p2_orientacion_a_objetos.ejercicio3.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Movimiento {
	private double cantidad;
	private String tipo;
	
	@Override
	public String toString() {
		if(tipo.equals("Ingreso"))
			return "Se ha ingresado "+cantidad+"€";
		else
			return "Se ha retirado "+cantidad+"€";
	}
}
