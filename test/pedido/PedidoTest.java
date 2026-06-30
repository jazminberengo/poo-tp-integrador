package pedido;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.ItemCatalogo;
import envios.Direccion;
import envios.MetodoEnvio;
import envios.Registro;
import envios.Sucursal;
import reportes.ItemVisitor;

class PedidoTest {

    static class ItemStub extends ItemCatalogo {

        private float precio;

        public ItemStub(String nombre, float precio, Sucursal sucursal) {
            super(nombre, "descripcion", sucursal);
            this.precio = precio;
        }

        @Override
        public float getPrecioBase() {
            return precio;
        }

        @Override
        public float getPeso() {
            return 1;
        }

        @Override
        public boolean validar() {
            return true;
        }

        @Override
        public boolean tieneCategoria(String categoria) {
            return false;
        }

        @Override
        public void accept(ItemVisitor v) {
        }
    }

    static class EnvioFijo implements MetodoEnvio {

        @Override
        public float calcularCosto(Pedido p) {
            return 200;
        }

        @Override
        public int estimarDias(Pedido p) {
            return 3;
        }
    }

    private Pedido pedido;
    private ItemStub item1;
    private ItemStub item2;

    @BeforeEach
    void setUp() {
        Direccion direccion =
                new Direccion("Zapiola", "350", "Bernal",
                        "Buenos Aires", "1876", 5f);

        Cliente cliente = new Cliente(
                "Juan",
                "juan@mail.com",
                direccion);

        pedido = new Pedido("ORD-1", cliente);

        Sucursal sucursal = new Sucursal();

        item1 = new ItemStub("Teclado", 1000, sucursal);
        item2 = new ItemStub("Mouse", 500, sucursal);

        Registro r1 = new Registro();
        r1.setItem(item1);
        r1.setCantidad(10);

        Registro r2 = new Registro();
        r2.setItem(item2);
        r2.setCantidad(10);

        sucursal.agregarRegistro(r1);
        sucursal.agregarRegistro(r2);
    }

    @Test
    void confirmarPedido() {
        pedido.agregarItem(item1, 1);
        pedido.confirmar();

        assertTrue(pedido.getEstado() instanceof EstadoConfirmado);
    }

    @Test
    void cancelarPedido() {
        pedido.agregarItem(item1, 1);
        pedido.cancelar();

        assertTrue(pedido.getEstado() instanceof EstadoCancelado);
    }

    @Test
    void calculaSubtotal() {
        pedido.agregarItem(item1, 2);
        pedido.agregarItem(item2, 1);

        assertEquals(2500, pedido.getSubtotalProductos());
    }

    @Test
    void calculaTotalConEnvio() {
        pedido.setMetodoEnvio(new EnvioFijo());
        pedido.agregarItem(item1, 1);

        assertEquals(1200, pedido.getTotal());
    }

    @Test
    void cancelarGeneraNotaCredito() {
        pedido.setMetodoEnvio(new EnvioFijo());
        pedido.agregarItem(item1, 1);

        pedido.confirmar();
        pedido.cancelar();

        assertEquals(1, pedido.getNotasCredito().size());
    }

    @Test
    void guardarFechaEntrega() {
        pedido.agregarItem(item1, 1);

        pedido.confirmar();
        pedido.preparar();
        pedido.enviar();
        pedido.entregar();

        assertNotNull(pedido.getFechaEntrega());
    }

    @Test
    void noSePuedeAgregarItemsDespuesDeConfirmar() {
        pedido.agregarItem(item1, 1);
        pedido.confirmar();

        assertThrows(
                OperacionInvalidaException.class,
                () -> pedido.agregarItem(item2, 1)
        );
    }

    @Test
    void observadorRecibeNotificacion() {
        int[] llamadas = {0};

        pedido.suscribir(
                (p, anterior, nuevo) -> llamadas[0]++
        );

        pedido.agregarItem(item1, 1);
        pedido.confirmar();

        assertEquals(1, llamadas[0]);
    }
}