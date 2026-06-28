package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;

class PrecioMaximoTest {
	
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
	void deberiaCumplirCuandoElPrecioEsMenorAlMaximo() {
		PrecioMaximo criterio = new PrecioMaximo(30000f);
		assertTrue(criterio.cumple(producto));
	}

	@Test
	void deberiaCumplirCuandoElPrecioEsIgualAlMaximo() {
		PrecioMaximo criterio = new PrecioMaximo(25000f);
		assertTrue(criterio.cumple(producto));
	}
	
	@Test
	void noDeberiaCumplirCuandoElPrecioSuperaElMaximo() {
		PrecioMaximo criterio = new PrecioMaximo(20000f);
		assertFalse(criterio.cumple(producto));
	}
	
}
