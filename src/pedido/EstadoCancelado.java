package pedido;

import notificaciones.Observador;

/*
 * CANCELADO: estado terminal. 
 * No se permite ninguna operación.
 */
public class EstadoCancelado implements EstadoPedido {
    // cualquier operación sobre el pedido lanza OperacionInvalidaException
	
	@Override
	public void notificarObservador(Observador observador, Pedido pedido, EstadoPedido anterior) {
	    observador.onCancelado(pedido);
	}
}
