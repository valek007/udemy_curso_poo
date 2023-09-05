package principal.p1_clases_standar.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contacto {
	private String nombre, email;
	private int edad;
	@Override
	public String toString() {
		return this.nombre+": email("+this.email+") edad("+this.edad+")";
	}
}


