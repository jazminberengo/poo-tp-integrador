package notificaciones;

import pedido.*;
import java.time.LocalDate;

public class GeneradorFactura implements Observador {

    @Override
    public void onEntregado(Pedido pedido) {
            emitirFactura(pedido);
    }

    private void emitirFactura(Pedido pedido) {
        String factura = "";

        factura += "=== FACTURA ===\n";
        factura += "Pedido #" + pedido.getId() + "\n";
        factura += "Fecha: " + LocalDate.now() + "\n";
        factura += "Cliente: " + pedido.getCliente().getNombre() + "\n";
        factura += "Email: " + pedido.getCliente().getEmail() + "\n\n";

        for (LineaPedido lp : pedido.getItems()) {
            factura += lp.getItem().getNombre()
                    + " x" + lp.getCantidad()
                    + " - $" + lp.getSubtotal()
                    + "\n";
        }

        factura += "\nSubtotal: $" + pedido.getSubtotalProductos() + "\n";
        factura += "Envío: $" + pedido.getCostoEnvio() + "\n";
        factura += "Total: $" + pedido.getTotal() + "\n";

        System.out.println(factura);
    }
}
