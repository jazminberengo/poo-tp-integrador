package notificaciones;

import pedido.EstadoPedido;
import pedido.EstadoCancelado;
import pedido.Pedido;

public class Fidelizacion implements Observador {

    @Override
    public void actualizar(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
        if (nuevo instanceof EstadoCancelado) {
            float descuento = pedido.getTotal() * 0.05f;
            System.out.println(
                "[Fidelización] Cupón de 5% generado para " +
                pedido.getCliente().getNombre() +
                " - Valor: $" + descuento
            );
        }
    }
}
