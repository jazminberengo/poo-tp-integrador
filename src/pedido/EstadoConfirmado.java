package pedido;

/*
   CONFIRMADO: el cliente confirmó. El stock ya fue decrementado.
   Si se cancela desde acá: se repone el stock y se reembolsa todo (productos + envío).
 */
public class EstadoConfirmado implements EstadoPedido {

    @Override
    public void preparar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnPreparacion());
    }

    @Override
    public void cancelar(Pedido pedido) {
        // Viene de CONFIRMADO: hay que reponer stock y reembolsar todo
        pedido.reponerStock();
        pedido.generarNotaCredito(pedido.getTotal()); // reembolso total
        pedido.registrarFechaCancelacion();
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
