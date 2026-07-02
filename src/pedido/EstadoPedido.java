package pedido;

import notificaciones.Observador;

/*
 * Implementa patrón STATE. EstadoPedido es la interfaz que define todas las operaciones posibles sobre un pedido.
 *
 * Los estados concretos implementan solo operaciones válidas (según dominio) dada su estado.
 * Las operaciones inválidas lanzan OperacionInvalidaException. 
 */
public interface EstadoPedido {

	default void agregarItem(Pedido pedido) { operacionInvalida("agregar ítems"); }
	default void quitarItem(Pedido pedido)  { operacionInvalida("quitar ítems"); }
	default void confirmar(Pedido pedido)   { operacionInvalida("confirmar"); }
	default void preparar(Pedido pedido)    { operacionInvalida("preparar"); }
	default void enviar(Pedido pedido)      { operacionInvalida("enviar"); }
	default void entregar(Pedido pedido)    { operacionInvalida("entregar"); }
	default void cancelar(Pedido pedido)    { operacionInvalida("cancelar"); }

	private void operacionInvalida(String operacion) {
	    throw new OperacionInvalidaException(
	        "No se puede " + operacion + " en estado: " + this.getClass().getSimpleName()
	    );
	}
	
	// En EstadoPedido
	default void notificarObservador(Observador observador, Pedido pedido, EstadoPedido anterior) {
	    // por defecto no hace nada
	}
}
