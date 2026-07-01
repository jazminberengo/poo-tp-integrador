package reportes;

import catalogo.Paquete;
import catalogo.Producto;
import envios.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class VisitorItemsMasVendidosTest {

    private VisitorItemsMasVendidos visitor;
    private Producto productoA;
    private Producto productoB;
    private Paquete paquete;

    @BeforeEach
    void setUp() {
        visitor = new VisitorItemsMasVendidos();
        Sucursal sucursal = new Sucursal();
        productoA = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.1f, "MarcaX");
        productoB = new Producto("Teclado", "desc", sucursal, "SKU-2", 200f, 0.5f, "MarcaY");
        paquete = new Paquete("Combo Gamer", "desc", sucursal);
    }

    @Test
    void tituloDevuelveElTextoEsperado() {
        assertEquals("Reporte de Productos Más Vendidos", visitor.titulo());
    }

    @Test
    void visitProductoCreaUnaEntradaNueva() {
        visitor.setVentaActual(3, 100f);
        visitor.visit(productoA);

        List<Entrada> finalizadas = visitor.getFinishedList();

        assertEquals(1, finalizadas.size());
        assertEquals(productoA, finalizadas.get(0).getItemCatalogo());
        assertEquals(3, finalizadas.get(0).getUnidadesVendidas());
        assertEquals(100f, finalizadas.get(0).getPrecioPromedio());
    }

    @Test
    void visitPaqueteCreaUnaEntradaNueva() {
        visitor.setVentaActual(4, 500f);
        visitor.visit(paquete);

        List<Entrada> finalizadas = visitor.getFinishedList();

        assertEquals(1, finalizadas.size());
        assertEquals(paquete, finalizadas.get(0).getItemCatalogo());
        assertEquals(4, finalizadas.get(0).getUnidadesVendidas());
    }

    @Test
    void visitarElMismoItemDosVecesAcumulaUnidadesYPromediaElPrecio() {
        visitor.setVentaActual(3, 100f);
        visitor.visit(productoA);

        visitor.setVentaActual(2, 130f);
        visitor.visit(productoA);

        List<Entrada> finalizadas = visitor.getFinishedList();

        assertEquals(1, finalizadas.size(), "Debe mergear en una sola entrada por nombre");
        Entrada e = finalizadas.get(0);
        assertEquals(5, e.getUnidadesVendidas());
        // (3*100 + 2*130) / 5 = 560/5 = 112
        assertEquals(112f, e.getPrecioPromedio(), 0.0001f);
    }

    @Test
    void getFinishedListOrdenaDeMayorAMenorCantidadVendida() {
        visitor.setVentaActual(3, 100f);
        visitor.visit(productoA); // 3 unidades

        visitor.setVentaActual(10, 200f);
        visitor.visit(productoB); // 10 unidades

        visitor.setVentaActual(7, 500f);
        visitor.visit(paquete); // 7 unidades

        List<Entrada> finalizadas = visitor.getFinishedList();

        assertEquals(3, finalizadas.size());
        assertEquals(productoB, finalizadas.get(0).getItemCatalogo()); // 10
        assertEquals(paquete, finalizadas.get(1).getItemCatalogo());   // 7
        assertEquals(productoA, finalizadas.get(2).getItemCatalogo()); // 3
    }
}
