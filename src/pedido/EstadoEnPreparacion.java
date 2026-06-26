package unqshop.pedido.estado;

import unqshop.pedido.Pedido;

/**
 * EN_PREPARACION: el depósito está preparando el envío.
 * Solo puede avanzar a ENVIADO. No puede cancelarse según la consigna.
 */
public class EstadoEnPreparacion implements EstadoPedido {

    @Override
    public void enviar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnviado());
    }
}
