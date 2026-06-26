package pagos;

public class PagoTarjeta extends ProcesadorPago{

	@Override
	protected void validarDatos() {
		// TODO Validar los datos de la tarjeta (nro, vencimiento, CVV)
		
	}

	@Override
	protected void reservarFondos() {
		// TODO Reservar el monto en la tarjeta antes de confirmar el pago
		
	}

	@Override
	protected void ejecutarTransaccion() {
		// TODO Ejecutar el pago utilizando la API de la tarjeta
		
	}

	@Override
	protected void notificarResultado() {
		// TODO Generar y enviar el cupon de pago
		
	}

}
