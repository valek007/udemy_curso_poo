package principal.p6_acceso_a_ficheros.ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/*
 Interfaz Path
 
 - Un objeto Path representa un fichero o directorio.
 - Para crear un objeto Path, utilizamos el método estático
 get(String url) de la clase Paths:
 
 Path pt = Paths.get("c:\\documentos\\datos.txt");
 
 - Utilizado como parámetro y tipo de devolución en las operaciones 
 sobre ficheros realizadas a través de los métodos de Files.
 
 Clase Files
 
 Proporciona un amplio conjunto de métodos para operar con ficheros/directorios.
 Entre ellos:
 
 - static void delete(Path pt). Elimina el fichero
 - static long copy(Path source, OutputStream out). Copia el contenido
 del fichero en la fuente de salida indicada.
 - static Stream<String> lines(Path pt). Devuelve un Stream con 
 las líneas del fichero.
 - static List<String> readAllLines(Path pt). Devuelve una lista con las 
 lineas de texto contenidas en el fichero.
 */

public class Test {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		//Se recupera una lista completa que luego puede ser recorrida
		Path pt = Paths.get("src/principal/p6_acceso_a_ficheros/ejercicio1/recursos/datos.txt");
		List<String> cadenas = Files.readAllLines(pt);
		for(String s: cadenas) {
			System.out.print(s+" ");
		}
		System.out.println();
		//el proceso es mucho mas sencillo que con la clase java.io
		
		//Busqueda en un fichero
		String buscar = "Juan";
		Stream<String> nombres = Files.lines(pt);
		if(nombres.anyMatch(s->s.equals(buscar))) {
			System.out.println("El nombre está en el fichero");
		}else {
			System.out.println("El nombre no está en el fichero");
		}
	}

}
