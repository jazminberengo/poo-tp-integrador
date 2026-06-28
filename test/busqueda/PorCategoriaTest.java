package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;
import envios.Sucursal;

class PorCategoriaTest {

	private Producto producto;
	private Sucursal sucursal;
	
	@BeforeEach
	void setUp() {
		sucursal = new Sucursal();
		
		producto = new Producto(
				"Teclado",
				"Teclado mecanico",
				sucursal,
				"SKU001",
				1000f,
				1f,
				"Logitech"
		);
		
		producto.setCategoria("Electronica");
	}

	@Test
	void deberiaCumplirCuandoElProductoPerteneceALaCategoria() {
		PorCategoria criterio = new PorCategoria("Electronica");
		
		assertTrue(criterio.cumple(producto));
	}
	
	@Test
	void noDeberiaCumplirCuandoElProductoNoPerteneceALaCategoria() {
		PorCategoria criterio = new PorCategoria("Hogar");
		
		assertFalse(criterio.cumple(producto));
	}

}
