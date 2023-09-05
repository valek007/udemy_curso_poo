package principal.p2_orientacion_a_objetos.ejercicio6;
/*
En este ejercicio deberás crear una jerarquía de clases que representen los empleados de una 
empresa y su funcionalidad.

Se deberán crear las siguientes clases:

- Empleado
Es la clase padre, contiene los atributos nombre. Nombre del empleado edad. Edad del empleado
fechaIngreso. Fecha en la que ingresó en la empresa salario. Salario anual.
Además de los constructores y métodos setter/getter, incluirá un método abstracto llamado 
incentivar(), que no devolverá ningún resultado ni recibirá ningún parámetro.
También incluirá una constante doble llamada BONO, con un valor predefinido.

- Director
Será un subtipo de empleado que incluirá los atributos:
 departamento. Nombre del departamento del que es director
 personal. Número de personas a su cargo
Sobrescribirá el método incentivar(), de modo que haga lo siguiente:
Si el lleva más de 30 meses en la empresa y tiene más de 20 personas a su cargo, se le 
incrementará su salario con el doble del bono. Si cumple solo una de las condiciones 
anteriores, se le incrementará su salario con el bono y si no cumple ninguna, no se hará nada

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import principal.p2_orientacion_a_objetos.ejercicio6.modelo.Director;
import principal.p2_orientacion_a_objetos.ejercicio6.modelo.Empleado;
import principal.p2_orientacion_a_objetos.ejercicio6.modelo.Operario;

public class Test {
	
	public static void main(String[] args) {
		
		Empleado valerio = new Director("Valerio", 20, LocalDate.of(2023, 1, 1), 20000, "Recepcion", 30);
		valerio.incentivar();
		mostrarDatosEmpleados(valerio);
		System.out.println();
		
		Operario pedro = new Operario("Pedro", 28, LocalDate.of(2020, 1, 1), 15000, 2);
		pedro.incentivar();
		mostrarDatosEmpleados(pedro);
		System.out.println(pedro.getNivel());
		
	}
	
	private static void mostrarDatosEmpleados(Empleado e) {
		
		System.out.println("Nombre "+e.getNombre());
		System.out.println("Edad "+e.getEdad());
		System.out.println("Fecha Ingreso "+e.getFechaIngreso());
		System.out.println("Salario Anual "+e.getSalarioAnual());
	}
	
	public static void comprobarFechasChronoUnit() {
		// Crear dos fechas LocalDate
		LocalDate fecha1 = LocalDate.of(2020, 1, 1);
		LocalDate fecha2 = LocalDate.of(2023, 8, 15);

		// Obtener el número de meses entre las dos fechas
		long meses = ChronoUnit.MONTHS.between(fecha1, fecha2);

		// Mostrar el resultado
		System.out.println("La diferencia entre " + fecha1 + " y " + fecha2 + " es de " + meses + " meses.");
	}
	
	public static void comparacionPeriodYChronoUnit() {
		// Crear dos objetos LocalDate
	    LocalDate date1 = LocalDate.of(2023, 8, 15);
	    LocalDate date2 = LocalDate.of(2023, 9, 15);

	    // Crear un objeto Period entre las dos fechas
	    Period period = Period.between(date1, date2);
	    System.out.println("Periodo entre " + date1 + " y " + date2 + ": " + period);

	    // Crear dos objetos LocalTime
	    LocalTime time1 = LocalTime.of(15, 17, 13);
	    LocalTime time2 = LocalTime.of(16, 25, 03);

	    // Crear un objeto ChronoUnit entre las dos horas
	    long hours = ChronoUnit.HOURS.between(time1, time2);
	    System.out.println("Horas entre " + time1 + " y " + time2 + ": " + hours);
	}
}
