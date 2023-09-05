package principal.p7_acceso_a_base_de_datos.ejercicio2.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contacto {
	private int idContacto;
	private String nombre;
	private String email;
	private int edad;

	public Contacto(String nombre, String email, int edad) {
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Contacto "+idContacto+": ("+nombre+", "+email+", "+edad+")";
	}
}
