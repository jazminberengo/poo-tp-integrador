package reportes;

import envios.Sucursal;

import org.junit.jupiter.api.Test;
import catalogo.Producto;
import catalogo.Paquete;

import static org.junit.jupiter.api.Assertions.*;

class ProductoVisitorTest {

    @Test
    void acceptInvocaVisitConProductoYConLaInstanciaCorrecta() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Mouse", "Mouse inalambrico", sucursal,
                "SKU-001", 1500f, 0.2f, "Logitech");

        final boolean[] visitoProducto = {false};
        final boolean[] visitoPaquete = {false};

        ItemVisitor visitor = new ItemVisitor() {
            @Override
            public void visit(Producto p) {
                visitoProducto[0] = true;
                assertSame(producto, p, "Debe pasarse la misma instancia de Producto");
            }

            @Override
            public void visit(Paquete paquete) {
                visitoPaquete[0] = true;
            }
        };

        producto.accept(visitor);

        assertTrue(visitoProducto[0], "accept() debe invocar visit(Producto)");
        assertFalse(visitoPaquete[0], "accept() de Producto no debe invocar visit(Paquete)");
    }
}
