package unqshop.pedido.estado;

import unqshop.pedido.Pedido;

/**
 * ENVIADO: el paquete está en camino.
 * Si se cancela acá: solo se reembolsan los productos, NO el costo de envío.
 */
public class EstadoEnviado implements EstadoPedido {

    @Override
    public void entregar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEntregado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        // Reembolso parcial: solo el subtotal de productos, no el envío
        pedido.generarNotaCredito(pedido.getSubtotalProductos());
        pedido.cambiarEstado(new EstadoCancelado());
    }
}
