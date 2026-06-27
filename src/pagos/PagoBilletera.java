package pagos;

public class PagoBilletera extends ProcesadorPago{
	
	private APIBilletera api;
	
	public PagoBilletera(APIBilletera api) {
		this.api = api;
	}

	@Override
	protected void validarDatos() {
		api.validarSaldo();		
	}

	@Override
	protected void reservarFondos() {
		api.bloquearSaldo();
	}

	@Override
	protected void ejecutarTransaccion() {
		codigoTransaccion = api.ejecutarPago();
	}

	@Override
	protected void notificarResultado() {
		super.notificarResultado();
		api.enviarNotificacion(codigoTransaccion);
	}

}
