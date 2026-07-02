package pedido;

import notificaciones.Observador;

/*
 * CONFIRMADO: el cliente confirmó, stock decrementado.
 * Si fuese cancelado luego, se repone el stock y se reembolsa todo (productos + envío).
 */
public class EstadoConfirmado implements EstadoPedido {

    @Override
    public void preparar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnPreparacion());
    }

    @Override
    public void cancelar(Pedido pedido) {
        // hay que reponer stock y reembolsar todo
        pedido.reponerStock();
        pedido.generarNotaCredito(pedido.getTotal()); // reembolso total
        pedido.registrarFechaCancelacion();
        pedido.cambiarEstado(new EstadoCancelado());
    }
    
    @Override
    public void notificarObservador(Observador observador, Pedido pedido, EstadoPedido anterior) {
        observador.onConfirmado(pedido);
    }
}
