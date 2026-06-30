package busqueda;

import static org.junit.jupiter.api.Assertions.*;
import envios.Sucursal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;
import java.util.List;

class AndTest {

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
		And criterio = new And(List.of(
				new NombreContiene("Teclado"),
				new PrecioMaximo(30000f)
		));
		assertTrue(criterio.cumple(producto));
	}
	
	@Test
	void noDeberiaCumplirCuandoFallaElPrimerCriterio() {
		And criterio = new And(List.of(
				new NombreContiene("Mouse"),
				new PrecioMaximo(30000f)
		));
		assertFalse(criterio.cumple(producto));
	}
	
	@Test
	void noDeberiaCumplirCuandoFallaElSegundoCriterio() {
		And criterio = new And(List.of(
				new NombreContiene("Teclado"),
				new PrecioMaximo(20000f)
		));
		assertFalse(criterio.cumple(producto));
	}

}
