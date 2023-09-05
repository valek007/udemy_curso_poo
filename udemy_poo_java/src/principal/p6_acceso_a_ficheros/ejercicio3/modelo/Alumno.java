package principal.p6_acceso_a_ficheros.ejercicio3.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alumno {
	private String nombre;
	private String curso;
	private double nota;
}
