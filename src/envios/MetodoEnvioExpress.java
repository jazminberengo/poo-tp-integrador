package envios;

import pedido.Pedido;

public class MetodoEnvioExpress implements MetodoEnvio {

	@Override
	public float calcularCosto(Pedido pedido) {
		return EnvioExpress.calcularCosto( pedido.getSubtotalProductos() );
	}
	@Override
	public int estimarDias(Pedido pedido) {
		return 1;
	}

}
