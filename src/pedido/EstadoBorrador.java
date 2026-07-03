package pedido;

import notificaciones.Observador;

/*
 * BORRADOR: el cliente está armando el pedido.
 * Operaciones válidas: agregar/quitar ítems, confirmar, cancelar.
 */
public class EstadoBorrador implements EstadoPedido {

    @Override
    public void agregarItem(Pedido pedido) {
        // válido — Pedido se encarga de la lógica de agregar
    }

    @Override
    public void quitarItem(Pedido pedido) {
        // válido — Pedido se encarga de la lógica de quitar
    }

    @Override
    public void confirmar(Pedido pedido) {
        // Decrementa el stock de cada ítem y avanza al siguiente estado
        pedido.decrementarStock();
        pedido.registrarFechaConfirmacion();
        pedido.cambiarEstado(new EstadoConfirmado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        // No hay stock que reponer ni reembolso
    	pedido.registrarFechaCancelacion();
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
