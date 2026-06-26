package pedido;


/*
   Patrón STATE — interfaz que define todas las operaciones posibles sobre un pedido.
  
   Cada estado concreto implementa solo las operaciones que tiene sentido hacer
   en ese momento. Las operaciones inválidas lanzan OperacionInvalidaException.
  
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
}
