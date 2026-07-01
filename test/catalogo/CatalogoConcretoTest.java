package catalogo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import busqueda.NombreContiene;
import envios.Sucursal;

public class CatalogoConcretoTest {

    private Catalogo catalogo;
    private Producto monitor;
    private Producto mouse;
    private Producto teclado;
    private Sucursal sucursal;

    @BeforeEach
    public void setUp() {
        catalogo = new Catalogo();
        sucursal = new Sucursal();

        monitor = new Producto(
                "Monitor LG",
                "24 pulgadas",
                sucursal,
                "MON-01",
                100f,
                5f,
                "LG");

        mouse = new Producto(
                "Mouse Logitech",
                "Inalambrico",
                sucursal,
                "MOU-01",
                50f,
                1f,
                "Logitech");

        teclado = new Producto(
                "Teclado Redragon",
                "Mecanico",
                sucursal,
                "TEC-01",
                80f,
                1f,
                "Redragon");
    }

    @Test
    public void testAgregarYBuscarTodos() {
        catalogo.agregar(monitor);
        catalogo.agregar(mouse);
        catalogo.agregar(teclado);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene(""));

        assertEquals(3, resultado.size());
    }

    @Test
    public void testBuscarPorNombre() {
        catalogo.agregar(monitor);
        catalogo.agregar(mouse);
        catalogo.agregar(teclado);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("Mouse"));

        assertEquals(1, resultado.size());
        assertEquals(mouse, resultado.get(0));
    }

    @Test
    public void testBuscarSinResultados() {
        catalogo.agregar(monitor);
        catalogo.agregar(mouse);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("Impresora"));

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testBuscarIgnoraMayusculasYMinusculas() {
        catalogo.agregar(monitor);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("monitor"));

        assertEquals(1, resultado.size());
        assertEquals(monitor, resultado.get(0));
    }

    @Test
    public void testBuscarPorParteDelNombre() {
        catalogo.agregar(mouse);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("Logi"));

        assertEquals(1, resultado.size());
        assertEquals(mouse, resultado.get(0));
    }
}