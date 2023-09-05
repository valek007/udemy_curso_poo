package principal.p2_orientacion_a_objetos.ejercicio1;

import principal.p2_orientacion_a_objetos.ejercicio1.modelo.CuentaLimitada;

/*
Crear una clase Cuenta, que incluya métodos para la realización de operaciones
sobre una cuenta(ingreso, extracción y recuperación de saldo), operaciones
que se llevarán a cabo sobre el atributo saldo de la clase.

Después, crear una subclase de esta que incluya un nuevo atributo limite,
que sirvirá para definir el límite máximo de dinero que se podrá extraer.
También incluirá un nuevo método, llamado ajustarLimite(), que establecerá
automáticamente como límite la mitad del saldo existente.
*/

public final class Test {
	
	public static void main(String[] args) {
		
		CuentaLimitada cuenta = new CuentaLimitada(1000, 300);
		cuenta.retiro(400);
		System.out.println(cuenta.obtenerSaldo());
		//CuentaLimitada no funciona
	}
}
