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
    void actualizar(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo);
}
