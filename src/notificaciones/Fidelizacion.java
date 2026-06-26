package notificaciones;

import pedido.EstadoPedido;
import pedido.EstadoEntregado;
import pedido.EstadoCancelado;
import pedido.Pedido;

public class Fidelizacion implements Observador {

    @Override
    public void actualizar(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
        int puntos = (int) (pedido.getTotal() / 100);

        if (nuevo instanceof EstadoEntregado) {
            System.out.println(
                    "[Fidelización] +" + puntos +
                    " puntos para " +
                    pedido.getCliente().getNombre()
            );
        } else if (nuevo instanceof EstadoCancelado
                && anterior instanceof EstadoEntregado) {

            System.out.println(
                    "[Fidelización] -" + puntos +
                    " puntos para " +
                    pedido.getCliente().getNombre()
            );
        }
    }
}
