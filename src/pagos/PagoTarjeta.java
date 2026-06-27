package pagos;

public class PagoTarjeta extends ProcesadorPago{
	
	private APITarjeta api;
	
	public PagoTarjeta(APITarjeta api) {
		this.api = api;
	}

	@Override
	protected void validarDatos() {
		api.validarTarjeta();
	}

	@Override
	protected void reservarFondos() {
		api.preAutorizarFondos();
	}

	@Override
	protected void ejecutarTransaccion() {
		codigoTransaccion = api.ejecutarPago();
	}

	@Override
	protected void notificarResultado() {
		super.notificarResultado();
		api.generarCupon(codigoTransaccion);
	}

}
