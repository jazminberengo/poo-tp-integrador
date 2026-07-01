package reportes;

import catalogo.Producto;
import envios.Direccion;
import envios.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Cliente;
import pedido.LineaPedido;
import pedido.Pedido;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GeneradorReportesTest {

    private GeneradorReportes generador;
    private Producto producto;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        generador = new GeneradorReportes();
        Sucursal sucursal = new Sucursal();
        producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Direccion direccion = new Direccion("Calle Falsa", "123", "CABA", "Bs As", "1000", 5f);
        cliente = new Cliente("Juan Perez", "juan@mail.com", direccion);
    }

    private Pedido crearPedidoConFecha(String id, LocalDate fechaEntrega, int cantidad) {
        Pedido pedido = new Pedido(id, cliente);
        pedido.setFechaEntrega(fechaEntrega);
        pedido.getLineaPedidos().add(new LineaPedido(producto, cantidad));
        return pedido;
    }

    @Test
    void filtraPedidosDentroDelRangoDeFechasInclusiveAmbosExtremos() {
        Pedido limiteInferior = crearPedidoConFecha("P1", LocalDate.of(2024, 1, 1), 2);
        Pedido dentroDelRango = crearPedidoConFecha("P2", LocalDate.of(2024, 1, 15), 3);
        Pedido limiteSuperior = crearPedidoConFecha("P3", LocalDate.of(2024, 1, 31), 1);
        Pedido antesDelRango = crearPedidoConFecha("P4", LocalDate.of(2023, 12, 31), 5);
        Pedido despuesDelRango = crearPedidoConFecha("P5", LocalDate.of(2024, 2, 1), 5);

        ReporteVisitorSpy visitorSpy = new ReporteVisitorSpy();
        FormatoExportadorSpy exportadorSpy = new FormatoExportadorSpy();

        generador.generarReporte(
                List.of(limiteInferior, dentroDelRango, limiteSuperior, antesDelRango, despuesDelRango),
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31),
                visitorSpy,
                exportadorSpy
        );

        assertEquals(3, visitorSpy.itemsVisitados.size(),
                "Solo deben procesarse los pedidos dentro del rango (bordes inclusive)");
    }

    @Test
    void pasaCantidadYPrecioDeCadaLineaAlVisitorAntesDeVisitar() {
        Pedido pedido = crearPedidoConFecha("P1", LocalDate.of(2024, 1, 10), 7);

        ReporteVisitorSpy visitorSpy = new ReporteVisitorSpy();
        FormatoExportadorSpy exportadorSpy = new FormatoExportadorSpy();

        generador.generarReporte(
                List.of(pedido),
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31),
                visitorSpy,
                exportadorSpy
        );

        assertEquals(1, visitorSpy.preciosRegistrados.size());
        assertEquals(producto.getPrecioBase(), visitorSpy.preciosRegistrados.get(0));
        assertEquals(7, visitorSpy.cantidadesRegistradas.get(0));
    }

    @Test
    void llamaAlExportadorConTituloFechasYListaFinalDelVisitor() {
        Pedido pedido = crearPedidoConFecha("P1", LocalDate.of(2024, 1, 10), 1);

        ReporteVisitorSpy visitorSpy = new ReporteVisitorSpy();
        FormatoExportadorSpy exportadorSpy = new FormatoExportadorSpy();

        LocalDate desde = LocalDate.of(2024, 1, 1);
        LocalDate hasta = LocalDate.of(2024, 1, 31);

        String resultado = generador.generarReporte(List.of(pedido), desde, hasta, visitorSpy, exportadorSpy);

        assertEquals("REPORTE_GENERADO", resultado);
        assertEquals("Reporte de prueba", exportadorSpy.tituloRecibido);
        assertEquals(desde, exportadorSpy.desdeRecibido);
        assertEquals(hasta, exportadorSpy.hastaRecibido);
        assertNotNull(exportadorSpy.entradasRecibidas);
    }

    @Test
    void pedidoSinLineasDePedidoNoRompeYNoVisitaNada() {
        Pedido pedidoVacio = crearPedidoConFecha("P1", LocalDate.of(2024, 1, 10), 0);
        pedidoVacio.getLineaPedidos().clear();

        ReporteVisitorSpy visitorSpy = new ReporteVisitorSpy();
        FormatoExportadorSpy exportadorSpy = new FormatoExportadorSpy();

        generador.generarReporte(
                List.of(pedidoVacio),
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31),
                visitorSpy,
                exportadorSpy
        );

        assertTrue(visitorSpy.itemsVisitados.isEmpty());
    }

    @Test
    void listaDePedidosVaciaGeneraReporteSinEntradas() {
        ReporteVisitorSpy visitorSpy = new ReporteVisitorSpy();
        FormatoExportadorSpy exportadorSpy = new FormatoExportadorSpy();

        generador.generarReporte(
                List.of(),
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31),
                visitorSpy,
                exportadorSpy
        );

        assertTrue(visitorSpy.itemsVisitados.isEmpty());
        assertTrue(exportadorSpy.entradasRecibidas.isEmpty());
    }
}
