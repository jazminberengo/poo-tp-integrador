package notificaciones;

import pedido.Pedido;

/*
   Interfaz del patrón Observer.
   Cualquier objeto que quiera reaccionar a cambios de estado de un Pedido
   debe implementar esta interfaz y suscribirse al pedido.
 */
public interface Observador {
	
	/*
	 *  Implementación default para respuesta a notificaciones: No hacer nada. 
	 *  Si se debe hacer algo, se sobreescribe por cada implementación
	 */
	default void onConfirmado(Pedido pedido) {}
    default void onEnviado(Pedido pedido) {}
    default void onEntregado(Pedido pedido) {}
    default void onCancelado(Pedido pedido) {}
}
