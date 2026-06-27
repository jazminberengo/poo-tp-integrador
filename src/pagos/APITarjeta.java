package pagos;

public interface APITarjeta {
	
	boolean validarTarjeta();
	
	void preAutorizarFondos();
	
	String ejecutarPago();
	
	void generarCupon(String codigoTransaccion);
}
