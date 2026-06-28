package envios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catalogo.ItemCatalogo;
import pedido.LineaPedido;
import pedido.Pedido;

public class Sucursal {

	private Map<String, Registro> stock = new HashMap<String, Registro>();
	
	public boolean tieneStockPara( Pedido pedido ) {
		
		List<LineaPedido> lineaPedidos = pedido.getLineaPedidos();
		
		int lineaPedidosSize = lineaPedidos.size();
		
		for( int i = 0; i < lineaPedidosSize; i++ ) {
			LineaPedido lineaPedidoActual = lineaPedidos.get(i);
			String nombreItem = lineaPedidoActual.getItem().getNombre();

			if ( !(stock.get( nombreItem ).cumplePara( lineaPedidoActual )) ) {
				return false;
			}
		}
		return true;
	}
	
	public void decrementarStock( ItemCatalogo item, int cantidad ) {
		String clave = item.getNombre();
		Registro registro = this.stock.get(clave);
		
		int cantAntesDeDecremento = registro.getCantidad();
		registro.setCantidad( cantAntesDeDecremento - cantidad);
	}
	
	public void incrementarStock( ItemCatalogo item, int cantidad ) {
		String clave = item.getNombre();
		Registro registro = this.stock.get(clave);
		
		int cantAntesDeIncremento = registro.getCantidad();
		registro.setCantidad( cantAntesDeIncremento + cantidad);
	}
	
	public boolean tieneStock(ItemCatalogo item) {
		Registro registro = stock.get(item.getNombre());
		
		return registro != null && registro.getCantidad() > 0;
	}
	
	public void agregarRegistro(Registro registro) {
		stock.put(registro.getItem().getNombre(), registro);
	}
}


















