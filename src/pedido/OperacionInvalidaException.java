package unqshop.pedido;

/**
 * Excepción de dominio lanzada cuando se intenta una operación
 * que no está permitida en el estado actual del pedido.
 *
 * Al ser una RuntimeException no obliga a declararla con throws,
 * pero sigue siendo una excepción propia del dominio (no genérica).
 */
public class OperacionInvalidaException extends RuntimeException {

    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
