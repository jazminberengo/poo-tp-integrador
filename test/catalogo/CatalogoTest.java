package catalogo;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import busqueda.NombreContiene;
import envios.Sucursal;

public class CatalogoTest {

    private Sucursal sucursal;
    private Producto productoBase;
    private Paquete paqueteBase;
    private Catalogo catalogo;

    @BeforeEach
    public void setUp() {
        sucursal = new Sucursal();

        productoBase = new Producto(
                "Procesador",
                "Intel i7",
                sucursal,
                "SKU-01",
                300.0f,
                0.5f,
                "Intel");
        productoBase.setDescuento(10f);

        paqueteBase = new Paquete(
                "Combo Gamer",
                "PC Components",
                sucursal);
        paqueteBase.setDescuento(5f);

        catalogo = new Catalogo();
    }

    // ---------- PRODUCTO ----------

    @Test
    public void testProductoValido() {
        assertTrue(productoBase.validar());
    }

    @Test
    public void testProductoSinNombre() {
        Producto p = new Producto(
                null, "Desc", sucursal,
                "SKU", 100f, 1f, "Marca");
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testProductoSinDescripcion() {
        Producto p = new Producto(
                "Nombre", null, sucursal,
                "SKU", 100f, 1f, "Marca");
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testProductoSinSku() {
        Producto p = new Producto(
                "Nombre", "Desc", sucursal,
                null, 100f, 1f, "Marca");
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testProductoSinMarca() {
        Producto p = new Producto(
                "Nombre", "Desc", sucursal,
                "SKU", 100f, 1f, null);
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testProductoSinPrecio() {
        Producto p = new Producto(
                "Nombre", "Desc", sucursal,
                "SKU", null, 1f, "Marca");
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testProductoSinDescuento() {
        Producto p = new Producto(
                "Nombre", "Desc", sucursal,
                "SKU", 100f, 1f, "Marca");

        p.setDescuento(null);

        assertFalse(p.validar());
    }

    @Test
    public void testProductoConAtributoDinamicoNull() {
        productoBase.addAtributoDinamico("Color", null);

        assertFalse(productoBase.validar());
    }

    @Test
    public void testAgregarAtributoRepetido() {
        productoBase.addAtributoDinamico("Garantia", "12 meses");

        assertDoesNotThrow(() ->
                productoBase.addAtributoDinamico("Garantia", "24 meses"));
    }

    @Test
    public void testGetPrecioBase() {
        assertEquals(300f, productoBase.getPrecioBase());
    }

    @Test
    public void testGetPrecioBaseConPrecioNull() {
        Producto p = new Producto(
                "Nombre", "Desc", sucursal,
                "SKU", null, 1f, "Marca");

        assertThrows(
                IllegalStateException.class,
                () -> p.getPrecioBase());
    }

    @Test
    public void testGetPrecioFinal() {
        assertEquals(270f, productoBase.getPrecioFinal());
    }

    @Test
    public void testGetPrecioFinalConDescuentoNull() {
        productoBase.setDescuento(null);

        assertThrows(
                IllegalStateException.class,
                () -> productoBase.getPrecioFinal());
    }

    @Test
    public void testGetPeso() {
        assertEquals(0.5f, productoBase.getPeso());
    }

    @Test
    public void testGetPesoConPesoNull() {
        Producto p = new Producto(
                "Nombre", "Desc", sucursal,
                "SKU", 100f, null, "Marca");

        assertThrows(
                IllegalStateException.class,
                () -> p.getPeso());
    }

    // ---------- PAQUETE ----------

    @Test
    public void testPaqueteValidarFallaPorNombreNull() {
        Paquete p = new Paquete(null, "Desc", sucursal);
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testPaqueteValidarFallaPorDescripcionNull() {
        Paquete p = new Paquete("Nombre", null, sucursal);
        p.setDescuento(10f);

        assertFalse(p.validar());
    }

    @Test
    public void testPaqueteValidarFallaPorDescuentoNull() {
        Paquete p = new Paquete("Nombre", "Desc", sucursal);
        p.setDescuento(null);

        assertFalse(p.validar());
    }

    @Test
    public void testPaqueteValido() {
        paqueteBase.agregar(productoBase);

        assertTrue(paqueteBase.validar());
    }

    @Test
    public void testPaqueteConItemInvalido() {
        Producto p = new Producto(
                null, "Desc", sucursal,
                "SKU", 100f, 1f, "Marca");
        p.setDescuento(10f);

        paqueteBase.agregar(p);

        assertFalse(paqueteBase.validar());
    }

    @Test
    public void testGetPrecioBasePaquete() {
        Producto p2 = new Producto(
                "Mouse",
                "Inalambrico",
                sucursal,
                "SKU-02",
                200f,
                1f,
                "Logitech");
        p2.setDescuento(0f);

        productoBase.setDescuento(0f);

        paqueteBase.agregar(productoBase);
        paqueteBase.agregar(p2);

        assertEquals(475f, paqueteBase.getPrecioBase());
    }

    @Test
    public void testGetPesoPaquete() {
        Producto p2 = new Producto(
                "Mouse",
                "Inalambrico",
                sucursal,
                "SKU-02",
                200f,
                1f,
                "Logitech");

        paqueteBase.agregar(productoBase);
        paqueteBase.agregar(p2);

        assertEquals(1.5f, paqueteBase.getPeso());
    }

    @Test
    public void testRemoverItem() {
        paqueteBase.agregar(productoBase);

        paqueteBase.remover(productoBase);

        assertEquals(0f, paqueteBase.getPeso());
    }

    @Test
    public void testTieneCategoriaFalse() {
        paqueteBase.agregar(productoBase);

        assertFalse(paqueteBase.tieneCategoria("Alimentos"));
    }

    // ---------- CATALOGO ----------

    @Test
    public void testAgregarYBuscar() {
        Producto mouse = new Producto(
                "Mouse Logitech",
                "Inalambrico",
                sucursal,
                "SKU-M",
                50f,
                1f,
                "Logitech");
        mouse.setDescuento(0f);

        catalogo.agregar(productoBase);
        catalogo.agregar(mouse);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("Mouse"));

        assertEquals(1, resultado.size());
        assertEquals(mouse, resultado.get(0));
    }

    @Test
    public void testBuscarSinResultados() {
        catalogo.agregar(productoBase);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("Impresora"));

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testBuscarIgnoraMayusculas() {
        catalogo.agregar(productoBase);

        List<ItemCatalogo> resultado =
                catalogo.buscar(new NombreContiene("procesador"));

        assertEquals(1, resultado.size());
    }
}