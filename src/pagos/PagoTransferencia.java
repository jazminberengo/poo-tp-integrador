package pagos;

public class PagoTransferencia extends ProcesadorPago{

	@Override
	protected void validarDatos() {
		// TODO Validar datos de la transferencia (CBU, alias)
		
	}

	@Override
	protected void reservarFondos() {
		// No aplica para transferencias bancarias
		
	}

	@Override
	protected void ejecutarTransaccion() {
		// TODO Ejecutar la transferencia mediante la API
		
	}

	@Override
	protected void notificarResultado() {
		// TODO Generar comprobante de transferencia
		
	}

}
