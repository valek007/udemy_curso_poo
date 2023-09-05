package principal.p6_acceso_a_ficheros.ejercicio4.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empleado {
	
	private String nombre;
	private String email;
	private double salario;
	private int edad;
	private String departamento;
	
	@Override
	public String toString() {
		return nombre+": ("+email+", "+salario+"€, "+edad+" años, "+departamento+")";
	}
	
	

}
