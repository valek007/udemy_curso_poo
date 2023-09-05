package principal.p6_acceso_a_ficheros.ejercicio3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import principal.p6_acceso_a_ficheros.ejercicio3.logica.GestionAlumnos;

class TestManejoNotas {
	
	static GestionAlumnos gestionAlumnos;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gestionAlumnos = new GestionAlumnos();
	}

	@Test
	void testObtenerNotaMedia() {
		assertEquals(6.4, gestionAlumnos.obtenerNotaMedia("Python", gestionAlumnos.RUTA_FICHERO),0.1);
		assertEquals(6.75, gestionAlumnos.obtenerNotaMedia("Java", gestionAlumnos.RUTA_FICHERO));
	}

	@Test
	void testObtenerListaXCurso() {
		assertEquals(2, gestionAlumnos.obtenerListaXCurso("Java", gestionAlumnos.RUTA_FICHERO).size());
		assertEquals(0, gestionAlumnos.obtenerListaXCurso("Angular", gestionAlumnos.RUTA_FICHERO).size());
	}

	@Test
	void testObtenerMejorAlumno() {
		assertEquals("a3", gestionAlumnos.obtenerMejorAlumno(gestionAlumnos.RUTA_FICHERO).getNombre());
	}

	@Test
	void testObtenerCursos() {
		assertEquals(3, gestionAlumnos.obtenerCursos(gestionAlumnos.RUTA_FICHERO).size());
	}

}
