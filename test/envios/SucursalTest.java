package envios;

import catalogo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Cliente;
import pedido.LineaPedido;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;

class SucursalTest {

    private Sucursal sucursal;
    private Producto producto;

    @BeforeEach
    void setUp() {
        sucursal = new Sucursal();
        producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
    }

    private Registro registrarStock(int cantidad) {
        Registro registro = new Registro();
        registro.setItem(producto);
        registro.setCantidad(cantidad);
        sucursal.agregarRegistro(registro);
        return registro;
    }

    private Pedido crearPedidoCon(int cantidadPedida) {
        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 5f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);
        Pedido pedido = new Pedido("P1", cliente);
        pedido.getLineaPedidos().add(new LineaPedido(producto, cantidadPedida));
        return pedido;
    }

    @Test
    void agregarRegistroLoIndexaPorNombreDeItem() {
        registrarStock(10);

        assertTrue(sucursal.tieneStock(producto));
    }

    @Test
    void tieneStockEsFalseSiNoHayRegistroParaElItem() {
        assertFalse(sucursal.tieneStock(producto));
    }

    @Test
    void tieneStockEsFalseSiLaCantidadRegistradaEsCero() {
        registrarStock(0);

        assertFalse(sucursal.tieneStock(producto));
    }

    @Test
    void decrementarStockRestaLaCantidadIndicadaAlRegistro() {
        Registro registro = registrarStock(10);

        sucursal.decrementarStock(producto, 4);

        assertEquals(6, registro.getCantidad());
    }

    @Test
    void incrementarStockSumaLaCantidadIndicadaAlRegistro() {
        Registro registro = registrarStock(10);

        sucursal.incrementarStock(producto, 5);

        assertEquals(15, registro.getCantidad());
    }

    @Test
    void tieneStockParaEsTrueCuandoTodasLasLineasCumplenElStockDisponible() {
        registrarStock(10);
        Pedido pedido = crearPedidoCon(5);

        assertTrue(sucursal.tieneStockPara(pedido));
    }

    @Test
    void tieneStockParaEsTrueCuandoLaCantidadPedidaEsExactamenteElStock() {
        registrarStock(5);
        Pedido pedido = crearPedidoCon(5);

        assertTrue(sucursal.tieneStockPara(pedido));
    }

    @Test
    void tieneStockParaEsFalseCuandoAlgunaLineaSuperaElStockDisponible() {
        registrarStock(3);
        Pedido pedido = crearPedidoCon(5);

        assertFalse(sucursal.tieneStockPara(pedido));
    }

    @Test
    void tieneStockParaTiraNullPointerSiElItemNoTieneRegistroDeStock() {
        Pedido pedido = crearPedidoCon(1);

        assertThrows(NullPointerException.class, () -> sucursal.tieneStockPara(pedido));
    }
}
