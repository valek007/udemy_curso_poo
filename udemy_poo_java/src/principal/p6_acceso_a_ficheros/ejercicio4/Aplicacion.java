package principal.p6_acceso_a_ficheros.ejercicio4;

import principal.p6_acceso_a_ficheros.ejercicio4.presentacion.VistaEmpleados;

/*
Ejercicio Streams

Realizar una aplicación para gestión de empleados. Cada empleado se caracteriza por los 
siguientes datos: nombre, email, salario, edad y departamento. Los empleados son 
almacenados en un fichero de texto, siguiendo el formato:
nombre,email,salario,edad,departamento

El programa deberá permitir realizar las siguientes operaciones:
 Alta de un empleado a partir de sus datos
 Obtener empleado a partir de su email, si no existe devolverá null
 Obtener empleados ordenados por edad
 Dado un departamento, obtener todos los empleados del mismo
 Calcular la media de salario de un departamento
 Nombre del empleado con mayor salario

Para el desarrollo de la aplicación utilizaremos streams y java.nio.
Después, crear una clase principal con un menú que permita exponer todas las operaciones.
*/
public class Aplicacion {
	
	public static void main(String[] args) {
		new VistaEmpleados().arrancarVista();
	}

}