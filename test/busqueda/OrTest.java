package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import catalogo.Producto;
import envios.Sucursal;

class OrTest {

	private Producto producto;
	private Sucursal sucursal;
	
	@BeforeEach
	void setUp() {
		sucursal = new Sucursal();
		
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
	void deberiaCumplirCuandoTodosLosCriteriosSonVerdaderos() {
		Or criterio = new Or(List.of(
				new NombreContiene("Teclado"),
				new PrecioMaximo(30000f)
		));
		assertTrue(criterio.cumple(producto));
	}

	@Test
	void deberiaCumplirCuandoElPrimeroEsVerdadero() {
		Or criterio = new Or(List.of(
				new NombreContiene("Teclado"),
				new PrecioMaximo(10000f)
		));
		assertTrue(criterio.cumple(producto));
	}
	
	@Test
	void deberiaCumplirCuandoElSegundoEsVerdadero() {
		Or criterio = new Or(List.of(
				new NombreContiene("Mouse"),
				new PrecioMaximo(30000f)
		));
		assertTrue(criterio.cumple(producto));
	}
	
	@Test
	void noDeberiaCumplirCuandoNingunCriterioEsVerdadero() {
		Or criterio = new Or(List.of(
				new NombreContiene("Mouse"),
				new PrecioMaximo(10000f)
		));
		assertFalse(criterio.cumple(producto));
	}
}
