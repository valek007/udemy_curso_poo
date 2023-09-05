package principal.p2_orientacion_a_objetos.ejercicio3;

import principal.p2_orientacion_a_objetos.ejercicio3.modelo.CuentaMovimientos;
import principal.p2_orientacion_a_objetos.ejercicio3.modelo.Movimiento;

/*
Modificar la clase CuentaLimitada del ejercicio 7 para que limite 
las extracciones al valor del atributo limite.

Después, se creará una nueva clase que registre los movimientos
realizados en la cuenta (ingreso y extracción).

Cada movimiento se caracteriza por una cantidad y un tipo
(ingreso o extracción).

Incorporará un nuevo método que devuelva los movimientos registrados.
*/



public class Test {
	
	public static void main(String[] args) {
		CuentaMovimientos cuenta = new CuentaMovimientos(100, 50);
		System.out.println(cuenta.obtenerSaldo());
		
		cuenta.retiro(10);
		cuenta.retiro(60);
		cuenta.ingresar(100);
		cuenta.retiro(50);
		
		for (Movimiento m : cuenta.obtenerMovimientos()) {
			System.out.println(m.toString());
		}
		
		System.out.println(cuenta.obtenerSaldo());
	}
}
