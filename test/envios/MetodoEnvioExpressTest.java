package envios;

import catalogo.Producto;
import org.junit.jupiter.api.Test;
import pedido.Cliente;
import pedido.LineaPedido;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;

class MetodoEnvioExpressTest {

    @Test
    void calcularCostoDelegaEnEnvioExpressUsandoElSubtotalDeProductos() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Direccion direccion = new Direccion("Calle", "1", "CABA", "BsAs", "1000", 10f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);

        Pedido pedido = new Pedido("P1", cliente);
        pedido.getLineaPedidos().add(new LineaPedido(producto, 2)); // subtotal = 100 * 2 = 200

        MetodoEnvioExpress metodo = new MetodoEnvioExpress();

        float esperado = EnvioExpress.calcularCosto(200f);
        assertEquals(esperado, metodo.calcularCosto(pedido), 0.001f);
    }

    @Test
    void estimarDiasSiempreDevuelveUno() {
        Direccion direccion = new Direccion("Calle", "1", "CABA", "BsAs", "1000", 10f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);
        Pedido pedido = new Pedido("P1", cliente);

        MetodoEnvioExpress metodo = new MetodoEnvioExpress();

        assertEquals(1, metodo.estimarDias(pedido));
    }
}
