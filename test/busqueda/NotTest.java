package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;
import envios.Sucursal;

class NotTest {
	
	private Producto producto;
	private Sucursal sucursal;
	
	@BeforeEach
	void setUp() {
		producto = new Producto(
				"Teclado Mecanico",
				"Teclado RGB",
				sucursal,
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
