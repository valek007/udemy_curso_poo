package principal.p5_testing;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import principal.p1_clases_standar.modelo.Contacto;

/*
 Testing JUnit
 
 - Libreria para la realizacion de pruebas unitarias.
 - Una prueba unitaria comprueba el funcionamiento de un metodo de forma aislada.
 - Se basa en realizar suposiciones sobre el resultado de la llamada a un metodo.
 - Si la suposicion es correcta, prueba superada, sino, prueba fallada.
 
 Clases de prueba:
 
 - Cada metodo que debe ser ejecutado al lanzar la prueba se anotara con @Test
 - Si se debe realizar alguna tarea antes de lanzar las pruebas, se definira en 
 un metodo @BeforeAll, si esta tarea se debe ejecutar antes de cada prueba el
 metodo se anotara con @BeforeEach.
 
 - En los metodos de test, haremos uso de la clase Assertions de JUnit para
 realizar las suposiciones.
 */
public class GestorContactos {
	
	// -------NUEVOS METODOS ANTERIORMENTE IMPLEMENTANDOS STREAMS--------

		// recuperar contactos por edad maxima
		public Contacto[] recuperarPorEdadMaxima(int edad, HashMap<String, Contacto> contactos) {

			Stream<Contacto> st = contactos.values().stream();
			return st.filter(c -> c.getEdad() <= edad).toArray(t -> new Contacto[t]);
		}

		// indica si existe un contacto con este nombre
		public boolean existeContacto(String nombre, HashMap<String, Contacto> contactos) {

			Stream<Contacto> st = contactos.values().stream();
			return st.anyMatch(c -> c.getNombre().equals(nombre));
		}

		// devuelve el contacto con mas edad
		public Contacto contactoMayor(HashMap<String, Contacto> contactos) {

			Stream<Contacto> st = contactos.values().stream();
			return st.max((c1, c2) -> c1.getEdad() - c2.getEdad()).get();
		}

		// devuelve el primer contacto que coincida con el nombre
		public Contacto buscarXNombre(String nombre, HashMap<String, Contacto> contactos) {

			Stream<Contacto> st = contactos.values().stream();
			return st.filter(c -> c.getNombre().equals(nombre)).findFirst().orElse(null);
		}

		// devuelve las lista de nombres
		public List<String> recuperarNombres(HashMap<String, Contacto> contactos) {

			Stream<Contacto> st = contactos.values().stream();
			return st.map(c -> c.getNombre()).collect(Collectors.toList());
		}

		// devuelve la edad media de todos los contactos
		public double edadMedia(HashMap<String, Contacto> contactos) {

			Stream<Contacto> st = contactos.values().stream();
			return st.mapToInt(c -> c.getEdad()).average().getAsDouble();
		}
}
