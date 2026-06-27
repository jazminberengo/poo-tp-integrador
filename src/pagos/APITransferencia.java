package pagos;

public interface APITransferencia {
	
	boolean validarCuenta();
	
	String ejecutarTransferencia();
	
	void generarComprobante(String codigoTransaccion);
}
