package principal.p2_orientacion_a_objetos.ejercicio6.modelo;

import java.time.LocalDate;
import java.time.Period;

import lombok.Getter;
import lombok.Setter;

/*
 - Director
Será un subtipo de empleado que incluirá los atributos:
 departamento. Nombre del departamento del que es director
 personal. Número de personas a su cargo
Sobrescribirá el método incentivar(), de modo que haga lo siguiente:
Si el lleva más de 30 meses en la empresa y tiene más de 20 personas a su cargo, se le 
incrementará su salario con el doble del bono. Si cumple solo una de las condiciones 
anteriores, se le incrementará su salario con el bono y si no cumple ninguna, no se hará nada
 */

@Getter
@Setter
public final class Director extends Empleado {

	private String departamento;
	private int personal;

	public Director(String nombre, int edad, LocalDate fechaIngreso, double salarioAnual, String departamento,
			int personal) {
		super(nombre, edad, fechaIngreso, salarioAnual);
		this.departamento = departamento;
		this.personal = personal;
	}

	@Override
	public void incentivar() {
//		long meses = ChronoUnit.MONTHS.between(getFechaIngreso(), LocalDate.now());
		
		//Podemos usar el periodo tambien
		Period period = Period.between(getFechaIngreso(), LocalDate.now());
		long meses = period.toTotalMonths();

		if (meses > 30 && personal > 20) {
			setSalarioAnual(getSalarioAnual() + (BONO * 2));
		} else if (meses > 30 || personal > 20) {
			setSalarioAnual(getSalarioAnual() + BONO);
		}
	}
}
