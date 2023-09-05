package principal.p6_acceso_a_ficheros.ejercicio4.logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import principal.p6_acceso_a_ficheros.ejercicio4.modelo.Empleado;

/*
					Ejercicio Streams
					
Realizar una aplicación para gestión de empleados. Cada empleado se caracteriza por los 
siguientes datos: nombre, email, salario, edad y departamento. Los empleados son 
almacenados en un fichero de texto, siguiendo el formato:
nombre,email,salario,edad,departamento
String nombre, String email, double salario, int edad,String departamento

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
public class GestorEmpleados {

	/** Alta de un empleado a partir de sus datos */
	public boolean altaEmpleado(String ruta, Empleado empleado) {

		if (escribirArchivoEmpleados(ruta, empleado))
			return true;
		else
			return false;
	}

	/** Obtener empleado a partir de su email, si no existe devolverá null */
	public Empleado existeEmpleado(String ruta, String email) {

		return leerArchivoEmpleados(ruta)
				.filter(e -> e.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}

	/** Obtener empleados ordenados por edad */
	public List<Empleado> obtenerEmpleadosXEdad(String ruta) {

		return leerArchivoEmpleados(ruta)
				.sorted(Comparator.comparingInt(Empleado::getEdad).reversed())
				.collect(Collectors.toList());
	}

	/** Dado un departamento, obtener todos los empleados del mismo */
	public List<Empleado> obtenerEmpleadosXDep(String ruta, String departamento) {

		return leerArchivoEmpleados(ruta)
				.filter(e->e.getDepartamento().equals(departamento))
				.collect(Collectors.toList());
	}

	/** Calcular la media de salario de un departamento */
	public double obtenerMediaSalarioXDep(String ruta, String departamento) {

		return leerArchivoEmpleados(ruta)
				.filter(e->e.getDepartamento().equals(departamento))
				.mapToDouble(d->d.getSalario())
				.average()
				.orElse(0.0);
	}

	/** Nombre del empleado con mayor salario */
	public Empleado obtenerMejorAsalariado(String ruta) {

		return leerArchivoEmpleados(ruta)
				.max((a,b)->Double.compare(a.getSalario(), b.getSalario()))
				.orElse(null);
	}

	// -----MANEJO FICHERO-----------------

	/** Devuelve un stream con todos los empleados */
	public Stream<Empleado> leerArchivoEmpleados(String ruta) {

		Path archivo = Paths.get(ruta); // 1. Crear el path (ruta del archivo)
		Stream<Empleado> empleados = null; // 2. Crear el Stream
		try {// Acceso al archivo puede provacar una Excepcion de tipo Entrada o Salida de
				// datos
			empleados = Files.lines(archivo).map(s -> { // 3. Recorrer el archivo con Files.lines
				String[] d = s.split("[,]"); // 4. Dentro del map separar los datos
				// 5.Devolver un objeto rellenado por los datos
				return new Empleado(d[0], d[1], Double.parseDouble(d[2]), Integer.parseInt(d[3]) , d[4]);
			});
			return empleados;
		} catch (IOException e) {
			return empleados;
		}
	}

	/** Devuelve un boolean, true si el empleado se ha podido agregar al archivo */
	public boolean escribirArchivoEmpleados(String ruta, Empleado e) {
		// 2. Usar el método FileWriter para solo agregar una linea
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
			// 3. Escribir texto en el archivo
			bw.newLine();
			bw.append(e.getNombre() + "," + e.getEmail() + "," + e.getSalario() + "," + e.getEdad() + ","
					+ e.getDepartamento());
			// 4. Cerrar el stream
			bw.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
}
