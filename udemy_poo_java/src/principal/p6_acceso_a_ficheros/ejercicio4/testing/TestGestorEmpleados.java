package principal.p6_acceso_a_ficheros.ejercicio4.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import principal.p6_acceso_a_ficheros.ejercicio4.logica.GestorEmpleados;
import principal.p6_acceso_a_ficheros.ejercicio4.presentacion.VistaEmpleados;

class TestGestorEmpleados {

	private static VistaEmpleados vista;
	private static GestorEmpleados prueba;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		vista = new VistaEmpleados();
		prueba = new GestorEmpleados();
	}

	@Test
	void testExisteEmpleado() {
		assertNotNull(prueba.existeEmpleado(vista.RUTA_ARCHIVO, "ana@mail.com"));
	}

	@Test
	void testObtenerEmpleadosXEdad() {
		assertEquals(13, prueba.obtenerEmpleadosXEdad(vista.RUTA_ARCHIVO).size());
	}

	@Test
	void testObtenerEmpleadosXDep() {
		assertEquals(3, prueba.obtenerEmpleadosXDep(vista.RUTA_ARCHIVO, "restaurante").size());
	}

	@Test
	void testObtenerMediaSalarioXDep() {
		assertEquals(1500.0, prueba.obtenerMediaSalarioXDep(vista.RUTA_ARCHIVO, "restaurante"),0.5);
	}

	@Test
	void testObtenerMejorAsalariado() {
		assertEquals("Carlos", prueba.obtenerMejorAsalariado(vista.RUTA_ARCHIVO).getNombre());
	}
}
