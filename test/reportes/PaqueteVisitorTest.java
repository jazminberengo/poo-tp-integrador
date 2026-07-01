package reportes;

import envios.Sucursal;
import org.junit.jupiter.api.Test;
import catalogo.Paquete;
import catalogo.Producto;


import static org.junit.jupiter.api.Assertions.*;

class PaqueteVisitorTest {

    @Test
    void acceptInvocaVisitConPaqueteYConLaInstanciaCorrecta() {
        Sucursal sucursal = new Sucursal();
        Paquete paquete = new Paquete("Combo Gamer", "Mouse + Teclado", sucursal);

        final boolean[] visitoProducto = {false};
        final boolean[] visitoPaquete = {false};

        ItemVisitor visitor = new ItemVisitor() {
            @Override
            public void visit(Producto p) {
                visitoProducto[0] = true;
            }

            @Override
            public void visit(Paquete p) {
                visitoPaquete[0] = true;
                assertSame(paquete, p, "Debe pasarse la misma instancia de Paquete");
            }
        };

        paquete.accept(visitor);

        assertTrue(visitoPaquete[0], "accept() debe invocar visit(Paquete)");
        assertFalse(visitoProducto[0], "accept() de Paquete no debe invocar visit(Producto)");
    }
}
