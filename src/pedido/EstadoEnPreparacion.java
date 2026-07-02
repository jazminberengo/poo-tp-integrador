package pedido;

import notificaciones.Observador;

/*
 * EN PREPARACION: el depósito está preparando el envío.
 * Solo puede avanzar a ENVIADO. No puede cancelarse.
 */
public class EstadoEnPreparacion implements EstadoPedido {

    @Override
    public void enviar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEnviado());
    }
    
    @Override
    public void notificarObservador(Observador observador, Pedido pedido, EstadoPedido anterior) {
        observador.onPreparacion(pedido);
    }
}
