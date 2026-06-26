package unqshop.pedido.estado;

import unqshop.pedido.Pedido;

/**
 * Patrón STATE — interfaz que define todas las operaciones posibles sobre un pedido.
 *
 * Cada estado concreto implementa solo las operaciones que tiene sentido hacer
 * en ese momento. Las operaciones inválidas lanzan OperacionInvalidaException.
 *
 * Por eso Pedido no tiene ningún if/switch sobre el estado: simplemente delega
 * en el objeto estado actual, que sabe qué puede y qué no puede hacer.
 */
public interface EstadoPedido {

    // Estas operaciones solo son válidas en BORRADOR
    default void agregarItem(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se pueden agregar ítems en estado: " + this.getClass().getSimpleName()
        );
    }

    default void quitarItem(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se pueden quitar ítems en estado: " + this.getClass().getSimpleName()
        );
    }

    // Transiciones del ciclo de vida
    default void confirmar(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se puede confirmar en estado: " + this.getClass().getSimpleName()
        );
    }

    default void preparar(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se puede preparar en estado: " + this.getClass().getSimpleName()
        );
    }

    default void enviar(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se puede enviar en estado: " + this.getClass().getSimpleName()
        );
    }

    default void entregar(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se puede entregar en estado: " + this.getClass().getSimpleName()
        );
    }

    default void cancelar(Pedido pedido) {
        throw new unqshop.pedido.OperacionInvalidaException(
            "No se puede cancelar en estado: " + this.getClass().getSimpleName()
        );
    }
}
