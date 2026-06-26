package unqshop.pedido.estado;

import unqshop.pedido.Pedido;

/**
 * BORRADOR: el cliente está armando el pedido.
 * Únicas operaciones válidas: agregar/quitar ítems, confirmar, cancelar.
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
        pedido.cambiarEstado(new EstadoConfirmado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        // Desde borrador no hay stock que reponer ni reembolso
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
