package pagos;

public abstract class ProcesadorPago {

	public String codigoTransaccion;
	
	// Template Method
	public void procesarPago() {
		validarDatos();
		reservarFondos();
		ejecutarTransaccion();
		notificarResultado();
	}
	
	// Pasos que las subclases deben implementar
	protected abstract void validarDatos();
	
	protected abstract void reservarFondos();
	
	protected abstract void ejecutarTransaccion();
	
	protected void notificarResultado() {
		System.out.println("Codigo de transaccion: " + codigoTransaccion);
	}

}
