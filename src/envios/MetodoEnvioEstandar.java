package envios;

import pedido.Pedido;

class MetodoEnvioEstandar implements 	MetodoEnvio{

	@Override
	public float calcularCosto(Pedido pedido) {
		
		Direccion direccion = pedido.getDireccion();
		float     peso      = pedido.getPesoTotal();
		
		return CorreoArgentino.estimarCostoEnvio(peso, direccion);
	}

	@Override
	public int estimarDias(Pedido pedido) {
		return CorreoArgentino.estimarDiasEnvio( pedido.getDireccion() );
	}
}
