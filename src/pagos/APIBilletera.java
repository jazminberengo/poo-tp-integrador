package pagos;

public interface APIBilletera {

	boolean validarSaldo();
	
	void bloquearSaldo();
	
	String ejecutarPago();
	
	void enviarNotificacion(String codigoTransaccion);
	
}
