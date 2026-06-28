package busqueda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Producto;
import envios.Sucursal;
import envios.Registro;

class DisponibleTest {

	private Producto producto;
	private Sucursal sucursal;
	private Disponible criterio;

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
		
		criterio = new Disponible();
	}
	@Test
	void deberiaCumplirCuandoHayStockDisponible() {
		Registro registro = new Registro();
		registro.setItem(producto);
		registro.setCantidad(5);
		
		sucursal.agregarRegistro(registro);
		
		assertTrue(criterio.cumple(producto));
	}

	@Test
	void noDeberiaCumplirCuandoNoHayStockDisponible() {
		Registro registro = new Registro();
		registro.setItem(producto);
		registro.setCantidad(0);
		
		sucursal.agregarRegistro(registro);
		
		assertFalse(criterio.cumple(producto));
	}
}
