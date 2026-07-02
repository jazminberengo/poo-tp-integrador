package notificaciones;

import pedido.EstadoPedido;
import pedido.Pedido;

/*
   Interfaz del patrón Observer.
   Cualquier objeto que quiera reaccionar a cambios de estado de un Pedido
   debe implementar esta interfaz y suscribirse al pedido.
 */
public interface Observador {

    /* 
       Método invocado por el Pedido cada vez que su estado cambia.
      
       pedido   =  el pedido que cambió de estado
       anterior =  estado previo al cambio
       nuevo    =  estado al que transicionó
     */
	
	// Implementación default para respuesta a notificaciones: No hacer nada. 
	// Si se debe hacer algo, se sobreescribe por cada implementación
	default void onConfirmado(Pedido pedido) {}
	default void onBorrador(Pedido pedido) {}
	default void onPreparacion(Pedido pedido) {}
    default void onEnviado(Pedido pedido) {}
    default void onEntregado(Pedido pedido) {}
    default void onCancelado(Pedido pedido) {}
}
