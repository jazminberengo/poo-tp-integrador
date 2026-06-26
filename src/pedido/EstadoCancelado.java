package unqshop.pedido.estado;

/**
 * CANCELADO: estado terminal. El pedido fue cancelado.
 * No se permite ninguna operación — todos los métodos lanzan excepción
 * gracias a los defaults de EstadoPedido.
 */
public class EstadoCancelado implements EstadoPedido {
    // Sin overrides: cualquier operación lanza OperacionInvalidaException
}
