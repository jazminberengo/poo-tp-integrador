package envios;

import catalogo.Producto;
import org.junit.jupiter.api.Test;
import pedido.Cliente;
import pedido.LineaPedido;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;

class MetodoRetiroSucursalTest {

    @Test
    void calcularCostoSiempreEsCero() {
        Sucursal sucursal = new Sucursal();
        MetodoRetiroSucursal metodo = new MetodoRetiroSucursal(sucursal);

        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 5f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);
        Pedido pedido = new Pedido("P1", cliente);

        assertEquals(0f, metodo.calcularCosto(pedido));
    }

    @Test
    void estimarDiasEsCeroCuandoLaSucursalTieneStockParaTodoElPedido() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Registro registro = new Registro();
        registro.setItem(producto);
        registro.setCantidad(10);
        sucursal.agregarRegistro(registro);

        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 5f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);
        Pedido pedido = new Pedido("P1", cliente);
        pedido.getLineaPedidos().add(new LineaPedido(producto, 5));

        MetodoRetiroSucursal metodo = new MetodoRetiroSucursal(sucursal);

        assertEquals(0, metodo.estimarDias(pedido));
    }

    @Test
    void estimarDiasEsTresCuandoLaSucursalNoTieneStockSuficiente() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Registro registro = new Registro();
        registro.setItem(producto);
        registro.setCantidad(2);
        sucursal.agregarRegistro(registro);

        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 5f);
        Cliente cliente = new Cliente("Juan", "juan@mail.com", direccion);
        Pedido pedido = new Pedido("P1", cliente);
        pedido.getLineaPedidos().add(new LineaPedido(producto, 5));

        MetodoRetiroSucursal metodo = new MetodoRetiroSucursal(sucursal);

        assertEquals(3, metodo.estimarDias(pedido));
    }
}
