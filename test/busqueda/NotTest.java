package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;

class NotTest {
	
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
	void noDeberiaCumplirCuandoElCriterioEsVerdadero() {
		Not criterio = new Not(
				new NombreContiene("Teclado")
		);
		assertFalse(criterio.cumple(producto));
	}
	
	@Test
	void deberiaCumplirCuandoElCriterioEsFalso() {
		Not criterio = new Not(
				new NombreContiene("Mouse")
		);
		assertTrue(criterio.cumple(producto));
	}

}
