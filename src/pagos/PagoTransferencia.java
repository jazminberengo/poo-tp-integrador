package pagos;

public class PagoTransferencia extends ProcesadorPago{
	
	private APITransferencia api;
	
	public PagoTransferencia(APITransferencia api) {
		this.api = api;
	}

	@Override
	protected void validarDatos() {
		api.validarCuenta();
	}

	@Override
	protected void reservarFondos() {
		// No aplica para transferencias bancarias
	}

	@Override
	protected void ejecutarTransaccion() {
		codigoTransaccion = api.ejecutarTransferencia();
	}

	@Override
	protected void notificarResultado() {
		super.notificarResultado();
		api.generarComprobante(codigoTransaccion);
	}

}
