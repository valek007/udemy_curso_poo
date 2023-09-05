package principal.p6_acceso_a_ficheros.ejercicio3.logica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import principal.p6_acceso_a_ficheros.ejercicio3.modelo.Alumno;

/*
Dado un fichero CSV(fichero en el que cada campo se encuentra separado
del siguiente por una coma) que contiene los datos de una serie de alumnos,
realizar la logica de negocio de un programa que proporcione 
la siguiente funcionalidad:

campos fichero: alumno,curso,nota
 */
public class GestionAlumnos {
	
	public final String RUTA_FICHERO = "src/principal/p6_acceso_a_ficheros/ejercicio3/recursos/cursos";
	
	/** Nota media de los alumnos pertenecientes a un determinado curso */
	public double obtenerNotaMedia(String curso, String ruta) {
		
		return obtenerAlumnos(ruta)
				.filter(c->c.getCurso().equals(curso)) //Stream<Alumno>
				.mapToDouble(d->d.getNota()) //Double Stream
				.average()//Optional Double
				.orElse(0.0);
	}
	
	/** Lista de alumnos de un determinado curso. */
	public List<Alumno> obtenerListaXCurso(String curso, String ruta){

		return obtenerAlumnos(ruta)
			.filter(c->c.getCurso().equals(curso)) //filtrar por curso
			.collect(Collectors.toList());//obtener alumnos
	}
	
	/** Alumno con la nota mas alta. */
	public Alumno obtenerMejorAlumno(String ruta) {

		return obtenerAlumnos(ruta)
				.max((a,b)->Double.compare(a.getNota(), b.getNota())) //Optional
				.orElse(null);
	}
	
	/** Lista con los nombres de todos los cursos. */
	public List<String> obtenerCursos(String ruta){
		
		return obtenerAlumnos(ruta) //Stream<Alumno>
				.map(d->d.getCurso()) //Stream<String>
				.distinct() //Elimina repetidos
				.collect(Collectors.toList());
	}
	
	/** Devuelve un stream con las cadenas obtenidas de un fichero */
	private Stream<String> leerFichero(String ruta){
		
		Path pt = Paths.get(ruta);
		Stream<String> st = null;
		try {
			return st = Files.lines(pt);
		}catch(IOException e) {
			return st;
		}
	}
	
	private Stream<Alumno> obtenerAlumnos(String ruta){
		
		return leerFichero(ruta)
				.map(s->{
					String[] datos = s.split(",");
					return new Alumno(datos[0], datos[1], Double.parseDouble(datos[2]));
				});
	}
}
