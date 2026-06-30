package envios;

import pedido.Pedido;

public class MetodoRetiroSucursal implements MetodoEnvio {

	private Sucursal sucursal;
	
	public MetodoRetiroSucursal(Sucursal sucursal){ this.sucursal = sucursal; }
	
	@Override
	public float calcularCosto(Pedido pedido) {
		return 0;
	}

	@Override
	public int estimarDias(Pedido pedido) {
		if( sucursal.tieneStockPara( pedido ) ) {
			return 0;
		}
		return 3;
	}

}
