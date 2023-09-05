package principal.p5_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import principal.p1_clases_standar.modelo.Contacto;

class TestPrueba {
	
	static HashMap<String, Contacto> contactos;
	static GestorContactos prueba;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		prueba = new GestorContactos();
		contactos = new HashMap<>();
		contactos.put("juan@mail.com", new Contacto("Juan","juan@mail.com",20));
		contactos.put("pedro@mail.com", new Contacto("Pedro","pedro@mail.com",25));
		contactos.put("ana@mail.com", new Contacto("Ana","ana@mail.com",30));
		contactos.put("paco@mail.com", new Contacto("Paco","paco@mail.com",35));
		contactos.put("esther@mail.com", new Contacto("Esther","esther@mail.com",40));

	}

	@Test
	void testRecuperarPorEdadMaxima() {
		assertEquals(40, prueba.contactoMayor(contactos).getEdad());
	}

	@Test
	void testExisteContacto() {
		assertTrue(prueba.existeContacto("Paco", contactos));
		assertFalse(prueba.existeContacto("Maria", contactos));
	}

	@Test
	void testContactoMayor() {
		assertEquals("Esther", prueba.contactoMayor(contactos).getNombre());
	}

	@Test
	void testBuscarXNombre() {
		assertNotNull(prueba.buscarXNombre("Ana", contactos));
		assertNull(prueba.buscarXNombre("Maria", contactos));
	}

	@Test
	void testRecuperarNombres() {
		assertEquals(5, prueba.recuperarNombres(contactos).size());
	}

	@Test
	void testEdadMedia() {
		assertEquals(30, prueba.edadMedia(contactos));
	}

}
