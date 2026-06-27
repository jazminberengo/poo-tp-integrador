package pedido;

/*
 * Se lanza esta excepción cuando se intentan ejecutar 
 * operaciones inválidas dado el estado actual. 
 */
public class OperacionInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
