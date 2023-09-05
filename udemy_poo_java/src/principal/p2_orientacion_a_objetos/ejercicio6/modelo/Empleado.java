package principal.p2_orientacion_a_objetos.ejercicio6.modelo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 - Empleado
Es la clase padre, contiene los atributos nombre. Nombre del empleado edad. Edad del empleado
fechaIngreso. Fecha en la que ingresó en la empresa salario. Salario anual.
Además de los constructores y métodos setter/getter, incluirá un método abstracto llamado 
incentivar(), que no devolverá ningún resultado ni recibirá ningún parámetro.
También incluirá una constante doble llamada BONO, con un valor predefinido.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Empleado {
	private String nombre;
	private int edad;
	private LocalDate fechaIngreso;
	private double salarioAnual;
	
	public abstract void incentivar();
	
	public final static double BONO = 5_000;
}
