package pedido;

import notificaciones.Observador;

/*
 * ENTREGADO: estado terminal. El cliente recibió el pedido.
 * No se permite ninguna operación.
 */
public class EstadoEntregado implements EstadoPedido {
    // cualquier operación sobre el pedido lanza OperacionInvalidaException
	
	@Override
	public void notificarObservador(Observador observador, Pedido pedido, EstadoPedido anterior) {
	    observador.onEntregado(pedido);
	}
}
