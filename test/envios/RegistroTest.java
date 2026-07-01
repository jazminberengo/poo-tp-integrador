package envios;

import catalogo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.LineaPedido;

import static org.junit.jupiter.api.Assertions.*;

class RegistroTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        Sucursal sucursal = new Sucursal();
        producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
    }

    @Test
    void settersYGettersFuncionanCorrectamente() {
        Registro registro = new Registro();

        registro.setItem(producto);
        registro.setCantidad(10);

        assertEquals(producto, registro.getItem());
        assertEquals(10, registro.getCantidad());
    }

    @Test
    void cumpleParaEsTrueCuandoLaCantidadPedidaEsMenorAlStock() {
        Registro registro = new Registro();
        registro.setItem(producto);
        registro.setCantidad(10);

        LineaPedido linea = new LineaPedido(producto, 5);

        assertTrue(registro.cumplePara(linea));
    }

    @Test
    void cumpleParaEsTrueCuandoLaCantidadPedidaEsIgualAlStock() {
        Registro registro = new Registro();
        registro.setItem(producto);
        registro.setCantidad(5);

        LineaPedido linea = new LineaPedido(producto, 5);

        assertTrue(registro.cumplePara(linea));
    }

    @Test
    void cumpleParaEsFalseCuandoLaCantidadPedidaSuperaElStock() {
        Registro registro = new Registro();
        registro.setItem(producto);
        registro.setCantidad(3);

        LineaPedido linea = new LineaPedido(producto, 4);

        assertFalse(registro.cumplePara(linea));
    }
}
