package pedido;

/*
   Excepción lanzada cuando se intenta una operación
   que no está permitida en el estado actual del pedido.
   Creada para evitar excepciones genéricas
 */
public class OperacionInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
