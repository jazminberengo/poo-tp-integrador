package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;

class NombreContieneTest {

	private Producto producto;
	
	@BeforeEach
	void setUp() {
		producto = new Producto(
				"Teclado Mecanico",
				"Teclado RGB",
				"SKU001",
				25000f,
				1.2f,
				"Logitech"
		);
	}
	
	@Test
	void deberiaEncontrarTextoExistenteEnElNombre() {
		NombreContiene criterio = new NombreContiene("Teclado");
		assertTrue(criterio.cumple(producto));
	}

	@Test
	void noDeberiaEncontrarTextoInexistente() {
		NombreContiene criterio = new NombreContiene("Mouse");
		assertFalse(criterio.cumple(producto));
	}
	
	@Test
	void deberiaIgnorarMayusculasYMinusculas() {
		NombreContiene criterio = new NombreContiene("teclado");
		assertTrue(criterio.cumple(producto));
	}
}
