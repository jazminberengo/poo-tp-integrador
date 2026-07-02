package pedido;

import notificaciones.Observador;

/*
 * ENVIADO: el paquete está en camino.
 * Si fuese cancelado, solo se reembolsan los productos, NO el costo de envío.
 */
public class EstadoEnviado implements EstadoPedido {

    @Override
    public void entregar(Pedido pedido) {
    	pedido.registrarFechaEntrega();
        pedido.cambiarEstado(new EstadoEntregado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        // solo el subtotal de productos, no el envío
        pedido.generarNotaCredito(pedido.getSubtotalProductos());
        pedido.registrarFechaCancelacion();
        pedido.cambiarEstado(new EstadoCancelado());
    }
    
    @Override
    public void notificarObservador(Observador observador, Pedido pedido, EstadoPedido anterior) {
        observador.onEnviado(pedido);
    }
}
