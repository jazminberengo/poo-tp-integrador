package envios;

import catalogo.Producto;
import org.junit.jupiter.api.Test;
import pedido.Cliente;
import pedido.LineaPedido;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IMPORTANTE: requiere Pedido.setDireccion(Direccion) (ver PATCHES.md)
 * para poder fijar la dirección del pedido sin depender del flujo
 * completo de estados.
 */
class MetodoEnvioEstandarTest {

    @Test
    void calcularCostoDelegaEnCorreoArgentinoUsandoPesoTotalYDireccionDelPedido() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 2f, "MarcaX");
        Direccion direccion = new Direccion("Calle", "123", "CABA", "BsAs", "1000", 50f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);

        Pedido pedido = new Pedido("P1", cliente);
        pedido.setDireccion(direccion);
        pedido.getLineaPedidos().add(new LineaPedido(producto, 3)); // peso total = 2 * 3 = 6

        MetodoEnvioEstandar metodo = new MetodoEnvioEstandar();

        float esperado = CorreoArgentino.estimarCostoEnvio(6f, direccion);
        assertEquals(esperado, metodo.calcularCosto(pedido), 0.001f);
    }

    @Test
    void estimarDiasDelegaEnCorreoArgentinoSegunLaDireccionDelPedido() {
        Direccion cerca = new Direccion("Calle", "1", "CABA", "BsAs", "1000", 50f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", cerca);

        Pedido pedido = new Pedido("P1", cliente);
        pedido.setDireccion(cerca);

        MetodoEnvioEstandar metodo = new MetodoEnvioEstandar();

        assertEquals(5, metodo.estimarDias(pedido));
    }
}
