package principal.p2_orientacion_a_objetos.ejercicio6.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.Setter;

/*
 - Operario
Será un subtipo de empleado que incluirá el siguiente atributo:
 nivel. Nivel de seguridad del operario, puede ser un valor entre 1 y 5.
Sobrescribirá el método incentivar(), de modo que haga lo siguiente:
Si tiene más de 30 años y su nivel es superior a 2, se le incrementará su salario con el doble del 
bono. Si cumple solo una de las condiciones anteriores, se le incrementará su salario con el 
bono y si no cumple ninguna, no se hará nada.
Dispondrá además de un método actualizarNivel(), que en caso de que el empleado lleve más 
de dos años en la empresa se le subirá un nivel de seguridad. Si ya está en nivel 5 no se hará 
nad
 */

@Getter
@Setter
public final class Operario extends Empleado {

	private int nivel;

	public Operario(String nombre, int edad, LocalDate fechaIngreso, double salarioAnual, int nivel) {
		super(nombre, edad, fechaIngreso, salarioAnual);
		if (nivel < 0)
			this.nivel = 0;
		else if (nivel > 5)
			this.nivel = 5;
		else
			this.nivel = nivel;
		
		actualizarNivel();
	}

	public void actualizarNivel() {

		long tiempo = ChronoUnit.YEARS.between(getFechaIngreso(), LocalDate.now());
		
		if (tiempo > 2 && nivel < 5)
			nivel++;
	}

	@Override
	public void incentivar() {
		if (getEdad() > 30 && nivel > 2) {
			setSalarioAnual(getSalarioAnual() + (BONO * 2));
		} else if (getEdad() > 30 || nivel > 2) {
			setSalarioAnual(getSalarioAnual() + BONO);
		}
	}

}
